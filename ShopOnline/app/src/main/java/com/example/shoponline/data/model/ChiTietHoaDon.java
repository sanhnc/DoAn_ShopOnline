package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChiTietHoaDon {
    @SerializedName("maHd")
    @Expose
    private String maHd;
    @SerializedName("maSp")
    @Expose
    private String maSp;
    @SerializedName("tenSp")
    @Expose
    private String tenSp;
    @SerializedName("soLuong")
    @Expose
    private Integer soLuong;
    @SerializedName("donGia")
    @Expose
    private Integer donGia;
    @SerializedName("hinhAnh")
    @Expose
    private String hinhAnh;

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

}