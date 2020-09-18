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
    public class LoaiSpsController : ControllerBase
    {
        private readonly ShopOnlineContext _context;

        public LoaiSpsController(ShopOnlineContext context)
        {
            _context = context;
        }

        // GET: api/LoaiSps
        [HttpGet]
        public async Task<ActionResult<IEnumerable<LoaiSp>>> GetLoaiSp()
        {
            return await _context.LoaiSp.ToListAsync();
        }

        // GET: api/LoaiSps/5
        [HttpGet("{id}")]
        public async Task<ActionResult<LoaiSp>> GetLoaiSp(string id)
        {
            var loaiSp = await _context.LoaiSp.FindAsync(id);

            if (loaiSp == null)
            {
                return NotFound();
            }

            return loaiSp;
        }

        // PUT: api/LoaiSps/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutLoaiSp(string id, LoaiSp loaiSp)
        {
            if (id != loaiSp.MaLoaiSp)
            {
                return BadRequest();
            }

            _context.Entry(loaiSp).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LoaiSpExists(id))
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

        // POST: api/LoaiSps
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<LoaiSp>> PostLoaiSp(LoaiSp loaiSp)
        {
            _context.LoaiSp.Add(loaiSp);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (LoaiSpExists(loaiSp.MaLoaiSp))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetLoaiSp", new { id = loaiSp.MaLoaiSp }, loaiSp);
        }

        // DELETE: api/LoaiSps/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<LoaiSp>> DeleteLoaiSp(string id)
        {
            var loaiSp = await _context.LoaiSp.FindAsync(id);
            if (loaiSp == null)
            {
                return NotFound();
            }

            _context.LoaiSp.Remove(loaiSp);
            await _context.SaveChangesAsync();

            return loaiSp;
        }

        private bool LoaiSpExists(string id)
        {
            return _context.LoaiSp.Any(e => e.MaLoaiSp == id);
        }
    }
}
