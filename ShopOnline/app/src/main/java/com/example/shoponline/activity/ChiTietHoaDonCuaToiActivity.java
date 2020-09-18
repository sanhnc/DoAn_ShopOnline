package com.example.shoponline.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.adapter.ChiTietHoaDonAdapter;
import com.example.shoponline.adapter.DonHangAdapter;
import com.example.shoponline.data.model.ChiTietHoaDon;
import com.example.shoponline.data.model.HoaDon;
import com.example.shoponline.data.model.luuMaHoaDon;
import com.example.shoponline.data.remote.ApiService;
import com.example.shoponline.data.remote.RetrofitClient;
import com.example.shoponline.util.Constan;

import java.util.List;

public class ChiTietHoaDonCuaToiActivity extends AppCompatActivity {
    private ListView lvDonhangcuatoi;
    private List<ChiTietHoaDon> donhangList;
    private ChiTietHoaDonAdapter adapter;
    private TextView tvDonhangtrong;
    private ImageView btnThoat;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don_cua_toi);
        getSupportActionBar().hide();
        lvDonhangcuatoi = findViewById(R.id.lv_dathang);
        tvDonhangtrong = findViewById(R.id.tv_donhang_trong);

        btnThoat = findViewById(R.id.btn_thoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
        });

        apiService = RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);
        Call<List<ChiTietHoaDon>> call = apiService.getCTHoaDon(DonHangCuaToiActivity.ma);
        call.enqueue(new Callback<List<ChiTietHoaDon>>() {
            @Override
            public void onResponse(Call<List<ChiTietHoaDon>> call, Response<List<ChiTietHoaDon>> response) {
                donhangList = response.body();
                adapter = new ChiTietHoaDonAdapter(ChiTietHoaDonCuaToiActivity.this, donhangList);
                lvDonhangcuatoi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChiTietHoaDon>> call, Throwable t) {
                Toast.makeText(ChiTietHoaDonCuaToiActivity.this, "Lỗi tải các đơn hàng của bạn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}