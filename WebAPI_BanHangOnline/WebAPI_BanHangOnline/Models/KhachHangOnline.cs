using System;
using System.Collections.Generic;

namespace WebAPI_BanHangOnline.Models
{
    public partial class KhachHangOnline
    {
        public KhachHangOnline()
        {
            HoaDon = new HashSet<HoaDon>();
        }

        public int MaKh { get; set; }
        public string Email { get; set; }
        public string MatKhau { get; set; }
        public string HoTen { get; set; }
        public string DiaChi { get; set; }
        public string Sdt { get; set; }

        public virtual ICollection<HoaDon> HoaDon { get; set; }
    }
}
