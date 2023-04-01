package com.example.doanv2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doanv2.adapter.ItemCallback;
import com.example.doanv2.adapter.MonAnChiTietDanhMucAdapter;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;

import java.util.ArrayList;

public class ChiTietDanhMucActivity extends AppCompatActivity implements ItemCallback {
    RecyclerView rvMonAnDanhMuc;
    MonAnChiTietDanhMucAdapter monAnChiTietDanhMucAdapter;
    ArrayList<MonAn> lstMonAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_danh_muc);

        MonAnDB monAnDB = new MonAnDB(this);

        Bundle goi = getIntent().getExtras();
        String id = goi.getString("idDanhMuc");
        String name = goi.getString("tenDanhMuc");

        lstMonAn = monAnDB.getListMonAnByIDDanhMuc(id);
        monAnChiTietDanhMucAdapter = new MonAnChiTietDanhMucAdapter(lstMonAn,this, this);
        rvMonAnDanhMuc = findViewById(R.id.rvMonAnDanhMuc);
        rvMonAnDanhMuc.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvMonAnDanhMuc.setAdapter(monAnChiTietDanhMucAdapter);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(name);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.baseline_arrow_back_ios_new_24_white);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// API 5+ solution
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onItemClick(String id) {
        String[] fixInput = id.split(":");
        MonAnDB monAnDB = new MonAnDB(this);
        MonAn monAn = monAnDB.getMonAnByID(fixInput[1]);
        monAnDB.DeleteLichSu(fixInput[1]);
        monAnDB.InsertLichSu(fixInput[1], monAn.getTenMonAn(), monAn.getAnh());
        lstMonAn.clear();
        Intent i = new Intent(ChiTietDanhMucActivity.this, ChiTietMonAnActivity.class);
            i.putExtra("idMonAn", fixInput[1]);
            startActivity(i);
        }
}