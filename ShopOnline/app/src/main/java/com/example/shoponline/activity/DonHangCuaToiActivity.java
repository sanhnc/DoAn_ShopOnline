package com.example.shoponline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.adapter.DonHangAdapter;
import com.example.shoponline.data.model.DonHangResponse;
import com.example.shoponline.data.model.Donhang;
import com.example.shoponline.data.model.HoaDon;
import com.example.shoponline.data.model.luuMaHoaDon;
import com.example.shoponline.data.remote.ApiService;
import com.example.shoponline.data.remote.RetrofitClient;
import com.example.shoponline.util.Constan;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangCuaToiActivity extends AppCompatActivity {
    public static String ma = "";
    private ListView lvDonhangcuatoi;
    private List<HoaDon> donhangList;
    private DonHangAdapter adapter;
    private TextView tvDonhangtrong;
    private ImageView btnThoat;
    private ApiService apiService;
    private String idLoaiSP = "HD001";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_cua_toi);
        getSupportActionBar().hide();
        lvDonhangcuatoi = findViewById(R.id.lv_donhangcuatoi);
        tvDonhangtrong = findViewById(R.id.tv_donhang_trong);
        btnThoat = findViewById(R.id.btn_thoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
        });

        donhangList= new ArrayList<>();
        if (MainActivity.khachHang != null) {
            apiService = RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);
            Call<List<HoaDon>> call = apiService.getHoaDon(MainActivity.khachHang.getMaKh());
            call.enqueue(new Callback<List<HoaDon>>() {
                @Override
                public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
//                            DonHangResponse donHangResponse = response.body();
//                            //loi php truy van theo id khach hang?
//                            for(int i = 0; i<donHangResponse.getDonhang().size();i++){
//                                if(MainActivity.khachHang.getId().equals(donHangResponse.getDonhang().get(i).getMakh())){
//                                    donhangList.add(donHangResponse.getDonhang().get(i));
//                                }
//                            }
                    donhangList = response.body();
                    if (donhangList.size() > 0) {
                        lvDonhangcuatoi.setVisibility(View.VISIBLE);
                        tvDonhangtrong.setVisibility(View.GONE);
                    }
                    adapter = new DonHangAdapter(DonHangCuaToiActivity.this, donhangList);
                    lvDonhangcuatoi.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<HoaDon>> call, Throwable t) {
                    Toast.makeText(DonHangCuaToiActivity.this, "Lỗi tải các đơn hàng của bạn", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            tvDonhangtrong.setVisibility(View.VISIBLE);
        }

        lvDonhangcuatoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idLoaiSP = donhangList.get(i).getMaHd();
                luuMaHoaDon a = new luuMaHoaDon();
                a.setMahd(idLoaiSP);
                ma = a.getMahd();
               // Toast.makeText(DonHangCuaToiActivity.this, a.getMahd(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DonHangCuaToiActivity.this, ChiTietHoaDonCuaToiActivity.class);
                startActivity(intent);
            }
        });
    }
}
