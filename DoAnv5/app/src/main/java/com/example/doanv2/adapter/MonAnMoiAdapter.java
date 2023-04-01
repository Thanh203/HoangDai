package com.example.doanv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanv2.R;
import com.example.doanv2.Utils;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;
import com.example.doanv2.model.MonAnDonGian;

import java.util.ArrayList;

public class MonAnMoiAdapter extends RecyclerView.Adapter<MonAnMoiAdapter.MonAnMoiVH>{
    ArrayList<MonAn> cacMonAn;
    Context context;
    ItemCallback itemCallback;
    public MonAnMoiAdapter(ArrayList<MonAn> cacMonAn,Context context ,ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
        this.cacMonAn = cacMonAn;
        this.context = context;
    }

    @NonNull
    @Override
    public MonAnMoiVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layoutitem1, parent, false);
        return new MonAnMoiVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnMoiVH holder, int position) {
        MonAn monAn =  cacMonAn.get(position);
        holder.Anh.setImageBitmap(Utils.converToBitmapFromAssets(context, monAn.getAnh()));
        holder.TenMonAn.setText(monAn.getTenMonAn());
        holder.itemView.setOnClickListener(view -> itemCallback.onItemClick("Mon an:" + String.valueOf(monAn.getIDMonAn())));


        MonAnDB monAnDB = new MonAnDB(context);

        if(monAnDB.checkYeuThich(String.valueOf(monAn.getIDMonAn())))
            holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_24_red);
        else
            holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_border_24);

        holder.ibLYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(monAnDB.checkYeuThich(String.valueOf(monAn.getIDMonAn())))
                {
                    monAnDB.DeleteYeuThich(String.valueOf(monAn.getIDMonAn()));
                    holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_border_24);
                }else{
                    monAnDB.DeleteYeuThich(String.valueOf(monAn.getIDMonAn()));
                    monAnDB.InsertYeuThich(String.valueOf(monAn.getIDMonAn()),monAn.getTenMonAn(),monAn.getAnh());
                    holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_24_red);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cacMonAn.size();
    }

    class MonAnMoiVH extends RecyclerView.ViewHolder{
        ImageView Anh;
        TextView TenMonAn;
        ImageButton ibLYeuThich;
        public MonAnMoiVH(@NonNull View itemView) {
            super(itemView);
            Anh = itemView.findViewById(R.id.ivAnh);
            TenMonAn = itemView.findViewById((R.id.tvTenMonAn));
            ibLYeuThich = itemView.findViewById(R.id.ibLYeuThich);
        }
    }
}
