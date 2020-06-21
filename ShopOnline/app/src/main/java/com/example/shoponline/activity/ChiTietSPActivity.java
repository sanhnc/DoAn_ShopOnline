package com.example.shoponline.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shoponline.R;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.util.Constan;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSPActivity extends AppCompatActivity {
    int id = 0;
    String tenchitiet = "";
    int giachitiet = 0;
    String hinhanhchitiet = "";
    String motachitiet = "";
    int idloaisp = 0;
    private TextView tvTenchitiet, tvGiachitiet, tvMotachitiet;
    private ImageView ivChitiet;

    public ChiTietSPActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_s_p);
        initView();
        getInfo();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AFADB1")));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi tiết sản phẩm");

    }
    private void initView() {

        ivChitiet = findViewById(R.id.iv_chitietsp);
        tvTenchitiet = findViewById(R.id.tv_tenchitietsp);
        tvGiachitiet = findViewById(R.id.tv_giachitietsp);
        tvMotachitiet = findViewById(R.id.tv_motachitietsp);
    }
    private void getInfo() {
        Sanpham sanPham = (Sanpham) getIntent().getSerializableExtra(Constan.THONGTIN_SP);
        id = Integer.parseInt(sanPham.getId());
        tenchitiet = sanPham.getTensp();
        giachitiet = Integer.parseInt(sanPham.getGiasp());
        hinhanhchitiet = sanPham.getHinhanhsp();
        motachitiet = sanPham.getMotasp();
        idloaisp = Integer.parseInt(sanPham.getIdLoaisp());

        tvTenchitiet.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiachitiet.setText("Giá: "+decimalFormat.format(giachitiet)+" Đ");
        tvMotachitiet.setText(motachitiet);
        Picasso.with(getApplicationContext()).load(hinhanhchitiet)
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(ivChitiet);
    }




}