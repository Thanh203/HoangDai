package com.example.doanv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.doanv2.adapter.ItemCallback;
import com.example.doanv2.adapter.LichSuAdapter;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;
import com.example.doanv2.model.MonAnDonGian;

import java.util.ArrayList;

public class LichSuActivity extends AppCompatActivity implements ItemCallback {
RecyclerView rvLichSu;
LichSuAdapter lichSuAdapter;
ArrayList<MonAnDonGian> monAnDonGians;
MonAnDB monAnDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);
        monAnDB = new MonAnDB(this);
        monAnDonGians = monAnDB.getLstLichSu();
        rvLichSu = findViewById(R.id.rvLichSu);
        lichSuAdapter = new LichSuAdapter(monAnDonGians, this, this);
        rvLichSu.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rvLichSu.setAdapter(lichSuAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lịch sử");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.baseline_arrow_back_ios_new_24_white);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if(item.getItemId() == R.id.iRemoveAll)
        {
            monAnDB.DeleteAllLichSu();
            LinearLayout llLichSu = findViewById(R.id.llLichSu);
            llLichSu.removeAllViews();
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInf = getMenuInflater();
        menuInf.inflate(R.menu.mnlichsu, menu);
        return true;
    }
}