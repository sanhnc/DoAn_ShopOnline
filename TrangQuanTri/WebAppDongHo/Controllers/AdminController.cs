using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebAppDongHo.Models;

namespace WebAppDongHo.Controllers
{
    public class AdminController : Controller
    {
        ShopOnlineDataContext db = new ShopOnlineDataContext();
        // GET: Admin
        public ActionResult Index()
        {
            List<SanPham> list = db.SanPhams.ToList();
            return View(list);
        }
        public ActionResult HoaDon()
        {
            List<HoaDon> list = db.HoaDons.ToList();
            return View(list);
        }
        public ActionResult KhachHang()
        {
            List<KhachHangOnline> list = db.KhachHangOnlines.ToList();
            return View(list);
        }
        public ActionResult TaiKhoan()
        {
            List<TaiKhoan> list = db.TaiKhoans.ToList();
            return View(list);
        }
        public ActionResult ChiTietHoaDon(string mahd)
        {
            string ms = mahd;
            var sp = from tt in db.ChiTietHoaDons.Where(m => m.MaHD == mahd) select tt;
            return View(sp);
        }
        public ActionResult LoaiSP()
        {
            List<LoaiSP> list = db.LoaiSPs.ToList();
            return View(list);
        }
        
        public ActionResult ThemSanPham()
        {
            ViewBag.LoaiSPs = db.LoaiSPs.ToList();
            return View();
        }
        [HttpPost, ValidateInput(false)]
        public ActionResult XLThemSanPham(SanPham sp)
        {
            ViewBag.LoaiSPs = new SelectList(db.LoaiSPs.ToList(), "MaLoaiSP", "TenLoaiSP");
            
                db.SanPhams.InsertOnSubmit(sp);
                db.SubmitChanges();
                return RedirectToAction("index", "admin");
            
        }
        public ActionResult SuaSanPham(string msp)
        {
            string a = msp;
            var sp = db.SanPhams.SingleOrDefault(t => t.MaSP == msp);
            ViewBag.LoaiSPs = new SelectList(db.LoaiSPs.ToList(), "MaLoaiSP", "TenLoaiSP", sp.MaLoaiSP);
            return View(sp);
        }

      
        [HttpPost]
        public ActionResult SuaSanPham(string msp, FormCollection collection)
        {
            string a = msp;
            var sp = db.SanPhams.SingleOrDefault(t => t.MaSP ==  msp);
            ViewBag.LoaiSPs = new SelectList(db.SanPhams.ToList(), "MaLoaiSP", "TenLoaiSP");
            var tenphim = collection["TenSP"];
            var tenphim1 = collection["DonViTinh"];
            var tenphim2 = collection["SoLuong"];
            var tenphim3 = collection["GiaBan"];
            var tenphim4 = collection["GiaVon"];
            var tenphim5 = collection["GiaNhapCuoi"];
            var tenphim6 = collection["MucTon"];
            var tenphim7 = collection["TrangThai"];
            var tenphim8 = collection["HinhAnh"];
            sp.TenSP = tenphim;
            sp.DonViTinh = tenphim1;
            sp.SoLuong = int.Parse(tenphim2);
            sp.GiaBan = int.Parse(tenphim3);
            sp.GiaVon = int.Parse(tenphim4);
            sp.GiaNhapCuoi = int.Parse(tenphim5);
            sp.MucTon = int.Parse(tenphim6);
            sp.TrangThai = int.Parse(tenphim7);
            sp.HinhAnh = tenphim8;
            UpdateModel(sp);
            db.SubmitChanges();
            return RedirectToAction("index", "Admin");

        }
      
        public ActionResult XoaSanPham(string msp)
        {
            string a = msp;
            SanPham sp = db.SanPhams.SingleOrDefault(t => t.MaSP == msp);
            if (sp == null)
            {
                Response.SubStatusCode = 404;
                return null;
            }
            db.SanPhams.DeleteOnSubmit(sp);
            db.SubmitChanges();
            return RedirectToAction("index", "Admin");
        }
        public ActionResult SuaHoaDon(string mhd)
        {
            HoaDon hd = db.HoaDons.FirstOrDefault(t => t.MaHD == mhd);
            return View(hd);
        }
        [HttpPost]
        public ActionResult XLSuaHoaDon(HoaDon hd)
        {
            var trangthai = hd.TrangThai;
            UpdateModel(hd);
            db.SubmitChanges();
            return RedirectToAction("HoaDon","Admin"); 
        }

        //[HttpPost, ValidateAntiForgeryToken]
        //public ActionResult XLSuaHoaDon(HoaDon hd)
        //{


        //    //ViewBag.KhachHangOnlines = new SelectList(db.KhachHangOnlines.ToList(), "MaKH", "HoTen");
        //    ViewBag.message = "Đã cập nhật trạng thái" + hd.TrangThai + "";
        //    UpdateModel(hd);
        //    db.SubmitChanges();
        //    return View();

        //}
    }
}