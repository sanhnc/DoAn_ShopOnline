package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.data.model.Donhang;
import com.example.shoponline.data.model.HoaDon;
import com.example.shoponline.data.model.KhachHang;
import com.example.shoponline.fragment.DanhMucFragment;
import com.example.shoponline.fragment.GioHangFragment;
import com.example.shoponline.fragment.TaiKhoanFragment;
import com.example.shoponline.fragment.TrangChuFragment;
import com.example.shoponline.model.GioHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    NavigationView navigationView;
    boolean status=false;
    MenuItem menuItem;
    public static List<GioHang> mangGioHang;
    public static KhachHang khachHang;
    public static HoaDon hoaDon;
    public static boolean isDangNhap = false;
    RelativeLayout constraintLayout;
    //ConstraintLayout constrainlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView =  findViewById(R.id.nav_view);
        mangGioHang = new ArrayList<>();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
        constraintLayout = findViewById(R.id.root);
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                constraintLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = constraintLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) {
                    navView.setVisibility(View.GONE);
                } else {
                    navView.setVisibility(View.VISIBLE);
                }
            }
        });
        loadFragment(new TrangChuFragment());
        getSupportActionBar().hide();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    getSupportActionBar().setTitle("Danh mục");
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    getSupportActionBar().show();
                    fragment = new DanhMucFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_home:
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().hide();
                    getSupportActionBar().setTitle("Shop Online");
                    getSupportActionBar().setDisplayShowHomeEnabled(false);

                    fragment = new TrangChuFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_notifications:
                    getSupportActionBar().show();
                    getSupportActionBar().setTitle("Giỏ hàng");
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    fragment = new GioHangFragment();

                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    getSupportActionBar().show();
                    getSupportActionBar().setTitle("Tài khoản");
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    fragment = new TaiKhoanFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    public static void setSoluong(int sl) {
//        tvSoluong.setText(sl + "");
//    }
}
