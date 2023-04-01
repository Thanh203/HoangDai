package com.example.doanv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.doanv2.adapter.MonAnAdapter;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Món ngon mỗi ngày");

        loadFragment(new TrangChuFragment());

        mnBottom = findViewById(R.id.navMenu);
        mnBottom.setOnItemSelectedListener(getListener());
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mnTrangChu:
                        loadFragment(new TrangChuFragment());
                        return true;
                    case R.id.mnDanhMuc:
                        loadFragment(new DanhMucFragment());
                        return true;
                    case R.id.mnCaNhan:
                        loadFragment(new CaNhanFragment());
                        return true;
                }
                return true;
            }
        };
    }

    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmDai = getSupportFragmentManager().beginTransaction();
        fmDai.replace(R.id.main_fragment, fmNew);
        fmDai.addToBackStack(null);
        fmDai.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInf = getMenuInflater();
        menuInf.inflate(R.menu.menusearch, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(i);
        return true;
    }
}