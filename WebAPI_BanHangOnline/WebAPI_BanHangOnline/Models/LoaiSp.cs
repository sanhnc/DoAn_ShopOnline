using System;
using System.Collections.Generic;

namespace WebAPI_BanHangOnline.Models
{
    public partial class LoaiSp
    {
        public LoaiSp()
        {
            SanPham = new HashSet<SanPham>();
        }

        public string MaLoaiSp { get; set; }
        public string TenLoaiSp { get; set; }
        public string HinhLoai { get; set; }

        public virtual ICollection<SanPham> SanPham { get; set; }
    }
}
