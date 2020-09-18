package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muase on 4/21/2018.
 */

public class DonHangResponse {
    @SerializedName("donhang")
    @Expose
    public List<Donhang> donhang = null;
    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;

    public List<Donhang> getDonhang() {
        return donhang;
    }

    public Integer getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
