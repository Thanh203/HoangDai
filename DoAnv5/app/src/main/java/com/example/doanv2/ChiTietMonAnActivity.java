package com.example.doanv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ChiTietMonAnActivity extends AppCompatActivity {
    ImageView ivAnhMonAnC;
    BottomNavigationView mnBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        mnBottom = findViewById(R.id.navMonAn);

        MonAnDB monAnDB = new MonAnDB(this);
        Bundle goi = getIntent().getExtras();
        String id = goi.getString("idMonAn");
        MonAn monAn = monAnDB.getMonAnByID(id);

        loadFragment(new NguyenLieuFragment(monAn.getNguyenLieu()));
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivAnhMonAnC = this.<ImageView>findViewById(R.id.ivAnhMonAn);

        //
        getSupportActionBar().setTitle(monAn.getTenMonAn());
        ivAnhMonAnC.setImageBitmap(Utils.converToBitmapFromAssets(getBaseContext(), monAn.getAnh()));

        mnBottom.setOnItemSelectedListener(getListener());
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                MonAnDB monAnDB = new MonAnDB(ChiTietMonAnActivity.this);
                Bundle goi = getIntent().getExtras();
                String id = goi.getString("idMonAn");
                MonAn monAn = monAnDB.getMonAnByID(id);
                switch (item.getItemId()) {
                    case R.id.mnNguyenLieu:
                        loadFragment(new NguyenLieuFragment(monAn.getNguyenLieu()));
                        return true;
                    case R.id.mnCheBien:
                        loadFragment(new CheBienFragment(monAn.getNoiDung()));
                        return true;
                    case R.id.mnVideo:
                        loadFragment(new VideoFragment(monAn.getVideo()));
                        return true;
                }
                return true;
            }

        };
    }
    void loadFragment (Fragment fmNew)
    {
        FragmentTransaction fmCN= getSupportFragmentManager().beginTransaction();
        fmCN.replace(R.id.monan_fragment, fmNew);
        fmCN.addToBackStack(null);
        fmCN.commit();
    }
}