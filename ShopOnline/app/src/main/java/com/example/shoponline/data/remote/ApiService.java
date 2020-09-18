package com.example.shoponline.data.remote;


import com.example.shoponline.data.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by muase on 3/14/2018.
 */

public interface ApiService {

    @GET("HoaDons/{id}")
    Call<List<HoaDon>> getHoaDon(@Path("id") int id);

    @GET("SanPhams")
    Call<List<Sanpham>> getSpMoinhat();

    @GET("LoaiSPs")
    Call<List<Loaisanpham>> getLoaiSp();

    @GET("KhachHangOnlines")
    Call<List<KhachHang>> getKhachhang();

    @GET("SanPhams/{id}")
    Call<List<Sanpham>> getSpTheoloai(@Path("id") String id);

    @GET("ChiTietHoaDons/{id}")
    Call<List<ChiTietHoaDon>> getCTHoaDon(@Path("id") String id);

    @POST("KhachHangOnlines")
    Call<KhachHang> insertThongtinkh(@Body KhachHang khachHang);

    @POST("HoaDons")
    Call<HoaDon> insertHoDon(@Body HoaDon hoaDon);

    @POST("ChiTietHoaDons")
    Call<ChiTietHoaDon> insertCTHoDon(@Body ChiTietHoaDon json);
}
