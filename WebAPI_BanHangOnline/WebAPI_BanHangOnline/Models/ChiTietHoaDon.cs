using System;
using System.Collections.Generic;

namespace WebAPI_BanHangOnline.Models
{
    public partial class ChiTietHoaDon
    {
        public string MaHd { get; set; }
        public string MaSp { get; set; }
        public int? SoLuong { get; set; }
        public int? DonGia { get; set; }

        public virtual HoaDon MaHdNavigation { get; set; }
        public virtual SanPham MaSpNavigation { get; set; }
    }
}
