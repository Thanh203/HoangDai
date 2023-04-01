package com.example.doanv2.model;

public class MonAnDonGian {
    private int IDMonAn;
    private String TenMonAn;
    private String Anh;
    //

    public MonAnDonGian(int IDMonAn, String tenMonAn, String anh) {
        this.IDMonAn = IDMonAn;
        TenMonAn = tenMonAn;
        Anh = anh;
    }

    public int getIDMonAn() {
        return IDMonAn;
    }

    public void setIDMonAn(int IDMonAn) {
        this.IDMonAn = IDMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }
}
