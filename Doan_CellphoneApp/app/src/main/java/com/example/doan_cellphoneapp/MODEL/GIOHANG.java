package com.example.doan_cellphoneapp.MODEL;

public class GIOHANG {
    int idsanpham, soluong;
    long dongia;

    public GIOHANG(int idsanpham, int soluong, long dongia, String idhang, String hinhsanpham, String tensanpham, String noiban, String mausanpham, String ngaycapnhat, String mota) {
        this.idsanpham = idsanpham;
        this.soluong = soluong;
        this.dongia = dongia;
        this.idhang = idhang;
        this.hinhsanpham = hinhsanpham;
        this.tensanpham = tensanpham;
        this.noiban = noiban;
        this.mausanpham = mausanpham;
        this.ngaycapnhat = ngaycapnhat;
        this.mota = mota;
    }

    public GIOHANG() {
    }

    public String idhang, hinhsanpham, tensanpham, noiban, mausanpham, ngaycapnhat, mota;

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getDongia() {
        return dongia;
    }

    public void setDongia(long dongia) {
        this.dongia = dongia;
    }

    public String getIdhang() {
        return idhang;
    }

    public void setIdhang(String idhang) {
        this.idhang = idhang;
    }

    public String getHinhsanpham() {
        return hinhsanpham;
    }

    public void setHinhsanpham(String hinhsanpham) {
        this.hinhsanpham = hinhsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getNoiban() {
        return noiban;
    }

    public void setNoiban(String noiban) {
        this.noiban = noiban;
    }

    public String getMausanpham() {
        return mausanpham;
    }

    public void setMausanpham(String mausanpham) {
        this.mausanpham = mausanpham;
    }

    public String getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(String ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
