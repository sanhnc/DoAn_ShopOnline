package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muase on 4/12/2018.
 */

public class SanPhamResponse {

    @SerializedName("sanpham")
    @Expose
    public List<Sanpham> sanpham = null;
    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;

    public List<Sanpham> getSanpham() {
       return sanpham;
  }

    public Integer getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}