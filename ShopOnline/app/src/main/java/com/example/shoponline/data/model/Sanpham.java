package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by muase on 4/12/2018.
 */

public class Sanpham implements Serializable{
    @SerializedName("maSp")
    @Expose
    private String maSp;
    @SerializedName("maLoaiSp")
    @Expose
    private String maLoaiSp;
    @SerializedName("tenSp")
    @Expose
    private String tenSp;
    @SerializedName("donViTinh")
    @Expose
    private String donViTinh;
    @SerializedName("soLuong")
    @Expose
    private Integer soLuong;
    @SerializedName("giaBan")
    @Expose
    private Integer giaBan;
    @SerializedName("giaVon")
    @Expose
    private Integer giaVon;
    @SerializedName("giaNhapCuoi")
    @Expose
    private Integer giaNhapCuoi;
    @SerializedName("mucTon")
    @Expose
    private Integer mucTon;
    @SerializedName("trangThai")
    @Expose
    private Integer trangThai;
    @SerializedName("hinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("moTa")
    @Expose
    private String moTa;
    @SerializedName("maLoaiSpNavigation")
    @Expose
    private Object maLoaiSpNavigation;
    @SerializedName("chiTietHoaDon")
    @Expose
    private List<Object> chiTietHoaDon = null;

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getMaLoaiSp() {
        return maLoaiSp;
    }

    public void setMaLoaiSp(String maLoaiSp) {
        this.maLoaiSp = maLoaiSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getGiaVon() {
        return giaVon;
    }

    public void setGiaVon(Integer giaVon) {
        this.giaVon = giaVon;
    }

    public Integer getGiaNhapCuoi() {
        return giaNhapCuoi;
    }

    public void setGiaNhapCuoi(Integer giaNhapCuoi) {
        this.giaNhapCuoi = giaNhapCuoi;
    }

    public Integer getMucTon() {
        return mucTon;
    }

    public void setMucTon(Integer mucTon) {
        this.mucTon = mucTon;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Object getMaLoaiSpNavigation() {
        return maLoaiSpNavigation;
    }

    public void setMaLoaiSpNavigation(Object maLoaiSpNavigation) {
        this.maLoaiSpNavigation = maLoaiSpNavigation;
    }

    public List<Object> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(List<Object> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }

}