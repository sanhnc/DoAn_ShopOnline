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
    public class ChiTietHoaDonsController : ControllerBase
    {
        private readonly ShopOnlineContext _context;

        public ChiTietHoaDonsController(ShopOnlineContext context)
        {
            _context = context;
        }

        // GET: api/ChiTietHoaDons
        [HttpGet]
        public async Task<ActionResult<IEnumerable<ChiTietHoaDon>>> GetChiTietHoaDon()
        {
            return await _context.ChiTietHoaDon.ToListAsync();
        }

        // GET: api/ChiTietHoaDons/5
        [HttpGet("{id}")]
        public async Task<ActionResult<IEnumerable<ChiTietHoaDon>>> GetChiTietHoaDon(string id)
        {
            //var chiTietHoaDon = await _context.ChiTietHoaDon.Where(p => p.MaHd == id).ToListAsync();

            var chiTietHoaDon = await (from gk in _context.ChiTietHoaDon
                                          join gl in _context.SanPham on gk.MaSp equals gl.MaSp
                                          where gk.MaHd == id
                                          select new
                                          {
                                              gk.MaHd,
                                              gk.MaSp,
                                             gl.TenSp,
                                             gk.SoLuong,
                                             gk.DonGia,
                                             gl.HinhAnh
                                          }).ToListAsync();
            //if (chiTietHoaDon == null)
            //{
            //    return NotFound();
            //}

            return Ok(chiTietHoaDon);
        }

        // PUT: api/ChiTietHoaDons/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutChiTietHoaDon(string id, ChiTietHoaDon chiTietHoaDon)
        {
            if (id != chiTietHoaDon.MaHd)
            {
                return BadRequest();
            }

            _context.Entry(chiTietHoaDon).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ChiTietHoaDonExists(id))
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

        // POST: api/ChiTietHoaDons
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<ChiTietHoaDon>> PostChiTietHoaDon(ChiTietHoaDon chiTietHoaDon)
        {
            _context.ChiTietHoaDon.Add(chiTietHoaDon);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (ChiTietHoaDonExists(chiTietHoaDon.MaHd))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetChiTietHoaDon", new { id = chiTietHoaDon.MaHd }, chiTietHoaDon);
        }

        // DELETE: api/ChiTietHoaDons/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<ChiTietHoaDon>> DeleteChiTietHoaDon(string id)
        {
            var chiTietHoaDon = await _context.ChiTietHoaDon.FindAsync(id);
            if (chiTietHoaDon == null)
            {
                return NotFound();
            }

            _context.ChiTietHoaDon.Remove(chiTietHoaDon);
            await _context.SaveChangesAsync();

            return chiTietHoaDon;
        }

        private bool ChiTietHoaDonExists(string id)
        {
            return _context.ChiTietHoaDon.Any(e => e.MaHd == id);
        }
    }
}
