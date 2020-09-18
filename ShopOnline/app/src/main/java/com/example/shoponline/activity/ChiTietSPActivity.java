package com.example.shoponline.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shoponline.R;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.model.GioHang;
import com.example.shoponline.util.Constan;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSPActivity extends AppCompatActivity {
    String id = "";
    String tenchitiet = "";
    int giachitiet = 0;
    String hinhanhchitiet = "";
    String motachitiet = "";
    String idloaisp = "";
    private Spinner spinner;
    private TextView tvTenchitiet, tvGiachitiet, tvMotachitiet;
    private ImageView ivChitiet,btnThoat;
    private TextView btnThemGioHang;
    public ChiTietSPActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_s_p);
        initView();
        getInfo();
        catEventSpinner();
        initEvient();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AFADB1")));
        getSupportActionBar().hide();


    }
    private void initEvient() {
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(MainActivity.mangGioHang.size()>0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exit = false;
                    for (int i = 0; i<MainActivity.mangGioHang.size();i++){
                        if(MainActivity.mangGioHang.get(i).getIdsp() == id){
                            MainActivity.mangGioHang.get(i).setSoluong(MainActivity.mangGioHang.get(i).getSoluong()+sl);
                            if(MainActivity.mangGioHang.get(i).getSoluong()>=10){
                                MainActivity.mangGioHang.get(i).setSoluong(10);
                            }
                            MainActivity.mangGioHang.get(i).setGiasp(giachitiet * MainActivity.mangGioHang.get(i).getSoluong());
                            exit = true;
                        }
                    }
                    if(!exit){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi = soluong * giachitiet;
                        MainActivity.mangGioHang.add(new GioHang(id,tenchitiet,giamoi,hinhanhchitiet,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong * giachitiet;
                    MainActivity.mangGioHang.add(new GioHang(id,tenchitiet,giamoi,hinhanhchitiet,soluong));
                }
                Toast.makeText(ChiTietSPActivity.this, "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        btnGiohang
//            public void onClick(View view) {
//                setResult(Constan.REQUEST_CODE_GIOHANG);
//                finish();
//            }
//        });
    }

    //so luong tren spiner
    private void catEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    private void initView() {

        ivChitiet = findViewById(R.id.iv_chitietsp);
        tvTenchitiet = findViewById(R.id.tv_tenchitietsp);
        tvGiachitiet = findViewById(R.id.tv_giachitietsp);
        tvMotachitiet = findViewById(R.id.tv_motachitietsp);
        spinner = findViewById(R.id.spinner);
        btnThemGioHang = findViewById(R.id.btn_datmua);
        btnThoat = findViewById(R.id.btn_thoat);

    }
    private void getInfo() {
        Sanpham sanPham = (Sanpham) getIntent().getSerializableExtra(Constan.THONGTIN_SP);
        id = sanPham.getMaSp();
        tenchitiet = sanPham.getTenSp();
        giachitiet =sanPham.getGiaBan();
        hinhanhchitiet = sanPham.getHinhAnh();
        motachitiet = sanPham.getMoTa();
        idloaisp = sanPham.getMaLoaiSp();

        tvTenchitiet.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiachitiet.setText("Giá: "+decimalFormat.format(giachitiet)+" Đ");
        tvMotachitiet.setText(motachitiet);
        Picasso.with(getApplicationContext()).load(hinhanhchitiet)
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(ivChitiet);
    }
    @Override
    public void finish() {
        super.finish();
    }



}