package com.example.shoponline.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.adapter.SanPhamAdapter;
import com.example.shoponline.data.model.InsertKHResponse;
import com.example.shoponline.data.model.KhachHang;
import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.data.remote.ApiService;
import com.example.shoponline.data.remote.RetrofitClient;
import com.example.shoponline.util.Constan;
import com.google.android.gms.common.SignInButton;

import java.util.List;
import java.util.Random;


public class DangKyFragment extends Fragment implements View.OnClickListener {


    private EditText edtHoten, edtMail, edtSdt, edtMk1, edtMk2, edtDiachi;
    private TextView btnDangky;
    private CheckBox cbDieukhoan;
    private LinearLayout btnLoginFb;
    private SignInButton btnSignGoogle;

    private ApiService apiService;

    public DangKyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_ky, container, false);

        initView(view);

        initData();

        initEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void initEvent() {
        btnDangky.setOnClickListener(this);
//        btnLoginFb.setOnClickListener(this);
//        btnSignGoogle.setOnClickListener(this);
    }

    private void initData() {

        apiService = RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);
    }

    private void initView(View view) {
        edtHoten = view.findViewById(R.id.edt_hoten);
        edtMail = view.findViewById(R.id.edt_email);
        edtSdt = view.findViewById(R.id.edt_sdt);
        edtMk1 = view.findViewById(R.id.edt_matkhau);
        edtMk2 = view.findViewById(R.id.edt_xacnhanmk);
        edtDiachi = view.findViewById(R.id.edt_diachi);

        btnDangky = view.findViewById(R.id.btn_dangnky);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_dangnky:
                xulyDangky();
                break;
//            case R.id.ll_loginfb:
//                Toast.makeText(getActivity(), "App đang nâng cấp, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btn_signin:
//                Toast.makeText(getActivity(), "App đang nâng cấp, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
//                break;
        }
    }

    private void xulyDangky() {
        String hoten = edtHoten.getText().toString().trim();
        String sdt = edtSdt.getText().toString().trim();
        String mail = edtMail.getText().toString().trim();
        String mk1 = edtMk1.getText().toString().trim();
        String mk2 = edtMk2.getText().toString().trim();
        String diachi = edtDiachi.getText().toString().trim();

        KhachHang taiKhoan= new KhachHang();
        taiKhoan.setSdt(sdt);
        taiKhoan.setHoTen(hoten);
        taiKhoan.setMatKhau(mk1);
        taiKhoan.setEmail(mail);
        taiKhoan.setDiaChi(diachi);

        Random rd = new Random();
        int number1 = rd.nextInt(9999);
        if (hoten.isEmpty() || sdt.isEmpty() || mail.isEmpty() || mk1.isEmpty() || mk2.isEmpty()) {
            Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
        } else {
            if (!mk1.equals(mk2)) {
                Toast.makeText(getActivity(), "Mật khẩu không khớp, Vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
            } else {






                Call<KhachHang> call = apiService.insertThongtinkh(taiKhoan);
                call.enqueue(new Callback<KhachHang>() {
                    @Override
                    public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {


                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Đăng ký thất bại! Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                            }
                            getActivity().setResult(Constan.REQUEST_CODE_DATHANG);
                            getActivity().finish();
                    }

                    @Override
                    public void onFailure(Call<KhachHang> call, Throwable t) {
                        Toast.makeText(getActivity(), "Lỗi đăng ký.", Toast.LENGTH_SHORT).show();
                    }
                });







//                    apiService.insertThongtinkh(mail,mk1,hoten, diachi, sdt).enqueue(new Callback<InsertKHResponse>() {
//                        @Override
//                        public void onResponse(Call<InsertKHResponse> call, Response<InsertKHResponse> response) {
//                            InsertKHResponse insertKHResponse = response.body();
//                            if (insertKHResponse.getSuccess() == 1) {
//                                Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getActivity(), "Đăng ký thất bại! Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
//                            }
//                            getActivity().setResult(Constan.REQUEST_CODE_DATHANG);
//                            getActivity().finish();
//                            //getActivity().overridePendingTransition(R.anim.left_in,R.anim.right_out);
//                        }
//
//                        @Override
//                        public void onFailure(Call<InsertKHResponse> call, Throwable t) {
//                            Toast.makeText(getActivity(), "Lỗi đăng ký.", Toast.LENGTH_SHORT).show();
//                        }
//                    });

            }
        }
    }
}
