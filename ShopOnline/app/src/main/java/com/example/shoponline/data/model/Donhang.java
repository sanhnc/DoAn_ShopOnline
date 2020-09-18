package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Donhang {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("makh")
    @Expose
    public String makh;

    @SerializedName("masp")
    @Expose
    public String masp;

    @SerializedName("tensp")
    @Expose
    public String tensp;

    @SerializedName("giasp")
    @Expose
    public String giasp;

    @SerializedName("soluongsp")
    @Expose
    public String soluongsp;

    @SerializedName("ngaydathang")
    @Expose
    public String ngaydathang;

    public String getId() {
        return id;
    }

    public String getMakh() {
        return makh;
    }

    public String getMasp() {
        return masp;
    }

    public String getTensp() {
        return tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public String getSoluongsp() {
        return soluongsp;
    }
    public String getNgaydathang(){
        return ngaydathang;
    }
}
