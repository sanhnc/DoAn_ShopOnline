package com.example.shoponline.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.data.model.Sanpham;
import com.example.shoponline.util.Constan;
import com.example.shoponline.activity.ChiTietSPActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by muase on 3/17/2018.
 */

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.Holder> {
    private Activity context;
    private List<Sanpham> sanPhamList;

    public SanPhamMoiAdapter(Activity context, List<Sanpham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_sp_moi,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Sanpham sanPham = sanPhamList.get(position);

        Picasso.with(context).load(sanPham.getHinhanhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivSanPham);
        holder.tvTenSp.setText(sanPham.getTensp());
        //định dang tiền
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaSp.setText("Giá: "+decimalFormat.format(Integer.parseInt(sanPham.getGiasp()))+" Đ");
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivSanPham;
        TextView tvTenSp;
        TextView tvGiaSp;
        public Holder(View itemView) {
            super(itemView);

            ivSanPham = itemView.findViewById(R.id.iv_sanpham);
            tvTenSp = itemView.findViewById(R.id.tv_tensp);
            tvGiaSp = itemView.findViewById(R.id.tv_giasp);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ChiTietSPActivity.class);
            intent.putExtra(Constan.THONGTIN_SP, sanPhamList.get(getLayoutPosition()));
            context.startActivityForResult(intent,Constan.REQUEST_CODE_GIOHANG);
        }
    }
}
