package com.example.shoponline.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shoponline.R;
import com.example.shoponline.activity.DonHangCuaToiActivity;
import com.example.shoponline.activity.ThietLapTaiKhoanActivity;


public class TaiKhoanFragment extends Fragment {

    public TaiKhoanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);

        Button btnThietLapTaiKhoan = (Button) view.findViewById(R.id.btnThietLapTaiKhoan);
        Button btnDonHang = (Button) view.findViewById(R.id.btnDonHang);
        Button btnDangXuat = (Button) view.findViewById(R.id.btnDangXuat);

        btnThietLapTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThietLapTaiKhoanActivity.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });

        btnDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(), DonHangCuaToiActivity.class);

                startActivity(a);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
                System.exit(0);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_menu_taikhoan,menu);
    }
}
