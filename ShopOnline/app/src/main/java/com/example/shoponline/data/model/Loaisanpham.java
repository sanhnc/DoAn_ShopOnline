package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muase on 4/12/2018.
 */

public class Loaisanpham {
    @SerializedName("maLoaiSp")
    @Expose
    private String maLoaiSp;
    @SerializedName("tenLoaiSp")
    @Expose
    private String tenLoaiSp;
    @SerializedName("hinhLoai")
    @Expose
    private String hinhLoai;
    @SerializedName("sanPham")
    @Expose
    private List<Object> sanPham = null;

    public String getMaLoaiSp() {
        return maLoaiSp;
    }

    public void setMaLoaiSp(String maLoaiSp) {
        this.maLoaiSp = maLoaiSp;
    }

    public String getTenLoaiSp() {
        return tenLoaiSp;
    }

    public void setTenLoaiSp(String tenLoaiSp) {
        this.tenLoaiSp = tenLoaiSp;
    }

    public String getHinhLoai() {
        return hinhLoai;
    }

    public void setHinhLoai(String hinhLoai) {
        this.hinhLoai = hinhLoai;
    }

    public List<Object> getSanPham() {
        return sanPham;
    }

    public void setSanPham(List<Object> sanPham) {
        this.sanPham = sanPham;
    }

}