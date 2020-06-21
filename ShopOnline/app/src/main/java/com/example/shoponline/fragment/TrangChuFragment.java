package com.example.shoponline.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;


import com.example.shoponline.data.model.SanPhamResponse;
import com.example.shoponline.R;
import com.example.shoponline.adapter.SanPhamMoiAdapter;
import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.data.remote.ApiService;
import com.example.shoponline.data.remote.RetrofitClient;
import com.example.shoponline.util.Constan;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private RecyclerView rvSpMoi;

    private List<Sanpham> spMoinhat;
    private SanPhamMoiAdapter sanPhamMoiAdapter;

    private ApiService apiService;

    public TrangChuFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        initView(view);
        //chạy ảnh quảng cáo


        initData();
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initView(View view) {


        rvSpMoi = view.findViewById(R.id.rv_spmoi);
    }
    private void initData() {
        //webservice
        apiService = RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);

        loadSanphammoinhat();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notifications) {

        }
        return super.onOptionsItemSelected(item);
    }
    private void loadSanphammoinhat(){
        apiService.getSpMoinhat().enqueue(new Callback<SanPhamResponse>() {
            @Override
            public void onResponse(Call<SanPhamResponse> call, Response<SanPhamResponse> response) {
                SanPhamResponse sanPhamResponse = response.body();
                spMoinhat = sanPhamResponse.getSanpham();

                sanPhamMoiAdapter = new SanPhamMoiAdapter(getActivity(),spMoinhat);
                rvSpMoi.setLayoutManager(new GridLayoutManager(getActivity(),2));
                rvSpMoi.setAdapter(sanPhamMoiAdapter);
            }
            @Override
            public void onFailure(Call<SanPhamResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi!\n Vui lòng kiểm tra lại kết nối.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
