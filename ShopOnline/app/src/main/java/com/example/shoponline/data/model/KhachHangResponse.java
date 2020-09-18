package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muase on 4/16/2018.
 */

public class KhachHangResponse {


    @SerializedName("khachhang")
    @Expose
    public List<KhachHang> khachhang = null;
    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;

    public List<KhachHang> getKhachhang() {
        return khachhang;
    }

    public Integer getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
