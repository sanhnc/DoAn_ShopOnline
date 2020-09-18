using System;
using System.Collections.Generic;

namespace WebAPI_BanHangOnline.Models
{
    public partial class TaiKhoan
    {
        public string TenDn { get; set; }
        public string MatKhau { get; set; }
        public bool? Quyen { get; set; }
    }
}
