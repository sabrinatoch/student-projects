using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using HVK.Models;

namespace HVK.Controllers
{
    public class customerController : Controller
    {
        private readonly HVKW24_Team3Context _context;

        public customerController(HVKW24_Team3Context context)
        {
            _context = context;
        }

        // GET: customer
        public async Task<IActionResult> Index()
        {
              return _context.Hvkusers != null ? 
                          View(await _context.Hvkusers.ToListAsync()) :
                          Problem("Entity set 'HVKW24_Team3Context.Hvkusers'  is null.");
        }

        // GET: customer/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null || _context.Hvkusers == null)
            {
                return NotFound();
            }

            var hvkuser = await _context.Hvkusers
                .FirstOrDefaultAsync(m => m.HvkuserId == id);
            if (hvkuser == null)
            {
                return NotFound();
            }

            return View(hvkuser);
        }

        // GET: customer/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: customer/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("HvkuserId,FirstName,LastName,Email,Password,Street,City,Province,PostalCode,Phone,CellPhone,EmergencyContactFirstName,EmergencyContactLastName,EmergencyContactPhone,UserType")] Hvkuser hvkuser)
        {
            if (ModelState.IsValid)
            {
                _context.Add(hvkuser);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(hvkuser);
        }

        // GET: customer/Edit/5
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

        // POST: customer/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
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
                return RedirectToAction(nameof(Index));
            }
            return View(hvkuser);
        }

        // GET: customer/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null || _context.Hvkusers == null)
            {
                return NotFound();
            }

            var hvkuser = await _context.Hvkusers
                .FirstOrDefaultAsync(m => m.HvkuserId == id);
            if (hvkuser == null)
            {
                return NotFound();
            }

            return View(hvkuser);
        }

        // POST: customer/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (_context.Hvkusers == null)
            {
                return Problem("Entity set 'HVKW24_Team3Context.Hvkusers'  is null.");
            }
            var hvkuser = await _context.Hvkusers.FindAsync(id);
            if (hvkuser != null)
            {
                _context.Hvkusers.Remove(hvkuser);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool HvkuserExists(int id)
        {
          return (_context.Hvkusers?.Any(e => e.HvkuserId == id)).GetValueOrDefault();
        }
    }
}
