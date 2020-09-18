using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebAPI_BanHangOnline.Models;

namespace WebAPI_BanHangOnline.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class HoaDonsController : ControllerBase
    {
        private readonly ShopOnlineContext _context;

        public HoaDonsController(ShopOnlineContext context)
        {
            _context = context;
        }

        // GET: api/HoaDons
        [HttpGet]
        public async Task<ActionResult<IEnumerable<HoaDon>>> GetHoaDon()
        {
            return await _context.HoaDon.ToListAsync();
        }

        // GET: api/HoaDons/5
        [HttpGet("{id}")]
        public async Task<ActionResult<IEnumerable<HoaDon>>> GetHoaDon(int id)
        {
            var hoaDon = await _context.HoaDon.Where(p => p.MaKh == id).ToListAsync();

            if (hoaDon == null)
            {
                return NotFound();
            }

            return hoaDon;
        }

        // PUT: api/HoaDons/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutHoaDon(string id, HoaDon hoaDon)
        {
            if (id != hoaDon.MaHd)
            {
                return BadRequest();
            }

            _context.Entry(hoaDon).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!HoaDonExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/HoaDons
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<HoaDon>> PostHoaDon(HoaDon hoaDon)
        {
            _context.HoaDon.Add(hoaDon);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (HoaDonExists(hoaDon.MaHd))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetHoaDon", new { id = hoaDon.MaHd }, hoaDon);
        }

        // DELETE: api/HoaDons/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<HoaDon>> DeleteHoaDon(string id)
        {
            var hoaDon = await _context.HoaDon.FindAsync(id);
            if (hoaDon == null)
            {
                return NotFound();
            }

            _context.HoaDon.Remove(hoaDon);
            await _context.SaveChangesAsync();

            return hoaDon;
        }

        private bool HoaDonExists(string id)
        {
            return _context.HoaDon.Any(e => e.MaHd == id);
        }
    }
}
