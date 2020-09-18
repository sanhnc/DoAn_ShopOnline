package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HoaDon {
    @SerializedName("maHd")
    @Expose
    private String maHd;
    @SerializedName("ngayLap")
    @Expose
    private String ngayLap;
    @SerializedName("ngayGiao")
    @Expose
    private Object ngayGiao;
    @SerializedName("maKh")
    @Expose
    private Integer maKh;
    @SerializedName("diaChiGiao")
    @Expose
    private String diaChiGiao;
    @SerializedName("trangThai")
    @Expose
    private Boolean trangThai;
    @SerializedName("maKhNavigation")
    @Expose
    private Object maKhNavigation;
    @SerializedName("chiTietHoaDon")
    @Expose
    private List<Object> chiTietHoaDon = null;

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Object getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Object ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public Integer getMaKh() {
        return maKh;
    }

    public void setMaKh(Integer maKh) {
        this.maKh = maKh;
    }

    public String getDiaChiGiao() {
        return diaChiGiao;
    }

    public void setDiaChiGiao(String diaChiGiao) {
        this.diaChiGiao = diaChiGiao;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Object getMaKhNavigation() {
        return maKhNavigation;
    }

    public void setMaKhNavigation(Object maKhNavigation) {
        this.maKhNavigation = maKhNavigation;
    }

    public List<Object> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(List<Object> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }

}

