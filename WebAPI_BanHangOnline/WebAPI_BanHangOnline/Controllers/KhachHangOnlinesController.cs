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
    public class KhachHangOnlinesController : ControllerBase
    {
        private readonly ShopOnlineContext _context;

        public KhachHangOnlinesController(ShopOnlineContext context)
        {
            _context = context;
        }

        // GET: api/KhachHangOnlines
        [HttpGet]
        public async Task<ActionResult<IEnumerable<KhachHangOnline>>> GetKhachHangOnline()
        {
            return await _context.KhachHangOnline.ToListAsync();
        }

        // GET: api/KhachHangOnlines/5
        [HttpGet("{id}")]
        public async Task<ActionResult<KhachHangOnline>> GetKhachHangOnline(int id)
        {
            var khachHangOnline = await _context.KhachHangOnline.FindAsync(id);

            if (khachHangOnline == null)
            {
                return NotFound();
            }

            return khachHangOnline;
        }

        // PUT: api/KhachHangOnlines/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutKhachHangOnline(int id, KhachHangOnline khachHangOnline)
        {
            if (id != khachHangOnline.MaKh)
            {
                return BadRequest();
            }

            _context.Entry(khachHangOnline).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!KhachHangOnlineExists(id))
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

        // POST: api/KhachHangOnlines
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<KhachHangOnline>> PostKhachHangOnline(KhachHangOnline khachHangOnline)
        {
            _context.KhachHangOnline.Add(khachHangOnline);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetKhachHangOnline", new { id = khachHangOnline.MaKh }, khachHangOnline);
        }

        // DELETE: api/KhachHangOnlines/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<KhachHangOnline>> DeleteKhachHangOnline(int id)
        {
            var khachHangOnline = await _context.KhachHangOnline.FindAsync(id);
            if (khachHangOnline == null)
            {
                return NotFound();
            }

            _context.KhachHangOnline.Remove(khachHangOnline);
            await _context.SaveChangesAsync();

            return khachHangOnline;
        }

        private bool KhachHangOnlineExists(int id)
        {
            return _context.KhachHangOnline.Any(e => e.MaKh == id);
        }
    }
}
