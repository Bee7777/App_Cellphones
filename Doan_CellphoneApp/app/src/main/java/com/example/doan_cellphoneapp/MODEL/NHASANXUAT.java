package com.example.doan_cellphoneapp.MODEL;

import java.io.Serializable;

public class NHASANXUAT implements Serializable {
    public String idhang, tenhang, hinhhang;

    public NHASANXUAT(String idhang, String tenhang, String hinhhang) {
        this.idhang = idhang;
        this.tenhang = tenhang;
        this.hinhhang = hinhhang;
    }

    public NHASANXUAT() {
    }
}
