package com.example.doanv2.model;

public class DanhMuc {
    String IDDanhMuc;
    String AnhDanhMuc;
    String TenDanhMuc;

    public DanhMuc(String IDDanhMuc, String anhDanhMuc, String tenDanhMuc) {
        this.IDDanhMuc = IDDanhMuc;
        AnhDanhMuc = anhDanhMuc;
        TenDanhMuc = tenDanhMuc;
    }

    public String getIDDanhMuc() {
        return IDDanhMuc;
    }

    public void setIDDanhMuc(String IDDanhMuc) {
        this.IDDanhMuc = IDDanhMuc;
    }

    public String getAnhDanhMuc() {
        return AnhDanhMuc;
    }

    public void setAnhDanhMuc(String anhDanhMuc) {
        AnhDanhMuc = anhDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }
}
