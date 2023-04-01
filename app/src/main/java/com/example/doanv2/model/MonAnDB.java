package com.example.doanv2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MonAnDB {
    String dbName = "MonAnDB.db";
    Context context;
    SQLiteDatabase db;

    public MonAnDB(Context context){
        this.context = context;
    }

    public SQLiteDatabase openDB(){
        return  context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    public void createTableMonAn(){
        String sql =
                "CREATE TABLE IF NOT EXISTS tbMonAn( " +
                        "IDMonAn INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TenMonAn TEXT, NguyenLieu TEXT, NoiDung TEXT, " +
                        "Video TEXT, AnhMonAn TEXT, LichSu INTEGER, YeuThich INTEGER, IDDanhMuc TEXT)";
        db = openDB();
        db.execSQL(sql);
        db.close();
    }
    public ArrayList<MonAn> getLstMonAn(){
        ArrayList<MonAn> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tbMonAn";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext())
        {
            int IDMonAn = cursor.getInt(0);
            String TenMonAn = cursor.getString(1);
            String NguyenLieu = cursor.getString(2);
            String NoiDung = cursor.getString(3);
            String Video = cursor.getString(4);
            String Anh = cursor.getString(5);
            int YeuThich = cursor.getInt(6);
            int LichSu = cursor.getInt(7);
            String IDDanhMuc = cursor.getString(8);
            tmp.add(new MonAn(IDMonAn, TenMonAn, NguyenLieu,
                    NoiDung, Video, Anh, LichSu, YeuThich, IDDanhMuc));
        }
        db.close();
        return tmp;
    }
    public void insertMonAn(String tenMonAn, String nguyenLieu,String noiDung, String video, String anh,int LichSu, int YeuTich, String IDDanhMuc) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("TenMonAn", tenMonAn);
        cv.put("NguyenLieu", nguyenLieu);
        cv.put("NoiDung", noiDung);
        cv.put("Video", video);
        cv.put("AnhMonAn", anh);
        cv.put("LichSu", LichSu);
        cv.put("YeuThich", YeuTich);
        cv.put("IDDanhMuc", IDDanhMuc);
        db.insert("tbMonAn",null,cv);
        db.close();
    }

    public void updateMonAn(int id, String tenMonAn, String nguyenLieu,String noiDung, String video, String anh,int LichSu, int YeuTich, String IDDanhMuc){
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("TenMonAn", tenMonAn);
        cv.put("NguyenLieu", nguyenLieu);
        cv.put("NoiDung", noiDung);
        cv.put("Video", video);
        cv.put("AnhMonAn", anh);
        cv.put("LichSu", LichSu);
        cv.put("YeuThich", YeuTich);
        cv.put("IDDanhMuc", IDDanhMuc);
        db.update("tbMonAn",cv, "IDMonAn="+id,null);
        db.close();
    }
    public void deleteMonAn(int id){
        db = openDB();
        db.delete("tbMonAn", "IDMonAn="+id,null);
        db.close();
    }
    public int countMonAn(){
        String sql = "SELECT * FROM tbMonAn";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        db.close();
        return count;
    }
    public MonAn getMonAnByID(String id) {
        String sql = "SELECT * FROM tbMonAn WHERE IDMonAn = " + id;
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        MonAn tmp = null;
        if (cursor.moveToFirst()) {
            int IDMonAn = cursor.getInt(0);
            String TenMonAn = cursor.getString(1);
            String NguyenLieu = cursor.getString(2);
            String NoiDung = cursor.getString(3);
            String Video = cursor.getString(4);
            String Anh = cursor.getString(5);
            int LichSu = cursor.getInt(6);
            int YeuThich = cursor.getInt(7);
            String IDDanhMuc = cursor.getString(8);
            tmp = new MonAn(IDMonAn, TenMonAn, NguyenLieu, NoiDung, Video, Anh, LichSu, YeuThich, IDDanhMuc);
        }
        db.close();
        return tmp;
    }
    public ArrayList<MonAn> getListMonAnByIDDanhMuc(String id) {
        String sql = "SELECT * FROM tbMonAn";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<MonAn> tmp = new ArrayList<>();
        while (cursor.moveToNext()) {
            String IDDanhMuc = cursor.getString(8);
            if(id.equals(IDDanhMuc))
            {
                int IDMonAn = cursor.getInt(0);
                String TenMonAn = cursor.getString(1);
                String NguyenLieu = cursor.getString(2);
                String NoiDung = cursor.getString(3);
                String Video = cursor.getString(4);
                String Anh = cursor.getString(5);
                int LichSu = cursor.getInt(6);
                int YeuThich = cursor.getInt(7);
                tmp.add(new MonAn(IDMonAn, TenMonAn, NguyenLieu, NoiDung, Video, Anh, LichSu, YeuThich, IDDanhMuc));
            }
        }
        db.close();
        return tmp;
    }
    //------------------------------------------------------------------------------------------
    public void createTableDanhMuc(){
        String sql =
                "CREATE TABLE IF NOT EXISTS tbDanhMuc( " +
                        "IDDanhMuc TEXT PRIMARY KEY, AnhDanhMuc TEXT, TenDanhMuc TEXT)";
        db = openDB();
        db.execSQL(sql);
        db.close();
    }
    public ArrayList<DanhMuc> getLstDanhMuc(){
        ArrayList<DanhMuc> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tbDanhMuc";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext())
        {
            String IDDanhMuc = cursor.getString(0);
            String AnhDanhMuc  = cursor.getString(1);
            String TenDanhMuc = cursor.getString(2);
            tmp.add(new DanhMuc(IDDanhMuc, AnhDanhMuc, TenDanhMuc));
        }
        db.close();
        return tmp;
    }
    public void insertDanhMuc(String IDDanhMuc, String AnhDanhMuc, String TenDanhMuc) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("IDDanhMuc", IDDanhMuc);
        cv.put("AnhDanhMuc", AnhDanhMuc);
        cv.put("TenDanhMuc", TenDanhMuc);
        db.insert("tbDanhMuc",null,cv);
        db.close();
    }
    public int countDanhMuc(){
        String sql = "SELECT * FROM tbDanhMuc";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        db.close();
        return count;
    }
    public String getNameDanhMucById(String id){
        String sql = "SELECT * FROM tbDanhMuc WHERE IDDanhMuc =" + "'"+ id + "'";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToFirst())
        {
            String TenDanhMuc = cursor.getString(2);
            return TenDanhMuc;
        }
        db.close();
        return "";
    }
    //------------------------------------------------------------------------------------------
    public void createTableLichSu(){
        String sql =
                "CREATE TABLE IF NOT EXISTS tbLichSu( " +
                        "IDLichSu INT PRIMARY KEY, IDMonAn INT, TenMonAn TEXT, Anh TEXT)";
        db = openDB();
        db.execSQL(sql);
        db.close();
    }
    public ArrayList<MonAnDonGian> getLstLichSu(){
        ArrayList<MonAnDonGian> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tbLichSu ORDER BY IDLichSu DESC";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext())
        {
            int IDMonAn = cursor.getInt(1);
            String TenMonAn = cursor.getString(2);
            String Anh = cursor.getString(3);
            tmp.add(new MonAnDonGian(IDMonAn, TenMonAn, Anh));
        }
        db.close();
        return tmp;
    }
    public void InsertLichSu(String Id, String TenMonAn, String Anh) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("IDMonAn", Id);
        cv.put("TenMonAn", TenMonAn);
        cv.put("Anh", Anh);
        db.insert("tbLichSu",null,cv);
        db.close();
    }
    public void DeleteLichSu(String id){
        db = openDB();
        db.delete("tbLichSu","IDMonAn=" + id,null);
        db.close();
    }

    public void DeleteAllLichSu(){
        db = openDB();
        db.delete("tbLichSu",null,null);
        db.close();
    }

    //------------------------------------------------------------------------------------------
    public void createTableYeuThich(){
        String sql =
                "CREATE TABLE IF NOT EXISTS tbYeuThich( " +
                        "IDYeuThich INT PRIMARY KEY, IDMonAn INT, TenMonAn TEXT, Anh TEXT)";
        db = openDB();
        db.execSQL(sql);
        db.close();
    }
    public ArrayList<MonAnDonGian> getLstYeuThich(){
        ArrayList<MonAnDonGian> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tbYeuThich ORDER BY IDYeuThich DESC";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext())
        {
            int IDMonAn = cursor.getInt(1);
            String TenMonAn = cursor.getString(2);
            String Anh = cursor.getString(3);
            tmp.add(new MonAnDonGian(IDMonAn, TenMonAn, Anh));
        }
        db.close();
        return tmp;
    }
    public void InsertYeuThich(String Id, String TenMonAn, String Anh) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("IDMonAn", Id);
        cv.put("TenMonAn", TenMonAn);
        cv.put("Anh", Anh);
        db.insert("tbYeuThich",null,cv);
        db.close();
    }
    public void DeleteYeuThich(String id){
        db = openDB();
        db.delete("tbYeuThich","IDMonAn=" + id,null);
        db.close();
    }
    public boolean checkYeuThich(String id){
        String sql = "SELECT IDMonAn FROM tbYeuThich";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext())
        {
            String IDMonAn = cursor.getString(0);
            if(id.equals(IDMonAn))
            {
                db.close();
                return true;
            }
        }
        db.close();
        return false;
    }
}
