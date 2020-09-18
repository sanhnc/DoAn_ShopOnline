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
    public class SanPhamsController : ControllerBase
    {
        private readonly ShopOnlineContext _context;

        public SanPhamsController(ShopOnlineContext context)
        {
            _context = context;
        }

        // GET: api/SanPhams
        [HttpGet]
        public async Task<ActionResult<IEnumerable<SanPham>>> GetSanPham()
        {
            return await _context.SanPham.OrderByDescending(p => p.MaSp).ToListAsync();
        }

        // GET: api/SanPhams/5
        [HttpGet("{id}")]
        public async Task<ActionResult<IEnumerable<SanPham>>> GetSanPham(string id)
        {
            var sanPham = await _context.SanPham.Where(p => p.MaLoaiSp == id).ToListAsync();

            if (sanPham == null)
            {
                return NotFound();
            }

            return sanPham;
        }

        // PUT: api/SanPhams/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutSanPham(string id, SanPham sanPham)
        {
            if (id != sanPham.MaSp)
            {
                return BadRequest();
            }

            _context.Entry(sanPham).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SanPhamExists(id))
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

        // POST: api/SanPhams
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<SanPham>> PostSanPham(SanPham sanPham)
        {
            _context.SanPham.Add(sanPham);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (SanPhamExists(sanPham.MaSp))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetSanPham", new { id = sanPham.MaSp }, sanPham);
        }

        // DELETE: api/SanPhams/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<SanPham>> DeleteSanPham(string id)
        {
            var sanPham = await _context.SanPham.FindAsync(id);
            if (sanPham == null)
            {
                return NotFound();
            }

            _context.SanPham.Remove(sanPham);
            await _context.SaveChangesAsync();

            return sanPham;
        }

        private bool SanPhamExists(string id)
        {
            return _context.SanPham.Any(e => e.MaSp == id);
        }
    }
}
