using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebAppDongHo.Models;

namespace WebAppDongHo.Controllers
{
    public class DangNhapController : Controller
    {
        ShopOnlineDataContext db = new ShopOnlineDataContext();
        // GET: DangNhap
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult DangKy()
        {
            return View();
        }
        [HttpPost]
        public ActionResult XLDangKy(FormCollection col, TaiKhoan tk)
        {
            tk.TenDN = col["Username"];
            tk.MatKhau = col["matkhau"];
            tk.Quyen = true;
            TaiKhoan x = db.TaiKhoans.FirstOrDefault(t => t.TenDN == tk.TenDN);
            if (x != null)
            {
                ViewBag.error = "UserName đã tồn tại, vui lòng nhập tên khác!!";
                return View("dangky");
            }

            db.TaiKhoans.InsertOnSubmit(tk);
            db.SubmitChanges();

            return RedirectToAction("DangNhap");
        }
        public ActionResult DangNhap()
        {
            return View();
        }
        [HttpPost]
        public ActionResult XLDangNhap(FormCollection col)
        {
            var username = col["Username"];
            var matkhau = col["matkhau"];
            TaiKhoan x = db.TaiKhoans.FirstOrDefault(t => t.TenDN == username);
            if (x == null)
            {
                ViewBag.error = "UserName không tồn tại!!!";
                return View("DangNhap");
            }
            
            if (x.MatKhau != matkhau)
            {
                ViewBag.error = "Mật khẩu không đúng, vui lòng nhập lại!!!";
                return View("DangNhap");
            }
            Session["TaiKhoan"] = x;
            return RedirectToAction("Index", "Admin");
        }
        public ActionResult DangXuat()
        {
            Session["TaiKhoan"] = null;
            return RedirectToAction("DangNhap", "DangNhap");
        }
    }
}