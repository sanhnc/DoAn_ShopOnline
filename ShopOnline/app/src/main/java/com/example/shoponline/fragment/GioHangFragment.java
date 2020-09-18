package com.example.shoponline.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.activity.DatHangActivity;
import com.example.shoponline.activity.MainActivity;
import com.example.shoponline.activity.ThietLapTaiKhoanActivity;
import com.example.shoponline.adapter.GioHangAdapter;
import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.model.GioHang;
import com.example.shoponline.util.Constan;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangFragment extends Fragment implements View.OnClickListener {


    private TextView tvDiachi, tvTiepkiem, tvTieuchuan, tvTongtien, btnThanhtoan, tvTrong;
    private LinearLayout llThongtin;
    private RelativeLayout rlTieuchuan, rlTiepkiem;
    private ImageView ivTickTc, ivTickTk;

    private ListView lvSPGiohang;

    private List<Sanpham> giohangList;

    private int phiVc = 15000;

    public GioHangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        initView(view);
        initData();
        initEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void initEvent() {
        btnThanhtoan.setOnClickListener(this);
        rlTieuchuan.setOnClickListener(this);
        rlTiepkiem.setOnClickListener(this);
    }

    private void initData() {
        if (MainActivity.mangGioHang.size() <= 0) {
            tvTrong.setVisibility(View.VISIBLE);
            llThongtin.setVisibility(View.GONE);
            tvTongtien.setText("0 Đ");
        } else {
            tvTrong.setVisibility(View.GONE);
            llThongtin.setVisibility(View.VISIBLE);

            GioHangAdapter adapter = new GioHangAdapter(getActivity(), MainActivity.mangGioHang);
            lvSPGiohang.setAdapter(adapter);

            tvTongtien.setText(loadTongCong(MainActivity.mangGioHang));
            tvTiepkiem.setText(ngayNhanHang(7));
            tvTieuchuan.setText(ngayNhanHang(5));
        }
        if(MainActivity.isDangNhap) tvDiachi.setText(MainActivity.khachHang.getDiaChi());
    }

    private String loadTongCong(List<GioHang> list) {
        long tongtien = 0;
        for (int i = 0; i < list.size(); i++) {
            tongtien += list.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        return decimalFormat.format(tongtien) + " Đ";
    }

    private void initView(View view) {
        tvDiachi = view.findViewById(R.id.tv_diachi);
        tvTiepkiem = view.findViewById(R.id.tv_thoigian_tiepkiem);
        tvTieuchuan = view.findViewById(R.id.tv_thoigian_tieuchuan);
        tvTongtien = view.findViewById(R.id.tv_tongtien);
        btnThanhtoan = view.findViewById(R.id.btn_thanhtoan);
        llThongtin = view.findViewById(R.id.ll_thongtin);
        lvSPGiohang = view.findViewById(R.id.lv_sp_giohang);
        tvTrong = view.findViewById(R.id.tv_trong);

        rlTieuchuan = view.findViewById(R.id.rl_tieuchuan);
        rlTiepkiem = view.findViewById(R.id.rl_tiepkiem);

        ivTickTc = view.findViewById(R.id.iv_tick_tc);
        ivTickTk = view.findViewById(R.id.iv_tick_tk);
    }

    private String ngayNhanHang(int ngay2) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stDate = dateFormat.format(new Date());

        Date date = convertStrinhToDate(stDate);
        String nam = (String) DateFormat.format("yyyy", date);
        String thang = (String) DateFormat.format("MMM", date);
        String ngay = (String) DateFormat.format("dd", date);

        String st = "Nhận hàng vào " + ngay + "-" + (Integer.parseInt(ngay) + ngay2) + " tháng " + thang + " " + nam;
        return st;
    }

    //chuyển đổi string lại thành ngày tháng
    public static Date convertStrinhToDate(String datetime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(datetime);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        if (MainActivity.mangGioHang.size() <= 0) {
            Toast.makeText(getActivity(), "Giỏ hàng đang trống, vui lòng quay lại mua hàng.", Toast.LENGTH_SHORT).show();
        } else {
            switch (view.getId()) {
                case R.id.btn_thanhtoan:
                    xulyThanhtoan();
                    break;
                case R.id.rl_tieuchuan:

                    ivTickTc.setVisibility(View.VISIBLE);
                    ivTickTk.setVisibility(View.GONE);
                    phiVc = 25000;
                    break;
                case R.id.rl_tiepkiem:

                    ivTickTc.setVisibility(View.GONE);
                    ivTickTk.setVisibility(View.VISIBLE);
                    phiVc = 15000;

                    break;
            }
        }
    }

    private void xulyThanhtoan() {
        //chưa đăng nhập
        if (!MainActivity.isDangNhap) {
            //chưa đăng nhập thì mở activity đăng ký / đăng nhập
            final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("Hãy đăng nhập tài khoản của bạn");
            dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(), ThietLapTaiKhoanActivity.class);
                    intent.putExtra(Constan.KEY_GIOHANG_DANGNHAP,true);
                    getActivity().startActivityForResult(intent, Constan.REQUEST_CODE_GIOHANG);
                    //getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
                }
            });
            dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();
        } else {
            //thanh toán
            Intent intent = new Intent(getActivity(), DatHangActivity.class);
            intent.putExtra(Constan.KEY_PHI_VC,phiVc);
            getActivity().startActivityForResult(intent,Constan.REQUEST_CODE_DATHANG);
            //getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
