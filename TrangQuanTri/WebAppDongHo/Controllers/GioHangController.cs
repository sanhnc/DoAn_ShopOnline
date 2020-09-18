using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebAppDongHo.Models;

namespace WebAppDongHo.Controllers
{
    public class GioHangController : Controller
    {
        QLDongHoDataContext db = new QLDongHoDataContext();
        //
        // GET: /GioHang/

        public ActionResult Index()
        {
            return View();
        }
        public List<GioHang> Laygiohang()
        {
            List<GioHang> lstgiohang = Session["Giohang"] as List<GioHang>;
            if (lstgiohang == null)
            {
                lstgiohang = new List<GioHang>();
                Session["Giohang"] = lstgiohang;
            }
            return lstgiohang;
        }

        public ActionResult Themgiohang(int msp)
        {
            List<GioHang> lstGiohang = Laygiohang();
            GioHang sp = lstGiohang.Find(n => n.MaSP == msp);
            if (sp == null)
            {
                sp = new GioHang(msp);
                lstGiohang.Add(sp);
            }
            else
            {
                sp.Soluong++;
            }
            return RedirectToAction("Xemgiohang");
        }
        public ActionResult Xemgiohang()
        {
            List<GioHang> lstgiohang = Laygiohang();
            if (lstgiohang.Count == 0)
            {
                return RedirectToAction("Index", "Home");
            }
            ViewBag.tongsoluong = tongsoluong();
            ViewBag.tongtien = tongtien();
            return View(lstgiohang);
        }
        private int tongsoluong()
        {
            int tong = 0;
            List<GioHang> lst = Session["Giohang"] as List<GioHang>;
            if (lst != null)
            {
                tong = lst.Sum(n => n.Soluong);
            }
            return tong;
        }
        private double tongtien()
        {
            double tong = 0;
            List<GioHang> lst = Session["Giohang"] as List<GioHang>;
            if (lst != null)
            {
                tong = lst.Sum(n => n.ThanhTien);
            }
            return tong;
        }
        public ActionResult Xoa1SPKhoiGio(int msp)
        {
            List<GioHang> lstGiohang = Laygiohang();
            GioHang sp = lstGiohang.Find(n => n.MaSP == msp);
            lstGiohang.Remove(sp);
            return RedirectToAction("Xemgiohang");
        }
        public ActionResult XoaAllGioHang()
        {
            List<GioHang> lstGiohang = Laygiohang();
            lstGiohang.RemoveRange(0, lstGiohang.Count);
            return RedirectToAction("Xemgiohang");
        }
        public ActionResult ThongTinDatHang()
        {
            TaiKhoan kh = (TaiKhoan)Session["TaiKhoan"];
            if (kh != null)
            {
                return View(kh);
            }
            else
            {
                return RedirectToAction("DatHang","GioHang");
            }
        }
        //
        public ActionResult DatHang()
        {
            return View();
        }
        [HttpPost]
        public ActionResult DatHang(FormCollection collection)
        {
            //Thêm Đơn hàng
            HoaDon hd = new HoaDon();
            TaiKhoan kh =(TaiKhoan)Session["TaiKhoan"];
            if(kh != null)
            {
                List<GioHang> gh = Laygiohang();
                hd.UserName = kh.UserName;
                hd.NgayDat = DateTime.Now;
                var ngaygiao = String.Format("{0:MM/dd/yyyy}", collection["ngaygiao"]);
                if(ngaygiao != "")
                {
                    hd.NgayGiao = DateTime.Parse(ngaygiao);
                }
                hd.TinhTrangGiaoHang = 0;
                hd.DaThanhToan = 0;
                db.HoaDons.InsertOnSubmit(hd);
                db.SubmitChanges();
                //Thêm chi tiết đơn hàng
                foreach (var item in gh)
                {
                    ChiTietHoaDon ct = new ChiTietHoaDon();
                    ct.MaHD = hd.MaHD;
                    ct.MaSP = item.MaSP;
                    ct.SoLuong = item.Soluong;
                    ct.DonGia = (int)item.Gia;
                    db.ChiTietHoaDons.InsertOnSubmit(ct);
                }
                db.SubmitChanges();
                Session["Giohang"] = null;
                return RedirectToAction("XacNhanDonHang", "GioHang");
            }
            else
            {
                return View();
            }
        }
        public ActionResult XacNhanDonHang()
        {
            return View();
        }
    }
}