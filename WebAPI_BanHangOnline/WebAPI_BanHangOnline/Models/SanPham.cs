using System;
using System.Collections.Generic;

namespace WebAPI_BanHangOnline.Models
{
    public partial class SanPham
    {
        public SanPham()
        {
            ChiTietHoaDon = new HashSet<ChiTietHoaDon>();
        }

        public string MaSp { get; set; }
        public string MaLoaiSp { get; set; }
        public string TenSp { get; set; }
        public string DonViTinh { get; set; }
        public int? SoLuong { get; set; }
        public int? GiaBan { get; set; }
        public int? GiaVon { get; set; }
        public int? GiaNhapCuoi { get; set; }
        public int? MucTon { get; set; }
        public int? TrangThai { get; set; }
        public string HinhAnh { get; set; }
        public string MoTa { get; set; }

        public virtual LoaiSp MaLoaiSpNavigation { get; set; }
        public virtual ICollection<ChiTietHoaDon> ChiTietHoaDon { get; set; }
    }
}
