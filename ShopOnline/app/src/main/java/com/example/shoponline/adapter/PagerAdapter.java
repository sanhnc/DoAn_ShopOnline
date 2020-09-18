package com.example.shoponline.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> arrayList = new ArrayList<>();


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public void addFragment(Fragment fragment){
        arrayList.add(fragment);
    }
}
