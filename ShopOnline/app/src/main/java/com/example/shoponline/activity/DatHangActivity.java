package com.example.shoponline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.adapter.GioHangAdapter;
import com.example.shoponline.adapter.GioHangChiTietAdapter;
import com.example.shoponline.data.model.ChiTietHoaDon;
import com.example.shoponline.data.model.HoaDon;
import com.example.shoponline.data.model.InsertChitietdonhangRespone;
import com.example.shoponline.data.model.KhachHang;
import com.example.shoponline.data.remote.ApiService;
import com.example.shoponline.data.remote.RetrofitClient;
import com.example.shoponline.util.Constan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatHangActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDiachinhanhang, tvSoluongsp, tvTong, tvPhivc, tvTongtien, btnDathang;
    private ImageView btnThoat;
    private ListView lvSanpham;
    private List<HoaDon> hoadonList;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

        btnDathang.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
    }

    private void initData() {

        apiService = RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);

        GioHangChiTietAdapter adapter = new GioHangChiTietAdapter(this, MainActivity.mangGioHang);
        lvSanpham.setAdapter(adapter);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        tvDiachinhanhang.setText(MainActivity.khachHang.getHoTen() + ", " + MainActivity.khachHang.getDiaChi());
        int phivc = getIntent().getIntExtra(Constan.KEY_PHI_VC, 0);
        tvPhivc.setText(decimalFormat.format(phivc) + " Đ");
        tvSoluongsp.setText("Tạm tính (" + MainActivity.mangGioHang.size() + " sản phẩm)");

        long tongtien = 0;
        for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
            tongtien += MainActivity.mangGioHang.get(i).getGiasp();
        }
        tvTong.setText(decimalFormat.format(tongtien) + " Đ");
        tvTongtien.setText(decimalFormat.format(tongtien + phivc) + " Đ");

    }

    private void initView() {
        tvDiachinhanhang = findViewById(R.id.tv_diachi_giaohang);
        tvSoluongsp = findViewById(R.id.tv_soluongsp);
        tvTong = findViewById(R.id.tv_tongtien_tt);
        tvPhivc = findViewById(R.id.tv_phivc);
        tvTongtien = findViewById(R.id.tv_tongtien);
        btnDathang = findViewById(R.id.btn_dathang);
        btnThoat = findViewById(R.id.btn_thoat);

        lvSanpham = findViewById(R.id.lv_dathang);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_thoat:
                finish();
                break;
            case R.id.btn_dathang:
                if (MainActivity.mangGioHang.size() <= 0) {
                    Toast.makeText(DatHangActivity.this, "Giỏ hàng đang trống, vui lòng quay lại mua hàng.", Toast.LENGTH_SHORT).show();
                }
                else
                xulyDathang();
                break;
        }
    }

    private void xulyDathang() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ngaydathang = dateFormat.format(new Date());
        Random rd = new Random();
        int number1 = rd.nextInt(99999);


        HoaDon newHodon = new HoaDon();
        String luuMaHoaDonVuaTao = "HD01" + number1;
        newHodon.setMaHd(luuMaHoaDonVuaTao);
        newHodon.setNgayLap(ngaydathang);
        newHodon.setNgayGiao(ngaydathang);
        newHodon.setMaKh(MainActivity.khachHang.getMaKh());
        newHodon.setDiaChiGiao(MainActivity.khachHang.getDiaChi());
        newHodon.setTrangThai(false);


        Call<HoaDon> call = apiService.insertHoDon(newHodon);
        call.enqueue(new Callback<HoaDon>() {
            @Override
            public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                        ChiTietHoaDon jsonArray = new ChiTietHoaDon();

                            jsonArray.setMaHd(luuMaHoaDonVuaTao);
                            jsonArray.setMaSp(MainActivity.mangGioHang.get(i).getIdsp());
                            jsonArray.setSoLuong(MainActivity.mangGioHang.get(i).getSoluong());
                            jsonArray.setDonGia((int)MainActivity.mangGioHang.get(i).getGiasp());
                        Call<ChiTietHoaDon> callCTHoDon = apiService.insertCTHoDon(jsonArray);
                        callCTHoDon.enqueue(new Callback<ChiTietHoaDon>() {
                            @Override
                            public void onResponse(Call<ChiTietHoaDon> call, Response<ChiTietHoaDon> response) {
                                setResult(Constan.RESULT_CODE_DATHANG);

                            }

                            @Override
                            public void onFailure(Call<ChiTietHoaDon> call, Throwable t) {
                                Toast.makeText(DatHangActivity.this, "Đặt hàng thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    finish();
                    Toast.makeText(DatHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    // overridePendingTransition(R.anim.left_in,R.anim.right_out);
                } else {
                    Toast.makeText(DatHangActivity.this, "Đặt hàng thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HoaDon> call, Throwable t) {
                Toast.makeText(DatHangActivity.this, "Loi load", Toast.LENGTH_SHORT).show();
            }
        });

//        apiService.insertDonhang(jsonArray.toString()).enqueue(new Callback<InsertChitietdonhangRespone>() {
//            @Override
//            public void onResponse(Call<InsertChitietdonhangRespone> call, Response<InsertChitietdonhangRespone> response) {
//                InsertChitietdonhangRespone respone1 = response.body();
//                if(respone1.getSuccess()==1){
//                    setResult(Constan.RESULT_CODE_DATHANG);
//                    finish();
//                    Toast.makeText(DatHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
//                   // overridePendingTransition(R.anim.left_in,R.anim.right_out);
//                }else {
//                    Toast.makeText(DatHangActivity.this, "Đặt hàng thất bại!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<InsertChitietdonhangRespone> call, Throwable t) {
//                Toast.makeText(DatHangActivity.this, "Lỗi đặt hàng.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
