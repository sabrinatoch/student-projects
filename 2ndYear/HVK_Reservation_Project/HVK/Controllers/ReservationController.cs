using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using HVK.Models;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Drawing;
using System;

namespace HVK.Controllers {
    public class ReservationController : Controller {
        private readonly HVKW24_Team3Context _context;

        public ReservationController(HVKW24_Team3Context context) {
            _context = context;
        }
        public IActionResult Index() {
            return View();
        }
        public IActionResult AllReservations() {
            return View();
        }
        [HttpGet]
        [Authorize(Roles = "employee,customer")]
        public IActionResult Create(int? id) {

            var customer = _context.Hvkusers
                            .Include(p => p.Pets)
                            .ThenInclude(p => p.PetVaccinations)
                            .ThenInclude(pv => pv.Vaccination)
                            .FirstOrDefault(x => x.HvkuserId == id);
            ReservationViewModel model = new ReservationViewModel();
            model.Hvkuser = customer;

            foreach (var pet in customer.Pets) {
                pet.Hvkuser = customer;
                var viewModel = new PetVaccinationViewModel {
                    Pet = pet,
                    VaccinationExpiryDates = Enumerable.Range(1, 6).ToDictionary(
                id => id,
                id => pet.PetVaccinations.FirstOrDefault(pv => pv.VaccinationId == id)?.ExpiryDate
            )
                };
                model.PetVaccinationViewModels.Add(viewModel);
            }

            ViewData["ServiceId"] = new SelectList(_context.Services.Where(s => s.ServiceDescription != "Boarding" && s.ServiceDescription != "Medication").ToList(), "ServiceId", "ServiceDescription");
            ViewData["VaccinationId"] = new SelectList(_context.Vaccinations.ToList(), "VaccinationId", "Name");

            ViewBag.VaccinationNames = new[] { "Bordetella", "Distemper", "Hepatitis", "Parainfluenza", "Parovirus", "Rabies" };

            return View(model);
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> Create(ReservationViewModel model) {

            var customer = _context.Hvkusers
                            .Include(p => p.Pets)
                            .ThenInclude(p => p.PetVaccinations)
                            .ThenInclude(pv => pv.Vaccination)
                            .FirstOrDefault(x => x.HvkuserId == model.Hvkuser.HvkuserId);

            for (int i = 0; i < model.PetVaccinationViewModels.Count(); ++i) {
                model.PetVaccinationViewModels[i].Pet.Hvkuser = customer;
                ModelState.Remove($"PetVaccinationViewModels[{i}].Pet.Hvkuser");
            }

            ViewData["ServiceId"] = new SelectList(_context.Services.Where(s => s.ServiceDescription != "Boarding" && s.ServiceDescription != "Medication").ToList(), "ServiceId", "ServiceDescription");
            ViewData["VaccinationId"] = new SelectList(_context.Vaccinations.ToList(), "VaccinationId", "Name");
            ViewBag.VaccinationNames = new[] { "Bordetella", "Distemper", "Hepatitis", "Parainfluenza", "Parovirus", "Rabies" };

            if (ModelState.IsValid) {

                var selectedPets = Request.Form["selectedpets"].ToList();
                if (selectedPets.Count() == 0) {
                    ModelState.AddModelError("SelectPets", "Please select a pet.");
                    return View(model);
                }

                // run availability check

                var reservationsDuringDates = _context.Reservations.Include(r => r.PetReservations).ThenInclude(pr => pr.Pet).Where(
                                                    r => (r.StartDate >= model.Reservation.StartDate && r.StartDate <= model.Reservation.EndDate) ||
                                                   (r.EndDate >= model.Reservation.StartDate && r.EndDate <= model.Reservation.EndDate) ||
                                                   (r.StartDate <= model.Reservation.StartDate && r.EndDate >= model.Reservation.EndDate)).ToList();

                var emptyRuns = _context.Runs.ToList().Count();
                var neededRuns = selectedPets.Count();

                foreach (var res in reservationsDuringDates) {
                    if (res.PetReservations.Count > 0) {
                        foreach (var petRes in res.PetReservations) {
                            emptyRuns--;
                        }
                    }
                }
                await _context.SaveChangesAsync();
                if (neededRuns > emptyRuns) {
                    ModelState.AddModelError("AvailableRuns", "Not enough runs available for the selected dates.");
                    return View(model);
                }

                model.Reservation.Status = 2;

                _context.Add(model.Reservation);
                await _context.SaveChangesAsync();

                var resId = _context.Reservations.Max(r => r.ReservationId);

                // apply discounts

                if (selectedPets.Count() >= 3) {
                    var reservationDiscount = new ReservationDiscount {
                        DiscountId = 2,
                        ReservationId = resId
                    };
                    _context.Add(reservationDiscount);
                }

                foreach (var viewModel in model.PetVaccinationViewModels) {
                    if (viewModel.Pet == null)
                        continue;
                    else if (selectedPets.Contains(viewModel.Pet.PetId.ToString())) {
                        try {
                            foreach (var kvp in viewModel.VaccinationExpiryDates) {
                                var petVaccination = _context.PetVaccinations.FirstOrDefault(pv => pv.PetId == viewModel.Pet.PetId && pv.VaccinationId == kvp.Key);
                                if (petVaccination != null) {
                                    if (kvp.Value.HasValue) {
                                        petVaccination.ExpiryDate = kvp.Value.Value;
                                        _context.Update(petVaccination);
                                    } else {
                                        _context.Remove(petVaccination);
                                    }
                                } else if (kvp.Value.HasValue) {
                                    petVaccination = new PetVaccination {
                                        PetId = viewModel.Pet.PetId,
                                        VaccinationId = kvp.Key,
                                        ExpiryDate = kvp.Value.Value
                                    };
                                    _context.Add(petVaccination);
                                }
                            }

                            await _context.SaveChangesAsync();
                        } catch (DbUpdateConcurrencyException) {
                        }
                    }
                }


                // add petreservations 

                for (int i = 0; i < selectedPets.Count(); ++i) {
                    var petRes = new PetReservation {
                        PetId = Int32.Parse(selectedPets[i]),
                        ReservationId = resId
                    };
                    model.Reservation.PetReservations.Add(petRes);
                    _context.Add(petRes);
                }
                await _context.SaveChangesAsync();

                // grab added reservation

                var savedReservation = await _context.Reservations
                                       .Include(r => r.PetReservations)
                                       .SingleOrDefaultAsync(r => r.ReservationId == model.Reservation.ReservationId);

                // add services

                foreach (var petRes in savedReservation.PetReservations) {
                    var petReservationBoarding = new PetReservationService {
                        PetReservationId = petRes.PetReservationId,
                        ServiceId = 1
                    };
                    _context.Add(petReservationBoarding);
                    var serviceList = Request.Form[$"Services_{petRes.Pet.PetId}"].ToList();
                    if (serviceList.Count() > 0) {
                        for (int i = 0; i < serviceList.Count(); ++i) {
                            var petReservationService = new PetReservationService {
                                PetReservationId = petRes.PetReservationId,
                                ServiceId = Int32.Parse(serviceList[i])
                            };
                            _context.Add(petReservationService);
                        }
                    }

                    // own food discount

                    var discount = Request.Form[$"Discount_{petRes.Pet.PetId}"].ToList();
                    if (discount != null) {
                        var reservationDiscount = new PetReservationDiscount {
                            PetReservationId = petRes.PetReservationId,
                            DiscountId = 3
                        };
                        _context.Add(reservationDiscount);
                    }
                }

                await _context.SaveChangesAsync();

                if (HttpContext.Session.GetString("usertype").ToLower() == "employee")
                    return RedirectToAction("EmployeeHome", "Hvkuser");
                else
                    return RedirectToAction("CustomerHome", "Hvkuser");
            }

            return View(model);
        }

        [Authorize(Roles = "employee,customer")]
        [HttpGet]
        public async Task<IActionResult> Edit(int? id) {

            var reservation = await _context.Reservations.FindAsync(id);
            //var petReservations = _context.PetReservations.Include(pr => pr.Pet).Where(pr => pr.ReservationId == reservation.ReservationId).ToList();
            //reservation.PetReservations = petReservations;

            return View(reservation);
        }

        [Authorize(Roles = "employee,customer")]
        [HttpPost]
        public async Task<IActionResult> Edit(Reservation reservation) {
            reservation.Status = 2;
            if (ModelState.IsValid) {
                _context.Update(reservation);
                await _context.SaveChangesAsync();

                if (HttpContext.Session.GetString("usertype").ToLower() == "employee")
                    return RedirectToAction("EmployeeHome", "Hvkuser");
                else
                    return RedirectToAction("CustomerHome", "Hvkuser");
            }
            return View(reservation);
        } 

        [HttpGet]
        [Authorize(Roles = "employee, customer")]
        public IActionResult AddMedication() {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> AddMedication(Medication medication, ReservationViewModel model) {
            if (ModelState.IsValid) {
                TempData["MedicationAdded"] = medication.Name;

                // Pass the model back to the Create Reservation view
                return RedirectToAction("Create", model);
            }
            return View(medication);
        }

        public async Task<IActionResult> Delete(int? id) {
            if (id == null || _context.Reservations == null) {
                return NotFound();
            }

            var reservation = await _context.Reservations
                .FirstOrDefaultAsync(m => m.ReservationId == id);
            if (reservation == null) {
                return NotFound();
            }

            return View(reservation);
        }

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id) {

            var reservation = await _context.Reservations.FindAsync(id);
            if (reservation != null) {

                var resDiscount = _context.ReservationDiscounts.Where(rd => rd.ReservationId == reservation.ReservationId).FirstOrDefault();
                if (resDiscount != null) {
                    _context.ReservationDiscounts.Remove(resDiscount);
                    await _context.SaveChangesAsync();
                }

                var petRes = _context.PetReservations.Where(pr => pr.ReservationId == reservation.ReservationId).ToList();
                foreach (var pet in petRes) {
                    var services = _context.PetReservationServices.Where(prs => prs.PetReservationId == pet.PetReservationId).ToList();
                    foreach (var service in services) {
                        _context.PetReservationServices.Remove(service);
                    }

                    var discounts = _context.PetReservationDiscounts.Where(prd => prd.PetReservationId == pet.PetReservationId).ToList();
                    foreach (var discount in discounts) {
                        _context.PetReservationDiscounts.Remove(discount);
                    }
                    await _context.SaveChangesAsync();
                    _context.PetReservations.Remove(pet);
                }
                await _context.SaveChangesAsync();
                _context.Reservations.Remove(reservation);
            }

            await _context.SaveChangesAsync();
            if (HttpContext.Session.GetString("usertype").ToLower() == "employee")
                return RedirectToAction("EmployeeHome", "Hvkuser");
            else
                return RedirectToAction("CustomerHome", "Hvkuser");
        }

        private bool ReservationExists(int id) {
            return (_context.Reservations?.Any(e => e.ReservationId == id)).GetValueOrDefault();
        }

        [Authorize(Roles = "employee,customer")]
        public IActionResult ReservationDetails() {
            return View();
        }
        [Authorize(Roles = "employee")]
        public IActionResult StartPetVisit(int id, int? curp) {

            var res = _context.Reservations
            .Include(pr => pr.PetReservations)
            .ThenInclude(p => p.Pet)
            .ThenInclude(u => u.Hvkuser)
            .Include(pr => pr.PetReservations)
            .ThenInclude(p => p.Pet)
            .ThenInclude(pv => pv.PetVaccinations)
            .ThenInclude(v => v.Vaccination)
            .Include(pr => pr.PetReservations)
            .ThenInclude(prs => prs.PetReservationServices)
            .ThenInclude(s => s.Service)
            .ThenInclude(dr => dr.DailyRates)
            .Include(pr => pr.PetReservations)
            .ThenInclude(m => m.Medications)
            .Include(pr => pr.PetReservations)
            .ThenInclude(r => r.Run)
            .Include(pr => pr.PetReservations)
            .ThenInclude(prd => prd.PetReservationDiscounts)
            .ThenInclude(d => d.Discount)
            .ThenInclude(rd => rd.ReservationDiscounts)
            .FirstOrDefault(i => i.ReservationId == id);
            var vacs = _context.Vaccinations.ToList();
            ViewBag.vacs = vacs;
            var ser = _context.Services
         .Where(s => s.ServiceDescription.ToLower() != "boarding" && s.ServiceDescription.ToLower() != "medication").OrderBy(o => o.ServiceDescription).ToList();
            ViewBag.ser = ser;
            var serc = _context.Services
         .Where(s => s.ServiceDescription.ToLower() != "boarding").OrderBy(o => o.ServiceDescription).ToList();
            ViewBag.serc = serc;
            var rates = _context.DailyRates.ToList();
            ViewBag.rates = rates;
            var Dis = _context.Discounts.ToList();
            ViewBag.Dis = Dis;
            var resDis = _context.ReservationDiscounts.Where(r => r.ReservationId == id).ToList();
            ViewBag.resDis = resDis;
            var lruns = _context.Runs.Where(s => s.Status == 1 && s.Size.ToLower() == "l" ).ToList();
            ViewBag.lruns = lruns;
            var mruns = _context.Runs.Where(s => s.Status == 1 && s.Size.ToLower() != "l").ToList();
            ViewBag.mruns = mruns;
            if (curp == 2 || curp == 3) {
                ViewBag.CurrentPart = curp;
                foreach (var pr in res.PetReservations) {
                    // Check if all vaccinations are checked and expiry date is greater than reservation end date
                    var allVaccinationsChecked = pr.Pet.PetVaccinations.Count(v => v.VaccinationChecked && v.ExpiryDate > res.EndDate) == 6;
                    if (!allVaccinationsChecked) {
                        // Set curp to 1 and add an error message
                        ViewBag.CurrentPart = 1;
                        ViewData["ErrorMessage"] = "Not all vaccinations have been checked or they expire before the end of the reservation.";
                        return View(res);
                    }
                }
            } else {
                ViewBag.CurrentPart = 1;
            }
            if (res == null) {
                return RedirectToAction("EmployeeHome", "Hvkuser");
            }
            return View(res);
        }
        [HttpPost]
        [Authorize(Roles = "employee")]
        public IActionResult PetVaccination(int petId, int resId, List<bool> VaccinationChecked, List<int> VaccinationId, List<DateTime> ExpiryDate) {
            var petVaccinations = _context.PetVaccinations.Where(pv => pv.PetId == petId).ToList();
            for (int i = 0; i < VaccinationChecked.Count; i++) {
                var existingPetVaccination = petVaccinations.FirstOrDefault(pv => pv.VaccinationId == VaccinationId[i]);

                if (existingPetVaccination != null) {
                    var a = "";

                    existingPetVaccination.ExpiryDate = ExpiryDate[i];
                    existingPetVaccination.VaccinationChecked = VaccinationChecked[i];
                    _context.Update(existingPetVaccination);
                } else {

                    var newPetVaccination = new PetVaccination {
                        ExpiryDate = ExpiryDate[i],
                        VaccinationId = VaccinationId[i],
                        PetId = petId,
                        VaccinationChecked = VaccinationChecked[i],
                    };

                    _context.PetVaccinations.Add(newPetVaccination);
                }
            }

            _context.SaveChanges();
            return RedirectToAction("StartPetVisit", "Reservation", new { id = resId });
        }
        [HttpPost]
        [Authorize(Roles = "employee")]
        public IActionResult ReservationAndDiscount(int resId, List<string> serv, List<int> PetReserIds) {
            var serviceIds = new List<int>();//all servece Id receves by the form
            var petResIds = new List<int>();//all pet reservation id receved by the form
            var uPetResIds = new HashSet<int>();// unique pet reservation id/each pet.
            var ser = _context.Services
         .Where(s => s.ServiceDescription.ToLower() != "boarding" && s.ServiceDescription.ToLower() != "medication").ToList();//all services that are not boarding or medication
            foreach (var prs in PetReserIds) {
                uPetResIds.Add(prs);
            }
            foreach (var s in serv) {
                // Split the string by comma to get servId and petReservationId
                var parts = s.Split(',');
                if (parts.Length == 2) {

                    if (int.TryParse(parts[0], out var servId)) {
                        serviceIds.Add(servId);
                    }
                    if (int.TryParse(parts[1], out var petId)) {
                        petResIds.Add(petId);
                    }
                }
            }
            //loop trougth each pet to check how many serveces they have
            foreach (var upri in uPetResIds) {
                var serInDat = new List<PetReservationService>(); // all petReservationService for this pet in the database 
                var serInForm = new List<int>();// all petReservationService for this pet sent by the form
                for (int i = 0; i < serv.Count(); i++) {
                    if (upri == petResIds[i]) {
                        serInForm.Add(serviceIds[i]);
                    }
                }
                serInDat = _context.PetReservationServices
                .Where(prs => prs.PetReservationId == upri)
                .ToList();

                foreach (var serviceId in serInForm) {
                    var existingService = serInDat.FirstOrDefault(prs => prs.ServiceId == serviceId);
                    if (existingService == null) {

                        _context.PetReservationServices.Add(new PetReservationService
                        {
                            PetReservationId = upri,
                            ServiceId = serviceId,

                        });
                    }
                }

                foreach (var prs in serInDat) {
                    if (!serInForm.Contains(prs.ServiceId)) {
                        // Service from the database is not in the form, delete it
                        _context.PetReservationServices.Remove(prs);
                    }
                }
            }
            _context.SaveChanges();

            return RedirectToAction("StartPetVisit", "Reservation", new { id = resId, curp = 3 });
        }
        public IActionResult Start(int id) {
            var reservation = _context.Reservations.FirstOrDefault(r => r.ReservationId == id);
            reservation.Status = 3;
            _context.SaveChanges();
            return RedirectToAction("EmployeeHome", "Hvkuser");
        }

        [Authorize(Roles = "employee")]
        [Route("Reservation/EndPetVisit/{reservationId}")]
        public async Task<IActionResult> EndPetVisit(int reservationId) {
            HttpContext.Session.SetInt32("CurrentReservationId", reservationId);
            var reservation = await _context.Reservations
                .Include(r => r.PetReservations)
                    .ThenInclude(pr => pr.Pet)
                .Include(r => r.PetReservations)
                    .ThenInclude(pr => pr.PetReservationServices)
                        .ThenInclude(prs => prs.Service)
                            .ThenInclude(s => s.DailyRates)
                .Include(r => r.PetReservations)
                    .ThenInclude(pr => pr.PetReservationDiscounts)
                        .ThenInclude(prd => prd.Discount)
                .FirstOrDefaultAsync(r => r.ReservationId == reservationId);

            if (reservation == null) {
                return NotFound();
            }

            ViewBag.ReservationId = reservationId;
            ViewBag.ReservationStart = reservation.StartDate;
            ViewBag.ReservationEnd = reservation.EndDate;
            ViewBag.Days = (reservation.EndDate - reservation.StartDate).Days;

            var Services = new List<dynamic>();
            decimal subtotal = 0;
            const int boardingServiceId = 1;
            const int multiplePetsDiscountId = 2;
            int numBoarding = 0;

            foreach (var petRes in reservation.PetReservations) {
                decimal boardingTotal = 0;
                bool hasBoarding = false;

                foreach (var service in petRes.PetReservationServices) {
                    var rate = service.Service.DailyRates.FirstOrDefault(dr => dr.DogSize == petRes.Pet.DogSize);
                    if (rate != null) {
                        var total = rate.Rate * ViewBag.Days;
                        Services.Add(new {
                            Description = $"{service.Service.ServiceDescription} – {petRes.Pet.Name}",
                            DailyRate = rate.Rate,
                            Days = ViewBag.Days,
                            ItemTotal = total
                        });
                        subtotal += total;
                        if (service.ServiceId == boardingServiceId) {
                            hasBoarding = true;
                            boardingTotal = total;
                            numBoarding += 1;
                        }
                    }
                }

                if (!hasBoarding) {
                    var boardingService = await _context.Services
                        .Include(s => s.DailyRates)
                        .FirstOrDefaultAsync(s => s.ServiceId == boardingServiceId);
                    var rate = boardingService?.DailyRates.FirstOrDefault(dr => dr.DogSize == petRes.Pet.DogSize);
                    if (rate != null) {
                        boardingTotal = rate.Rate * ViewBag.Days;
                        Services.Add(new {
                            Description = $"{boardingService.ServiceDescription} – {petRes.Pet.Name}",
                            DailyRate = rate.Rate,
                            Days = ViewBag.Days,
                            ItemTotal = boardingTotal
                        });
                        numBoarding += 1;
                        subtotal += boardingTotal;
                    }
                }

                foreach (var discount in petRes.PetReservationDiscounts) {
                    if (discount.DiscountId != multiplePetsDiscountId) {
                        var discountAmount = boardingTotal * discount.Discount.Percentage;
                        Services.Add(new {
                            Description = $"{discount.Discount.Desciption} Discount ({(discount.Discount.Percentage * 100).ToString("F0")}%) – {petRes.Pet.Name}",
                            DailyRate = -(discountAmount) / ViewBag.Days,
                            Days = ViewBag.Days,
                            ItemTotal = -discountAmount
                        });
                        subtotal -= discountAmount;
                    }
                }
            }

            ViewBag.Services = Services;
            ViewBag.Subtotal = subtotal;
            ViewBag.MultiplePets = numBoarding >= 3 ? subtotal * 0.07m : 0.00m;
            ViewBag.SubtotalAfter = subtotal - ViewBag.MultiplePets;
            ViewBag.Tax = ViewBag.SubtotalAfter * 0.13m;
            ViewBag.Total = ViewBag.SubtotalAfter + ViewBag.Tax;

            return View();
        }

        [HttpPost]
        [Route("Reservation/CompleteReservation/{reservationId}")]
        public async Task<IActionResult> CompleteReservation(int reservationId) {
            var reservation = await _context.Reservations
                .Include(r => r.PetReservations)
                    .ThenInclude(pr => pr.Run)
                .FirstOrDefaultAsync(r => r.ReservationId == reservationId);

            if (reservation == null) {
                return NotFound();
            }
            
                reservation.Status = 5;
                foreach (var petReservation in reservation.PetReservations) {
                    if (petReservation.Run != null) {
                        petReservation.Run.Status = 3; 
                    }
                }
                await _context.SaveChangesAsync();

            return RedirectToAction("EmployeeHome", "Hvkuser");
        }
    }
}
