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
import com.example.doanv2.model.MonAnDB;
import com.example.doanv2.model.MonAnDonGian;

import java.util.ArrayList;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.LichSuVH>{
    ArrayList<MonAnDonGian> cacMonAn;
    Context context;
    ItemCallback itemCallback;

    public LichSuAdapter(ArrayList<MonAnDonGian> cacMonAn, Context context, ItemCallback itemCallback) {
        this.cacMonAn = cacMonAn;
        this.context = context;
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override

    public LichSuVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layoutitem5, parent, false);
        return new LichSuVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuVH holder, int position) {
        MonAnDonGian monAn =  cacMonAn.get(position);
        holder.Anh.setImageBitmap(Utils.converToBitmapFromAssets(context, monAn.getAnh()));
        holder.TenMonAn.setText(monAn.getTenMonAn());
        holder.itemView.setOnClickListener(view -> itemCallback.onItemClick("Mon an:" + String.valueOf(monAn.getIDMonAn())));


        MonAnDB monAnDB = new MonAnDB(context);
        if(monAnDB.checkYeuThich(String.valueOf(monAn.getIDMonAn())))
            holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_24_red);
        else
            holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_border_24);

        holder.ibLYeuThich.setOnClickListener(FavoriteClick(holder, monAn, monAnDB));
        holder.ibRemove.setOnClickListener(RemoveClick(position, monAn.getIDMonAn()));
    }

    @NonNull
    private View.OnClickListener RemoveClick(int position, int id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonAnDB monAnDB = new MonAnDB(context);
                monAnDB.DeleteLichSu(String.valueOf(id));
                cacMonAn.remove(position);
                notifyItemRemoved(position);
            }
        };
    }

    @NonNull
    private View.OnClickListener FavoriteClick(@NonNull LichSuVH holder, MonAnDonGian monAn, MonAnDB monAnDB) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (monAnDB.checkYeuThich(String.valueOf(monAn.getIDMonAn()))) {
                    monAnDB.DeleteYeuThich(String.valueOf(monAn.getIDMonAn()));
                    holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_border_24);
                } else {
                    monAnDB.DeleteYeuThich(String.valueOf(monAn.getIDMonAn()));
                    monAnDB.InsertYeuThich(String.valueOf(monAn.getIDMonAn()), monAn.getTenMonAn(), monAn.getAnh());
                    holder.ibLYeuThich.setImageResource(R.drawable.baseline_favorite_24_red);

                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return cacMonAn.size();
    }

    class LichSuVH extends RecyclerView.ViewHolder{
        ImageView Anh;
        TextView TenMonAn;
        ImageButton ibLYeuThich;
        ImageButton ibRemove;
        public LichSuVH(@NonNull View itemview){
            super(itemview);
            Anh = itemView.findViewById(R.id.ivImage);
            TenMonAn = itemView.findViewById((R.id.tvName));
            ibLYeuThich = itemView.findViewById(R.id.ibLYeuThich2);
            ibRemove = itemview.findViewById(R.id.ibRemove);
        }
    }
}
