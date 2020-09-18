package com.example.shoponline.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.data.model.ChiTietHoaDon;
import com.example.shoponline.data.model.HoaDon;
import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietHoaDonAdapter extends BaseAdapter {
    private Context context;
    private List<ChiTietHoaDon> donhangList;
    private List<Sanpham> sanphamList;

    public ChiTietHoaDonAdapter(Context context, List<ChiTietHoaDon> donhangList) {
        this.context = context;
        this.donhangList = donhangList;
    }

    @Override
    public int getCount() {
        return donhangList.size();
    }

    @Override
    public Object getItem(int i) {
        return donhangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ChiTietHoaDonAdapter.Holder holder;
        if (view == null) {
            holder = new ChiTietHoaDonAdapter.Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_chitiethoadon, null);
            holder.ivGiohang = view.findViewById(R.id.iv_giohang);
            holder.tvTengiohang = view.findViewById(R.id.tv_tengiohang);
            holder.tvGiagiohang = view.findViewById(R.id.tv_giagiohang);
            holder.btnSoluong = view.findViewById(R.id.btn_soluong);
            view.setTag(holder);

        } else holder = (ChiTietHoaDonAdapter.Holder) view.getTag();

        ChiTietHoaDon gioHang = donhangList.get(i);

        holder.tvTengiohang.setText(gioHang.getTenSp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiagiohang.setText(decimalFormat.format(gioHang.getDonGia()) + " Đ");
        Picasso.with(context).load(gioHang.getHinhAnh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivGiohang);
        holder.btnSoluong.setText(gioHang.getSoLuong() + "");


        return view;
    }

    class Holder {
        ImageView ivGiohang;
        TextView tvTengiohang, tvGiagiohang, btnSoluong;

    }

}
