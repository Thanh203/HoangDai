package com.example.doanv2;

import android.app.Application;

import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;

import java.util.ArrayList;

public class App extends Application {
    public static ArrayList<MonAn> data;
    @Override
    public void onCreate() {
        super.onCreate();
        MonAnDB monAnDB = new MonAnDB(this);
        monAnDB.createTableMonAn();
        monAnDB.createTableDanhMuc();
        monAnDB.createTableLichSu();
        monAnDB.createTableYeuThich();
        
        if(monAnDB.countMonAn() == 0)
        {
            monAnDB.insertMonAn("Mì xào giòn", "1","2","","mi_xao_gion.jpg", 0, 0,"MX");
            monAnDB.insertMonAn("Nui xào bò", "1","2","","nui_xao_bo.jpg", 0, 0,"MX");
            monAnDB.insertMonAn("Mì xào", "1","2","","mi_xao.jpg", 0, 0,"MX");
            monAnDB.insertMonAn("Pizza", "1","2","","pizza.jpg", 0, 0,"FF");
            monAnDB.insertMonAn("Banh ngot 1", "1","2","","banh_ngot_1.jpg", 0, 0,"BN");
            monAnDB.insertMonAn("Donut", "1","2","","banh_ngot_3.jpg", 0, 0,"BN");
            monAnDB.insertMonAn("Banh ngot 2", "1","2","","banh_ngot_2.jpg", 0, 0,"BN");
            monAnDB.insertMonAn("Bò trừng", "1","2","","mon_an_1.jpg", 0, 0,"BM");
            monAnDB.insertMonAn("Hamberger", "1","2","","hamberger.jpg", 0, 0,"FF");
            monAnDB.insertMonAn("Bò bít tết", "1","2","","bo_bit_tet.jpg", 0, 0,"BM");
        }
        if(monAnDB.countDanhMuc() == 0)
        {
            monAnDB.insertDanhMuc("MX", "mon_xao.jpg","Món xào");
            monAnDB.insertDanhMuc("MC", "mon_chien.jpg","Món chiên");
            monAnDB.insertDanhMuc("C", "canh.jpg","Canh");
            monAnDB.insertDanhMuc("KV", "mon_khai_vi.jpg","Món khai vị");
            monAnDB.insertDanhMuc("BM", "banh_mi_op_la.jpg","Các món ăn với bánh mình");
            monAnDB.insertDanhMuc("FF", "fast_food.jpg","Thức ăn nhanh");
            monAnDB.insertDanhMuc("BN", "banh_ngot.jpg","Bánh ngọt");
        }
    }

}