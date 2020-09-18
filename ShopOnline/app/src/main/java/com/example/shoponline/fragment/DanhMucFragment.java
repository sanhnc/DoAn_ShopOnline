package com.example.shoponline.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.adapter.LoaiSpAdapter;
import com.example.shoponline.adapter.SanPhamAdapter;
import com.example.shoponline.data.model.LoaiSpResponse;
import com.example.shoponline.data.model.Loaisanpham;
import com.example.shoponline.data.model.SanPhamResponse;
import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.data.remote.ApiService;
import com.example.shoponline.data.remote.RetrofitClient;
import com.example.shoponline.util.Constan;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhMucFragment extends Fragment {

    private TwoWayView lvLoaiSp;
    private RecyclerView rvChitietLoaiSP;

    private List<Loaisanpham> loaiSPList;
    private LoaiSpAdapter loaiSpAdapter;

    private List<Sanpham> sanphamList;
    private SanPhamAdapter sanPhamAdapter;

    private ApiService apiService;

    private String idLoaiSP = "1";

    private EditText edtTimkiem;
    private ImageView ivTimkiem;
    public final int NORMAL_MODE = 0;
    public final int SEACH_MODE = 1;
    private int currenMode;

    public DanhMucFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_muc, container, false);
        initView(view);
        initData();
        initEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void initEvent() {

        lvLoaiSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idLoaiSP = loaiSPList.get(i).getMaLoaiSp();
                loadSPtheoLoai(idLoaiSP);
            }
        });
        edtTimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                timSanPham(edtTimkiem.getText().toString());
            }
        });
    }


    private void initData() {
        apiService = RetrofitClient.getClient(Constan.API_URL).create(ApiService.class);

        rvChitietLoaiSP.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //list loại sản phẩm
        loadLoaiSP();

        //list tất cả các sản phẩm
        loadAllSP();

    }

    private void initView(View view) {
        lvLoaiSp = view.findViewById(R.id.lv_loaisp);
        rvChitietLoaiSP = view.findViewById(R.id.rv_chitietloaisp);
        edtTimkiem = view.findViewById(R.id.edt_timkiem);
        ivTimkiem = view.findViewById(R.id.iv_tiemkiem);
    }

    private void loadLoaiSP() {
        Call<List<Loaisanpham>> call = apiService.getLoaiSp();
        call.enqueue(new Callback<List<Loaisanpham>>() {
            @Override
            public void onResponse(Call<List<Loaisanpham>> call, Response<List<Loaisanpham>> response) {

                loaiSPList = (List<Loaisanpham>) response.body();
                loaiSpAdapter = new LoaiSpAdapter(getActivity(), loaiSPList);
                lvLoaiSp.setAdapter(loaiSpAdapter);
            }

            @Override
            public void onFailure(Call<List<Loaisanpham>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi kết nối đến loại sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });


//        apiService.getLoaiSp().enqueue(new Callback<LoaiSpResponse>() {
//            @Override
//            public void onResponse(Call<LoaiSpResponse> call, Response<LoaiSpResponse> response) {
//                LoaiSpResponse loaiSpResponse = response.body();
//                loaiSPList = loaiSpResponse.getLoaisanpham();
//                loaiSpAdapter = new LoaiSpAdapter(getActivity(),loaiSPList);
//                lvLoaiSp.setAdapter(loaiSpAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<LoaiSpResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Lỗi kết nối đến loại sản phẩm", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadSPtheoLoai(String MaLoaiSP) {

        Call<List<Sanpham>> call = apiService.getSpTheoloai(MaLoaiSP);
        call.enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {


                sanphamList = response.body();
             sanPhamAdapter = new SanPhamAdapter(getActivity(), sanphamList);
               rvChitietLoaiSP.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi kết nối đến loại sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });


//        apiService.getSpTheoloai(idLoaiSP).enqueue(new Callback<SanPhamResponse>() {
//            @Override
//            public void onResponse(Call<SanPhamResponse> call, Response<SanPhamResponse> response) {
//                SanPhamResponse sanPhamResponse = response.body();
//                sanPhamAdapter = new SanPhamAdapter(getActivity(), sanphamList);
//                rvChitietLoaiSP.setAdapter(sanPhamAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<SanPhamResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Lỗi tải sản phẩm theo loại", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadAllSP() {
        Call<List<Sanpham>> call = apiService.getSpMoinhat();
        call.enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {

                sanphamList = (List<Sanpham>) response.body();
                sanPhamAdapter =new SanPhamAdapter(getActivity(), sanphamList);
                rvChitietLoaiSP.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi kết nối đến loại sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });

//        apiService.getAllSanpham().enqueue(new Callback<SanPhamResponse>() {
//            @Override
//            public void onResponse(Call<SanPhamResponse> call, Response<SanPhamResponse> response) {
//                SanPhamResponse sanPhamResponse = response.body();
//                sanphamList = sanPhamResponse.getSanpham();
//                sanPhamAdapter = new SanPhamAdapter(getActivity(), sanphamList);
//                rvChitietLoaiSP.setAdapter(sanPhamAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<SanPhamResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Lỗi tải tất cả sản phẩm", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void timSanPham(String s) {
        if (s.trim().length() > 0) {
            //search
            //clear list

            ArrayList<Sanpham> listSeach = new ArrayList<>();
            listSeach.clear();
            //search item
            for (int i = 0; i < sanphamList.size(); i++) {
                //kiểm tra input có nằm trong danh sách không?
                if (sanphamList.get(i).getTenSp().trim().toLowerCase().contains(s.trim().toLowerCase())) {
                    listSeach.add(sanphamList.get(i));
                }
            }
            //change mode view
            currenMode = SEACH_MODE;
            //updet ui
            sanPhamAdapter = new SanPhamAdapter(getActivity(), listSeach);
            rvChitietLoaiSP.setAdapter(sanPhamAdapter);
        } else {
            //show normal listview
            //change mode view
            currenMode = NORMAL_MODE;

            sanPhamAdapter = new SanPhamAdapter(getActivity(), sanphamList);
            rvChitietLoaiSP.setAdapter(sanPhamAdapter);
        }
    }
}
