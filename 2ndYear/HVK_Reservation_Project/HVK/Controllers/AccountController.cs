using HVK.Models;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;
using System.Security.Claims;
using Microsoft.EntityFrameworkCore;

namespace HVK.Controllers {
    public class AccountController : Controller {
        private readonly HVKW24_Team3Context _context;

        public AccountController(HVKW24_Team3Context context) {
            _context = context;
        }

        [HttpGet]
        public IActionResult Login() {
            ViewData["username"] = "";
            ViewData["password"] = "";
            TempData["HvkuserType"] = "Customer";
            TempData.Remove("HvkuserId");
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(string username, string password) {
            if (username == null && password == null) {
                TempData["ErrorMessage"] = "Invalid username or password";
                return View();
            }
            var user = _context.Hvkusers.FirstOrDefault(u => u.Email == username);
            if (user == null || user.Password != password) {
                TempData["ErrorMessage"] = "Invalid username or password";
                ViewData["username"] = username;
                ViewData["password"] = password;
                return View();
            } else {
                var claims = new List<Claim> {
                    new Claim(ClaimTypes.Sid, user.HvkuserId.ToString()),
                    new Claim(ClaimTypes.Role, user.UserType.ToLower())
                };
                var claimsIdentity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);
                var authProperties = new AuthenticationProperties {
                    //IsPersistent = true;
                };
                await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, new ClaimsPrincipal(claimsIdentity), authProperties);
                HttpContext.Session.SetInt32("userid", user.HvkuserId);
                HttpContext.Session.SetString("usertype", user.UserType.ToLower());
                ViewBag.Type = user.UserType.ToLower();
                if (user.UserType == "Customer")
                    return RedirectToAction("CustomerHome", "Hvkuser");
                else if (user.UserType == "Employee")
                    return RedirectToAction("EmployeeHome", "Hvkuser");
            }
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error() {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
