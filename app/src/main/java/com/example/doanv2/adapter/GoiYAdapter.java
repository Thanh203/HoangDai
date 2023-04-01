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
import com.example.doanv2.model.MonAn;

import java.util.ArrayList;

public class GoiYAdapter extends RecyclerView.Adapter<GoiYAdapter.GoiYVH>{
    ArrayList<MonAn> cacMonAn;
    Context context;
    ItemCallback itemCallback;
    public GoiYAdapter(ArrayList cacMonAn,Context context ,ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
        this.cacMonAn = cacMonAn;
        this.context = context;
    }

    @NonNull
    @Override
    public GoiYVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layoutitem4, parent, false);
        return new GoiYVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoiYVH holder, int position) {
        MonAn monAn =  cacMonAn.get(position);

        holder.TenMonAn.setText(monAn.getTenMonAn());

        holder.itemView.setOnClickListener(view -> itemCallback.onItemClick("Goi Y:" + monAn.getIDMonAn()));
    }

    @Override
    public int getItemCount() {
        return cacMonAn.size();
    }

    class GoiYVH extends RecyclerView.ViewHolder{
        TextView TenMonAn;
        public GoiYVH(@NonNull View itemView) {
            super(itemView);
            TenMonAn = itemView.findViewById((R.id.tvName4));
        }
    }
}
