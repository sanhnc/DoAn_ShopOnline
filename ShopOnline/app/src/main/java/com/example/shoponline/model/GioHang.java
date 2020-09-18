package com.example.shoponline.model;

import java.io.Serializable;

/**
 * Created by muase on 3/18/2018.
 */

public class GioHang implements Serializable{
    private String idsp;
    private String tensp;
    private long giasp;
    private String hinhsp;
    private int soluong;
    public GioHang(){

    }
    public String getIdsp() {
        return idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public GioHang(String idsp, String tensp, long giasp, String hinhsp, int soluong) {

        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluong = soluong;
    }
}
