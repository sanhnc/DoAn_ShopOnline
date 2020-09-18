package com.example.shoponline.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.shoponline.fragment.GioHangFragment;
import com.example.shoponline.R;
import com.example.shoponline.model.GioHang;
import com.example.shoponline.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by muase on 3/18/2018.
 */

public class GioHangAdapter extends BaseAdapter {

    private Activity context;
    private List<GioHang> gioHangList;

    public GioHangAdapter(Activity context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @Override
    public Object getItem(int i) {
        return gioHangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final Holder holder;
        if(view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_giohang,null);
            holder.ivGiohang = view.findViewById(R.id.iv_giohang);
            holder.tvTengiohang = view.findViewById(R.id.tv_tengiohang);
            holder.tvGiagiohang = view.findViewById(R.id.tv_giagiohang);
            holder.btnCong = view.findViewById(R.id.btn_cong);
            holder.btnSoluong = view.findViewById(R.id.btn_soluong);
            holder.btnTru = view.findViewById(R.id.btn_tru);
            holder.btnDelete = view.findViewById(R.id.btn_delete);

            view.setTag(holder);

        }else holder = (Holder) view.getTag();

        GioHang gioHang = gioHangList.get(i);
        holder.tvTengiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiagiohang.setText(decimalFormat.format(gioHang.getGiasp())+" Đ");
        Picasso.with(context).load(gioHang.getHinhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivGiohang);
        holder.btnSoluong.setText(gioHang.getSoluong()+"");

        int sl = Integer.parseInt(holder.btnSoluong.getText().toString());
        if(sl>=10){
            holder.btnCong.setEnabled(false);
        }else
            if(sl==1){
                holder.btnCong.setEnabled(true);
                holder.btnTru.setEnabled(false);
            }else
            if(sl>1){
                holder.btnCong.setEnabled(true);
                holder.btnTru.setEnabled(true);
            }


        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(holder.btnSoluong.getText().toString())+1;
                int slhientai = MainActivity.mangGioHang.get(i).getSoluong();
                long giahientai = MainActivity.mangGioHang.get(i).getGiasp();
                MainActivity.mangGioHang.get(i).setSoluong(slmoinhat);
                long giamoinhat = giahientai*slmoinhat/slhientai;
                MainActivity.mangGioHang.get(i).setGiasp(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvGiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");
                loadFragmentUI();
                //load lại ui tổng tiền ben fragment?

                if (slmoinhat>9){
                    holder.btnCong.setEnabled(false);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }else {
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(holder.btnSoluong.getText().toString())-1;
                int slhientai = MainActivity.mangGioHang.get(i).getSoluong();
                long giahientai = MainActivity.mangGioHang.get(i).getGiasp();
                MainActivity.mangGioHang.get(i).setSoluong(slmoinhat);
                long giamoinhat = giahientai*slmoinhat/slhientai;
                MainActivity.mangGioHang.get(i).setGiasp(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvGiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");
                loadFragmentUI();
             //load lại ui tổng tiền ben fragment?

                if (slmoinhat<2){
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(false);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }else {
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Xóa thành công sản phẩm "+MainActivity.mangGioHang.get(i).getTensp(), Toast.LENGTH_SHORT).show();
                MainActivity.mangGioHang.remove(i);
                loadFragmentUI();
                //MainActivity.setSoluong(MainActivity.mangGioHang.size());
            }
        });

        return view;
    }

    class Holder{
        ImageView ivGiohang, btnDelete;
        TextView tvTengiohang, tvGiagiohang;
        Button btnCong, btnTru, btnSoluong;
    }

    public void loadFragmentUI(){

        androidx.fragment.app.FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, new GioHangFragment());
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
