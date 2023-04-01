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

public class TatCaDanhMucAdapter extends RecyclerView.Adapter<TatCaDanhMucAdapter.TatCaDanhMucVH> {
    ArrayList<DanhMuc> lstMonAn;
    Context context;
    ItemCallback itemCallback;
    public TatCaDanhMucAdapter(ArrayList<DanhMuc> lstMonAn, Context context, ItemCallback itemCallback) {
        this.lstMonAn = lstMonAn;
        this.context = context;
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override
    public TatCaDanhMucVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layoutitem6, parent, false);
        return new TatCaDanhMucAdapter.TatCaDanhMucVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TatCaDanhMucVH holder, int position) {
        DanhMuc danhMuc =  lstMonAn.get(position);

        holder.Anh.setImageBitmap(Utils.converToBitmapFromAssets(context, danhMuc.getAnhDanhMuc()));
        holder.TenMonAn.setText(danhMuc.getTenDanhMuc());

        holder.itemView.setOnClickListener(view -> itemCallback.onItemClick(String.valueOf("Danh muc:" + danhMuc.getIDDanhMuc())));

    }

    @Override
    public int getItemCount() {
        return lstMonAn.size();
    }

    class TatCaDanhMucVH extends RecyclerView.ViewHolder{
        ImageView Anh;
        TextView TenMonAn;
        public TatCaDanhMucVH(@NonNull View itemView) {
            super(itemView);
            Anh = itemView.findViewById(R.id.ivImage);
            TenMonAn = itemView.findViewById((R.id.tvName));
        }
    }
}
