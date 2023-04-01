package com.example.doanv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.doanv2.adapter.GoiYAdapter;
import com.example.doanv2.adapter.ItemCallback;
import com.example.doanv2.adapter.MonAnAdapter;
import com.example.doanv2.adapter.MonAnChiTietDanhMucAdapter;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ItemCallback {
SearchView searchView;
RecyclerView rvSearch;
MonAnChiTietDanhMucAdapter searchAdapter;
ArrayList<MonAn> lstMonAn, lstGoiY;
RecyclerView rvSuggest;
GoiYAdapter goiYAdapter;
ImageButton ibBack;
MonAnDB monAnDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        searchView = findViewById(R.id.svTimKiem);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(getListenerSearch());

        monAnDB = new MonAnDB(SearchActivity.this);
        lstMonAn = monAnDB.getLstMonAn();

        rvSearch = findViewById(R.id.rvSearch);
        searchAdapter = new MonAnChiTietDanhMucAdapter(lstMonAn,this ,this);
        rvSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvSearch.setAdapter(searchAdapter);

        lstGoiY = new ArrayList<>();
        lstGoiY.add(lstMonAn.get(1));
        lstGoiY.add(lstMonAn.get(2));
        lstGoiY.add(lstMonAn.get(3));
        lstGoiY.add(lstMonAn.get(4));
        lstGoiY.add(lstMonAn.get(5));

        rvSuggest = findViewById(R.id.rvSuggest);
        goiYAdapter = new GoiYAdapter(lstGoiY, this ,this);
        rvSuggest.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        rvSuggest.setAdapter(goiYAdapter);



        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(v -> finish());
    }

    @NonNull
    private SearchView.OnQueryTextListener getListenerSearch() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        };
    }

    private void filterList(String newText) {
        ArrayList<MonAn> filterLstMonAn = new ArrayList<>();
        for(MonAn monAn : lstMonAn){
            if(monAn.getTenMonAn().toLowerCase().contains(newText.toLowerCase()))
                filterLstMonAn.add(monAn);
        }
        searchAdapter.setFilterList(filterLstMonAn);
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