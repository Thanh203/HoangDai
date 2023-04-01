package com.example.doanv2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doanv2.adapter.ItemCallback;
import com.example.doanv2.adapter.LichSuAdapter;
import com.example.doanv2.adapter.MonAnAdapter;
import com.example.doanv2.adapter.MonAnChiTietDanhMucAdapter;
import com.example.doanv2.adapter.YeuThichAdapter;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;
import com.example.doanv2.model.MonAnDonGian;

import java.util.ArrayList;

public class YeuThichActivity extends AppCompatActivity implements ItemCallback {
    RecyclerView rvYeuThich;
    YeuThichAdapter yeuThichAdapter;
    ArrayList<MonAnDonGian> monAnDonGians;
    MonAnDB monAnDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich);
        monAnDB = new MonAnDB(this);
        monAnDonGians = monAnDB.getLstYeuThich();
        rvYeuThich = findViewById(R.id.rvYeuThich);
        yeuThichAdapter = new YeuThichAdapter(monAnDonGians, this, this);
        rvYeuThich.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rvYeuThich.setAdapter(yeuThichAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Yêu thích");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.baseline_arrow_back_ios_new_24_white);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(String id) {
        String[] fixInput = id.split(":");
        MonAn monAn = monAnDB.getMonAnByID(fixInput[1]);
        monAnDB.DeleteLichSu(fixInput[1]);
        monAnDB.InsertLichSu(fixInput[1], monAn.getTenMonAn(), monAn.getAnh());
        Intent i = new Intent(this, ChiTietMonAnActivity.class);
        i.putExtra("idMonAn", fixInput[1]);
        startActivity(i);
    }
}