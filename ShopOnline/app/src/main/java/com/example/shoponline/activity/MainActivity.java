package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MenuItem;
import com.example.shoponline.R;
import com.example.shoponline.fragment.DanhMucFragment;
import com.example.shoponline.fragment.GioHangFragment;
import com.example.shoponline.fragment.TaiKhoanFragment;
import com.example.shoponline.fragment.TrangChuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    NavigationView navigationView;
    boolean status=false;
    MenuItem menuItem;
    //ConstraintLayout constrainlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView =  findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
        loadFragment(new TrangChuFragment());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    fragment = new DanhMucFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_home:
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setTitle("Shop Online");
                    getSupportActionBar().setDisplayShowHomeEnabled(false);

                    fragment = new TrangChuFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_notifications:
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    fragment = new GioHangFragment();

                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
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

}
