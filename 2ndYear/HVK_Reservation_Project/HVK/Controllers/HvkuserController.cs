using HVK.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Http;

namespace HVK.Controllers
{
    public class HvkuserController : Controller
    {
        private readonly HVKW24_Team3Context _context;
        public HvkuserController(HVKW24_Team3Context context)
        {
            _context = context;
        }

        public IActionResult Index()
        {
            return View();
        }

        [Authorize(Roles = "customer")]
        public IActionResult CustomerHome()
        {
            int hvkuserId = (int)HttpContext.Session.GetInt32("userid");
            var customer = _context.Hvkusers
                                 .Include(u => u.Pets)
                                 .ThenInclude(p => p.PetReservations)
                                 .ThenInclude(pr => pr.Reservation)
                                  .FirstOrDefault(u => u.HvkuserId == hvkuserId);
            return View(customer);
        }
        [Authorize(Roles = "employee")]
        public IActionResult EmployeeHome()
        {
            int hvkuserId = (int)HttpContext.Session.GetInt32("userid");
            var employee = _context.Hvkusers.FirstOrDefault(u => u.HvkuserId == hvkuserId);
            var pets = _context.Pets
                            .Include(p => p.Hvkuser)
                            .Include(p => p.PetReservations)
                            .ThenInclude(pr => pr.Reservation)
                            .ToList();
            var viewModel = new EmployeeHomeViewModel
            {
                Employee = employee,
                Pets = pets
            };

            return View(viewModel);
        }

        [Authorize(Roles = "employee")]
        public IActionResult AllCustomers(string? search)
        {
            IQueryable<HVK.Models.Hvkuser> customerList = _context.Hvkusers.Where(u => u.UserType.ToLower() == "customer");
            if (!string.IsNullOrEmpty(search))
            {
                customerList = customerList.Where(u => u.LastName.Contains(search) || u.Email.Contains(search) || u.Phone.Contains(search) || u.CellPhone.Contains(search));
            }
            List<HVK.Models.Hvkuser> res = customerList.OrderBy(u => u.LastName).ToList();
            return View(res);
        }

        [Authorize(Roles = "employee,customer")]
        public IActionResult CustomerDetails(int id)
        {
            var customer = _context.Hvkusers
                            .Include(u => u.Pets)
                            .ThenInclude(p => p.PetReservations)
                            .ThenInclude(pr => pr.Reservation)
                            .FirstOrDefault(u => u.HvkuserId == id);
            if (customer != null)
                return View(customer);
            return RedirectToAction("Index", "Home");
        }
        public IActionResult ManageCustomer()
        {
            ViewBag.Type = HttpContext.Session.GetString("usertype").ToString();
            return View();
        }

        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null || _context.Hvkusers == null)
            {
                return NotFound();
            }

            var hvkuser = await _context.Hvkusers.FindAsync(id);
            if (hvkuser == null)
            {
                return NotFound();
            }
            return View(hvkuser);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> Edit(int id, [Bind("HvkuserId,FirstName,LastName,Email,Password,Street,City,Province,PostalCode,Phone,CellPhone,EmergencyContactFirstName,EmergencyContactLastName,EmergencyContactPhone,UserType")] Hvkuser hvkuser)
        {
            if (id != hvkuser.HvkuserId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(hvkuser);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!HvkuserExists(hvkuser.HvkuserId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                if (hvkuser.UserType.ToLower() == "employee")
                    return RedirectToAction("EmployeeHome");
                return RedirectToAction("CustomerHome");
            }
            return View(hvkuser);
        }

        private bool HvkuserExists(int id)
        {
            return (_context.Hvkusers?.Any(e => e.HvkuserId == id)).GetValueOrDefault();
        }

        [HttpGet]
        [Authorize(Roles = "employee,customer")]
        public IActionResult CreateCustomer()
        {
            TempData.Keep();
            return View();
        }
        [HttpPost]
        [Authorize(Roles = "employee,customer")]
        public async Task<IActionResult> CreateCustomer(Hvkuser user)
        {
            if (ModelState.IsValid)
            {
                _context.Add(user);
                await _context.SaveChangesAsync();
                if (HttpContext.Session.GetString("usertype").ToString() == "customer")
                    return RedirectToAction(nameof(CustomerHome));
                if (HttpContext.Session.GetString("usertype").ToString() == "employee")
                    return RedirectToAction(nameof(EmployeeHome));
            }
            return View(user);
        }


    }
}
