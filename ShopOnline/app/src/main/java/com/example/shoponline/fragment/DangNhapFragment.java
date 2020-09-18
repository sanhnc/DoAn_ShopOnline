package com.example.shoponline.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.activity.MainActivity;
import com.example.shoponline.adapter.SanPhamMoiAdapter;
import com.example.shoponline.data.model.HoaDon;
import com.example.shoponline.data.model.KhachHang;
import com.example.shoponline.data.model.KhachHangResponse;
import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.data.remote.ApiService;
import com.example.shoponline.data.remote.RetrofitClient;
import com.example.shoponline.util.Constan;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.List;

public class DangNhapFragment extends Fragment implements View.OnClickListener {
    private EditText edtMail, edtPass;
    private TextView tvQuenmk, btnDangnhap;
    private LinearLayout btnLoginFb;
    private SignInButton btnSignGoogle;

    private ApiService apiService;
    private List<KhachHang> userList;
    private  List<HoaDon> hoadonList;
    public DangNhapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dang_nhap, container, false);
        initView(view);

        initData();

        iniEvent();
        // Inflate the layout for this fragment
        return view;
    }
    private void iniEvent() {
        btnDangnhap.setOnClickListener(this);
//        tvQuenmk.setOnClickListener(this);
//        btnLoginFb.setOnClickListener(this);
//        btnSignGoogle.setOnClickListener(this);
    }

    private void initData() {
        apiService =  RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);
        userList = new ArrayList<>();

        Call<List<KhachHang>> call = apiService.getKhachhang();
        call.enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {

             userList = response.body();

            }

            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {

            }
        });
//        apiService.getAllUser().enqueue(new Callback<KhachHangResponse>() {
//            @Override
//            public void onResponse(Call<KhachHangResponse> call, Response<KhachHangResponse> response) {
//                KhachHangResponse userResponse = response.body();
//                userList = userResponse.getKhachhang();
//            }
//
//            @Override
//            public void onFailure(Call<KhachHangResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Lỗi load user.", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void initView(View view) {
        edtMail = view.findViewById(R.id.email);
        edtPass = view.findViewById(R.id.password);
//        tvQuenmk = view.findViewById(R.id.tv_quenmk);
        btnDangnhap = view.findViewById(R.id.login);
//        btnLoginFb = view.findViewById(R.id.ll_loginfb);
//        btnSignGoogle = view.findViewById(R.id.btn_signin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                //xử lý đăng nhập
                String user = edtMail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                for (int i = 0; i<userList.size(); i++){
                    if(userList.get(i).getEmail().equalsIgnoreCase(user) && userList.get(i).getMatKhau().equals(pass)){
                        MainActivity.isDangNhap = true;
                        MainActivity.khachHang = userList.get(i);
                        continue;
                    }
                }

                if(MainActivity.isDangNhap){
                    Toast.makeText(getActivity(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    if(getActivity().getIntent().getBooleanExtra(Constan.KEY_GIOHANG_DANGNHAP,false))
                        getActivity().setResult(Constan.RESULT_CODE_GIOHANG_DANGNHAP);
                    else getActivity().setResult(Constan.REQUEST_CODE_DANHNHAP);

                    getActivity().finish();
                    //getActivity().overridePendingTransition(R.anim.left_in,R.anim.right_out);
                }else{
                    Toast.makeText(getActivity(), "Đăng nhập thất bại, sai tên đăng nhập hoặc mật khẩu.\nNếu bạn chưa có tài khoản vui lòng đăng ký.", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
