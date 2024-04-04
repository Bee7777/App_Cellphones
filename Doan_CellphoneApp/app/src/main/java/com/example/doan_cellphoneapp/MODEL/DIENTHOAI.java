package com.example.doan_cellphoneapp.MODEL;

import java.io.Serializable;

public class DIENTHOAI implements Serializable {
    int idsanpham, soluong;
    public String idhang, hinhsanpham, tensanpham, dongia, noiban, mausanpham, ngaycapnhat, mota;

    public DIENTHOAI(int idsanpham, int soluong, String idhang, String hinhsanpham, String tensanpham, String dongia, String noiban, String mausanpham, String ngaycapnhat, String mota) {
        this.idsanpham = idsanpham;
        this.soluong = soluong;
        this.idhang = idhang;
        this.hinhsanpham = hinhsanpham;
        this.tensanpham = tensanpham;
        this.dongia = dongia;
        this.noiban = noiban;
        this.mausanpham = mausanpham;
        this.ngaycapnhat = ngaycapnhat;
        this.mota = mota;
    }

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

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
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

