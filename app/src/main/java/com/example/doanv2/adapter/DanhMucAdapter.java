package com.example.doanv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanv2.R;
import com.example.doanv2.Utils;
import com.example.doanv2.model.DanhMuc;
import com.example.doanv2.model.MonAn;

import java.util.ArrayList;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.DanhMucVH> {
    ArrayList<DanhMuc> lstDanhMuc;
    Context context;
    ItemCallback itemCallback;
    public DanhMucAdapter(ArrayList<DanhMuc> lstDanhMuc, Context context, ItemCallback itemCallback) {
        this.lstDanhMuc = lstDanhMuc;
        this.context = context;
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override
    public DanhMucVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layoutitem3, parent, false);
        return new DanhMucAdapter.DanhMucVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucVH holder, int position) {
        DanhMuc danhMuc =  lstDanhMuc.get(position);
        holder.Anh.setImageBitmap(Utils.converToBitmapFromAssets(context, danhMuc.getAnhDanhMuc()));
        holder.TenDanhMuc.setText(danhMuc.getTenDanhMuc());
        holder.itemView.setOnClickListener(view -> itemCallback.onItemClick("Danh muc:" + String.valueOf(danhMuc.getIDDanhMuc())));
    }

    @Override
    public int getItemCount() {
        return lstDanhMuc.size();
    }

    class DanhMucVH extends RecyclerView.ViewHolder{
        ImageView Anh;
        TextView TenDanhMuc;
        public DanhMucVH(@NonNull View itemView) {
            super(itemView);
            Anh = itemView.findViewById(R.id.ivImage);
            TenDanhMuc = itemView.findViewById((R.id.tvName));
        }
    }
}
