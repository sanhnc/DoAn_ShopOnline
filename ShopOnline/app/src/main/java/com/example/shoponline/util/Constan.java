package com.example.shoponline.util;

import android.content.SharedPreferences;

import com.example.shoponline.R;
import com.example.shoponline.model.ItemTK;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muase on 4/12/2018.
 */

public class Constan {
    public static final String data = "data";
    public static final String SANPHAM = "sanpham";
    public static final String LOAI_SANPHAM = "loaisanpham";
    public static final String DONHANG = "donhang";
    public static final String CHITIET_DONHANG = "chitietdonhang";
    public static final String THONGTIN_SP = "thongtinsanpham";

    public static final String NEWHOST = "newhost";

    public static final String KEY_PHI_VC = "phivanchuyen";
    public static final String KEY_TONGTIEN = "tongtien";

    public static final int REQUEST_CODE_GIOHANG = 123;
    public static final int RESULT_CODE_GIOHANG = 124;

    public static final int REQUEST_CODE_DANHNHAP = 100;
    public static final int RESULT_CODE_DANHNHAP = 99;

    public static final int REQUEST_CODE_DATHANG = 101;
    public static final int RESULT_CODE_DATHANG = 102;

    public static final int REQUEST_CODE_QUENMK = 101;
    public static final int RESULT_CODE_QUENMK = 102;

    public static final int REQUEST_CODE_CAIDAT = 109;
    public static final int RESULT_CODE_CAIDAT = 110;

    public static final int REQUEST_CODE_GIOHANG_DANGNHAP = 151;
    public static final int RESULT_CODE_GIOHANG_DANGNHAP = 152;
    public static final String KEY_GIOHANG_DANGNHAP = "giohang_dangnhap";


    //public static String localhost = "192.168.100.10";
//    public static String localhost = "192.168.0.38";
    public static String localhost = "192.168.5.102";
    public static final String API_URL = "http://" + localhost + ":5000/api/";

//    //Chức năng trong tài khoản
//    public static List<ItemTK> getListChucnang() {
//        List<ItemTK> list = new ArrayList<>();
//        list.add(new ItemTK(R.drawable.ic_tk_tinnhan, "Tin nhắn"));
//        list.add(new ItemTK(R.drawable.ic_tk_donhangcuatoi, "Đơn hàng của tôi"));
//        return list;
//    }
//    //Chức năng trong tài khoản
//    public static List<ItemTK> getListCaidat() {
//        List<ItemTK> list = new ArrayList<>();
//        list.add(new ItemTK(R.drawable.ic_tk_caidat, "Cài đặt"));
//        list.add(new ItemTK(R.drawable.ic_tk_chinhsach, "Chính sách"));
//        list.add(new ItemTK(R.drawable.ic_tk_trogiup, "Trợ giúp"));
//        return list;
//    }
//
//    public static List<String> getQuangcao() {
//        ArrayList<String> mangquangcao = new ArrayList<>();
//        mangquangcao.add("https://2.bp.blogspot.com/-bGCxRtnRmWI/WjjEkfTg54I/AAAAAAAAESs/KsesnBvqSWYdRU7JuOueJy8AR98AFvWlgCLcBGAs/s1600/shopeemall.PNG");
//        mangquangcao.add("http://media.giadinhvietnam.com/files/ankhe1980/2017/11/06/1111--1212-shopee-super-sale_600x299-1452.jpg");
//        mangquangcao.add("http://static.dientutieudung.vn/img/2017/10/banner-shopee-mall-1507263685.jpg");
//        mangquangcao.add("http://thelevel.my/wp-content/uploads/2017/04/Shopee-Lowest-Price-Image-B.jpg");
//        mangquangcao.add("https://khuyenmaimoingay.info/wp-content/uploads/2017/04/%C4%90%E1%BA%A1i-ti%E1%BB%87c-Xiaomi-tr%C3%AAn-Shopee-696x261.jpg");
//        return mangquangcao;
//    }
}
