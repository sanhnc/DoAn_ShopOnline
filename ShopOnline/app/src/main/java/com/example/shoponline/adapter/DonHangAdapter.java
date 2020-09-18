package com.example.shoponline.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.data.model.Donhang;
import com.example.shoponline.data.model.HoaDon;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by muase on 4/21/2018.
 */

public class DonHangAdapter extends BaseAdapter {

    private Context context;
    private List<HoaDon> donhangList;

    public DonHangAdapter(Context context, List<HoaDon> donhangList) {
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
        Holder holder;
        if(view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_donhangcuatoi, null);
            holder.tvTensp = view.findViewById(R.id.tv_tensp);
            holder.tvSoluong = view.findViewById(R.id.tv_soluong);
            holder.tvTongtien = view.findViewById(R.id.tv_tongtien);
            holder.tvNgaydathang = view.findViewById(R.id.tv_ngaymuahang);
            holder.tvDiachiNhanHang = view.findViewById(R.id.tv_dichinhanhang);
            view.setTag(holder);
        }else holder = (Holder) view.getTag();

        HoaDon donhang = donhangList.get(i);

        holder.tvTensp.setText(donhang.getMaHd());
        holder.tvDiachiNhanHang.setText(donhang.getDiaChiGiao());

        if(donhang.getTrangThai()==false)
        {
            holder.tvSoluong.setText("Đơn hàng chưa được giao");
            holder.tvSoluong.setTextColor(Color.rgb(254, 0, 0));
        }
        else {
            holder.tvSoluong.setText("Đơn hàng đã được giao");
            holder.tvSoluong.setTextColor(Color.rgb(14, 203, 0));

        }


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        //holder.tvTongtien.setText(decimalFormat.format(Integer.parseInt(donhang.getGiasp()))+" Đ");
        holder.tvNgaydathang.setText(donhang.getNgayLap());

        return view;
    }

    class Holder{
        TextView tvTensp, tvSoluong, tvTongtien, tvNgaydathang, tvDiachiNhanHang;
    }

}
