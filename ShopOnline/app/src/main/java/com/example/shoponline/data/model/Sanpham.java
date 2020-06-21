package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by muase on 4/12/2018.
 */

public class Sanpham implements Serializable{
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("tensp")
    @Expose
    public String tensp;
    @SerializedName("giasp")
    @Expose
    public String giasp;
    @SerializedName("hinhanhsp")
    @Expose
    public String hinhanhsp;
    @SerializedName("motasp")
    @Expose
    public String motasp;
    @SerializedName("id_loaisp")
    @Expose
    public String idLoaisp;

    public String getId() {
        return id;
    }

    public String getTensp() {
        return tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public String getMotasp() {
        return motasp;
    }

    public String getIdLoaisp() {
        return idLoaisp;
    }
}
