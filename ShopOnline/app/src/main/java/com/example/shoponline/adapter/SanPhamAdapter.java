package com.example.shoponline.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
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

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.Holder> {
    private Activity context;
    private List<Sanpham> sanPhamList;

    public SanPhamAdapter(Activity context, List<Sanpham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Sanpham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getHinhAnh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivDienthoai);
        holder.tvTenDt.setText(sanPham.getTenSp());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaDt.setText("Giá: "+decimalFormat.format(sanPham.getGiaBan())+" Đ");

        holder.tvMotaDt.setMaxLines(2);
        holder.tvMotaDt.setEllipsize(TextUtils.TruncateAt.END); //kiểu dấu 3 chấm nếu mô tả dài quá.
        holder.tvMotaDt.setText(sanPham.getMoTa());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        if(sanPhamList != null) {
            return sanPhamList.size();
        }
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivDienthoai;
        TextView tvTenDt, tvGiaDt, tvMotaDt;

        public Holder(View itemView) {
            super(itemView);
            ivDienthoai = itemView.findViewById(R.id.iv_dienthoai);
            tvTenDt = itemView.findViewById(R.id.tv_tendt);
            tvGiaDt = itemView.findViewById(R.id.tv_giadt);
            tvMotaDt = itemView.findViewById(R.id.tv_motadt);
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
