package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.shoponline.R;
import com.example.shoponline.adapter.PagerAdapter;
import com.example.shoponline.fragment.DangKyFragment;
import com.example.shoponline.fragment.DangNhapFragment;
import com.example.shoponline.fragment.TaiKhoanFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class ThietLapTaiKhoanActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().show();
        getSupportActionBar().setTitle("Thông tin tài khoản");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap_tai_khoan);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new DangNhapFragment());
        pagerAdapter.addFragment(new DangKyFragment());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Đăng nhập");
        tabLayout.getTabAt(1).setText("Đăng ký");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}