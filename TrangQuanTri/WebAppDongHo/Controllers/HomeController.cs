using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebAppDongHo.Models;
using PagedList.Mvc;
using PagedList;

namespace WebAppDongHo.Controllers
{
    public class HomeController : Controller
    {
        ShopOnlineDataContext db = new ShopOnlineDataContext();
        public ActionResult Index(int? page)
        {
            int pageSize = 16;
            int pageNum = (page ?? 1);
            return View(db.SanPhams.ToPagedList(pageNum, pageSize));
        }
        //public ActionResult HienThiNhom()
        //{
        //    return PartialView(db.Nhoms.ToList());
        //}
        //public ActionResult HienThiLoaiTheoNhom(int mn)
        //{
        //    List<Loai> list = db.Loais.Where(t => t.MaNhom == mn).ToList();
        //    return PartialView(list);
        //}
        //public ActionResult HienThiSanPhamTheoLoai(int ml)
        //{
        //    Loai l = db.Loais.FirstOrDefault(t => t.MaLoai == ml);
        //    ViewBag.TenLoai = l.ThuongHieu;
        //    List<SanPham> list = db.SanPhams.Where(t => t.MaLoai == ml).ToList();
        //    return View(list);
        //}
        //public ActionResult ChiTietSP(int msp)
        //{
        //    SanPham sp = db.SanPhams.FirstOrDefault(t => t.MaSP == msp);
        //    Loai l = db.Loais.FirstOrDefault(t => t.MaLoai == sp.MaLoai);
        //    ViewBag.TenLoai = l.ThuongHieu;
        //    return View(sp);
        //}
        //public ActionResult SPLienQuan(int msp)
        //{
        //    SanPham sp = db.SanPhams.FirstOrDefault(t => t.MaSP == msp);
        //    List<SanPham> list = db.SanPhams.Where(t => t.MaSP != sp.MaSP && t.MaLoai == sp.MaLoai).ToList();
        //    return PartialView(list);
        //}
        //public ActionResult TimTenLoai(int maloai)
        //{
        //    Loai l = db.Loais.FirstOrDefault(t => t.MaLoai == maloai);
        //    return PartialView(l);
        //}
        //public ActionResult Search(string term)
        //{
        //    return Json(sanpham.SearchSanPham(term), JsonRequestBehavior.AllowGet);
        //}
    }
}