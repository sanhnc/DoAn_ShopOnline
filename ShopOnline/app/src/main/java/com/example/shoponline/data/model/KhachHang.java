package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muase on 4/16/2018.
 */

public class KhachHang {
    @SerializedName("maKh")
    @Expose
    private Integer maKh;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("matKhau")
    @Expose
    private String matKhau;
    @SerializedName("hoTen")
    @Expose
    private String hoTen;
    @SerializedName("diaChi")
    @Expose
    private String diaChi;
    @SerializedName("sdt")
    @Expose
    private String sdt;
    @SerializedName("hoaDon")
    @Expose
    private List<Object> hoaDon = null;

    public Integer getMaKh() {
        return maKh;
    }

    public void setMaKh(Integer maKh) {
        this.maKh = maKh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public List<Object> getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(List<Object> hoaDon) {
        this.hoaDon = hoaDon;
    }

}