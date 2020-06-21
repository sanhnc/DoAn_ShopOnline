package com.example.shoponline.model;

/**
 * Created by muase on 4/11/2018.
 */

public class ItemTK {
    private int icon;
    private String tenChucnang;

    public ItemTK(int icon, String tenChucnang) {
        this.icon = icon;
        this.tenChucnang = tenChucnang;
    }

    public int getIcon() {
        return icon;
    }

    public String getTenChucnang() {
        return tenChucnang;
    }
}
