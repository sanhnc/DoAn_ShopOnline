package com.example.shoponline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muase on 4/12/2018.
 */

public class InsertChitietdonhangRespone {
    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;

    public Integer getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
