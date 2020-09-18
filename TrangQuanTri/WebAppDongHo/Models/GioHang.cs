using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAppDongHo.Models
{
    public class GioHang
    {
        QLDongHoDataContext db = new QLDongHoDataContext();
        public int MaSP { get; set; }
        public string TenSP { get; set; }
        public double Gia { get; set; }
        public string Hinh { get; set; }
        public int Soluong { get; set; }
        public string Size { get; set; }
        public int Maloai { get; set; }
        public double ThanhTien
        {
            get
            {
                return Soluong * Gia;
            }
        }
        public GioHang(int msp)
        {
            MaSP = msp;
            SanPham obj = db.SanPhams.Single(n => n.MaSP == MaSP);
            TenSP = obj.TenSP;
            Hinh = obj.HinhAnh;
            Gia = double.Parse(obj.GiaBan.ToString());
            Soluong = 1;
            Size = obj.Size;
            Maloai = obj.MaLoai;
        }
    }
}