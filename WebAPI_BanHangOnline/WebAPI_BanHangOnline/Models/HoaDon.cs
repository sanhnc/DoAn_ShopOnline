using System;
using System.Collections.Generic;

namespace WebAPI_BanHangOnline.Models
{
    public partial class HoaDon
    {
        public HoaDon()
        {
            ChiTietHoaDon = new HashSet<ChiTietHoaDon>();
        }

        public string MaHd { get; set; }
        public string NgayLap { get; set; }
        public string NgayGiao { get; set; }
        public int? MaKh { get; set; }
        public string DiaChiGiao { get; set; }
        public bool? TrangThai { get; set; }

        public virtual KhachHangOnline MaKhNavigation { get; set; }
        public virtual ICollection<ChiTietHoaDon> ChiTietHoaDon { get; set; }
    }
}
