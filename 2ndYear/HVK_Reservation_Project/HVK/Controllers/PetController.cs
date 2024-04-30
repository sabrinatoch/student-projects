using HVK.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;

namespace HVK.Controllers {
    public class PetController : Controller {
        private readonly HVKW24_Team3Context _context;
        public PetController(HVKW24_Team3Context context) {
            _context = context;
        }
        // GET: Pets
        public async Task<IActionResult> Index() {
            return View();
        }

        [Authorize(Roles = "employee,customer")]
        public IActionResult PetDetails(int id) {
            ViewBag.type = HttpContext.Session.GetString("usertype").ToString();
            var pet = _context.Pets
                              .Include(p => p.PetVaccinations)
                              .ThenInclude(pv => pv.Vaccination)
                              .FirstOrDefault(p => p.PetId == id);
            TempData.Keep();
            if (pet != null) {
                return View(pet);
            }
            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        [Authorize(Roles = "employee,customer")]
        public IActionResult Create(int id) {
            HttpContext.Session.SetInt32("createuserid", id);
            ViewBag.VaccinationNames = new[] { "Bordetella", "Distemper", "Hepatitis", "Parainfluenza", "Parovirus", "Rabies" };

            var viewModel = new PetVaccinationViewModel {
                Pet = new Pet(),
                VaccinationExpiryDates = Enumerable.Range(1, 6).ToDictionary(x => x, x => (DateTime?)null)
            };
            return View(viewModel);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> Create(PetVaccinationViewModel viewModel) {
            var pet = viewModel.Pet;

            pet.Hvkuser = _context.Hvkusers.FirstOrDefault(u => u.HvkuserId == pet.HvkuserId);

            ModelState.Remove("Pet.Hvkuser");
            TempData.Keep("PetUserId");

            if (ModelState.IsValid) {
                _context.Add(pet);
                await _context.SaveChangesAsync();

                foreach (var vacc in viewModel.VaccinationExpiryDates) {
                    if (vacc.Value.HasValue) {
                        var petVaccination = new PetVaccination {
                            PetId = pet.PetId,
                            VaccinationId = vacc.Key,
                            ExpiryDate = vacc.Value.Value
                        };
                        _context.PetVaccinations.Add(petVaccination);
                    }
                }

                await _context.SaveChangesAsync();

                if (HttpContext.Session.GetString("usertype").ToLower() == "employee") {
                    return RedirectToAction("CustomerDetails", "Hvkuser", new { id = pet.HvkuserId });
                }
                return RedirectToAction("CustomerHome", "Hvkuser");
            }

            ViewBag.VaccinationNames = new[] { "Bordetella", "Distemper", "Hepatitis", "Parainfluenza", "Parovirus", "Rabies" };
            return View(viewModel);
        }

        // GET: Pets/Edit/5
        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> Edit(int? id) {
            ViewBag.type = HttpContext.Session.GetString("usertype").ToString();
            if (id == null || _context.Pets == null) {
                return NotFound();
            }

            var pet = _context.Pets.Include(p => p.PetVaccinations).FirstOrDefault(p => p.PetId == id);
            if (pet == null) {
                return NotFound();
            }

            var viewModel = new PetVaccinationViewModel {
                Pet = pet,
                VaccinationExpiryDates = Enumerable.Range(1, 6).ToDictionary(
                id => id,
                id => pet.PetVaccinations.FirstOrDefault(pv => pv.VaccinationId == id)?.ExpiryDate
            )
            };

            ViewBag.VaccinationNames = new[] { "Bordetella", "Distemper", "Hepatitis", "Parainfluenza", "Parovirus", "Rabies" };
            return View(viewModel);
        }

        // POST: Pets/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> Edit(int id, PetVaccinationViewModel viewModel) {
            if (id != viewModel.Pet.PetId) {
                return NotFound();
            }

            viewModel.Pet.Hvkuser = _context.Hvkusers.FirstOrDefault(u => u.HvkuserId == viewModel.Pet.HvkuserId);
            ModelState.Remove("Pet.Hvkuser");

            if (ModelState.IsValid) {
                try {
                    _context.Update(viewModel.Pet);
                    await _context.SaveChangesAsync();
                    foreach (var vacc in viewModel.VaccinationExpiryDates) {
                        var petVaccination = _context.PetVaccinations.FirstOrDefault(pv => pv.PetId == viewModel.Pet.PetId && pv.VaccinationId == vacc.Key);
                        if (petVaccination != null) {
                            if (vacc.Value.HasValue) {
                                petVaccination.ExpiryDate = vacc.Value.Value;
                                _context.Update(petVaccination);
                            } else {
                                _context.Remove(petVaccination);
                            }
                        } else if (vacc.Value.HasValue) {
                            petVaccination = new PetVaccination {
                                PetId = viewModel.Pet.PetId,
                                VaccinationId = vacc.Key,
                                ExpiryDate = vacc.Value.Value
                            };
                            _context.Add(petVaccination);
                        }
                    }

                    await _context.SaveChangesAsync();
                } catch (DbUpdateConcurrencyException) {
                    if (!PetExists(viewModel.Pet.PetId)) {
                        return NotFound();
                    } else {
                        throw;
                    }
                }

                var tempPet = _context.Pets.FirstOrDefault(p => p.PetId == id);
                var hvkUser = _context.Hvkusers.FirstOrDefault(u => u.HvkuserId == tempPet.HvkuserId);
                if (HttpContext.Session.GetString("usertype").ToLower() == "employee") {
                    return RedirectToAction("PetDetails", "Pet", new { id = viewModel.Pet.PetId });
                }
                return RedirectToAction("PetDetails", "Pet", new { id = viewModel.Pet.PetId });
            }

            ViewBag.VaccinationNames = new[] { "Bordetella", "Distemper", "Hepatitis", "Parainfluenza", "Parovirus", "Rabies" };
            return View(viewModel);
        }

        private bool PetExists(int id) {
            return (_context.Pets?.Any(e => e.PetId == id)).GetValueOrDefault();
        }


        // GET: Pets/Delete/5
        public async Task<IActionResult> Delete(int? id) {
            if (id == null || _context.Pets == null) {
                return NotFound();
            }

            var pet = await _context.Pets
                .Include(p => p.Hvkuser)
                .FirstOrDefaultAsync(m => m.PetId == id);
            if (pet == null) {
                return NotFound();
            }

            return View(pet);
        }

        // POST: Pets/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id) {
            if (_context.Pets == null) {
                return Problem("Entity set 'HVKW24_Team3Context.Pets'  is null.");
            }
            var pet = await _context.Pets
                .Include(p => p.PetVaccinations)
                .FirstOrDefaultAsync(m => m.PetId == id);
            if (pet != null) {
                _context.PetVaccinations.RemoveRange(pet.PetVaccinations);

                _context.Pets.Remove(pet);

                await _context.SaveChangesAsync();
            }

            
            if (HttpContext.Session.GetString("usertype").ToLower() == "employee") {
                return RedirectToAction("CustomerDetails", "Hvkuser", new { id = pet.HvkuserId });
            }
            return RedirectToAction("CustomerHome", "Hvkuser");
        }

    }
}
