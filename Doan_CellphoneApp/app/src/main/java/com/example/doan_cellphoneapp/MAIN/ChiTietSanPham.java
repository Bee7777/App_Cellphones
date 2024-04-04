package com.example.doan_cellphoneapp.MAIN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.MODEL.GIOHANG;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

public class ChiTietSanPham extends AppCompatActivity {
    private DIENTHOAI dienthoai;
    int soluong = 0;
    ImageView imgChitietDT;
    TextView tvChitietTenDT, tvGia, tvchitietSo, tvchitietMotaDT, tvchitietMau, tvchitietNoiban;
    AppCompatButton btnchitietCong, btnchitietTru, btnchitietThem;
    DIENTHOAI dt;
    NotificationBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        imgChitietDT = findViewById(R.id.imgChitietDT);
        tvChitietTenDT = findViewById(R.id.tvChitietTenDT);
        tvGia = findViewById(R.id.tvGia);
        tvchitietSo = findViewById(R.id.tvchitietSo);
        btnchitietCong = findViewById(R.id.btnchitietCong);
        btnchitietTru = findViewById(R.id.btnchitietTru);
        btnchitietThem = findViewById(R.id.btnchitietThem);
        tvchitietMotaDT = findViewById(R.id.tvchitietMotaDT);
        tvchitietMau = findViewById(R.id.tvchitietMau);
        tvchitietNoiban = findViewById(R.id.tvchitietNoiban);
        tvchitietSo = findViewById(R.id.tvchitietSo);
        badge = findViewById(R.id.menu_soluong);
        initCotrol();


        Intent intent = getIntent();
        dienthoai = (DIENTHOAI) intent.getSerializableExtra("sanpham");

        Picasso.get().load(SERVER.pathsanpham + dienthoai.getHinhsanpham()).into(imgChitietDT);
        tvChitietTenDT.setText(dienthoai.getTensanpham());
        tvGia.setText(dienthoai.getDongia());
        tvchitietMau.setText(dienthoai.getMausanpham());
        tvchitietNoiban.setText(dienthoai.getNoiban());
        tvchitietMotaDT.setText(dienthoai.getMota());

        btnchitietCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong++;
                tvchitietSo.setText("" + soluong);
            }
        });
        btnchitietTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong--;
                if (soluong < 0)
                    soluong = 0;
                tvchitietSo.setText("" + soluong);
            }
        });

    }

    private void initCotrol() {
        btnchitietThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themgiohang();
            }
        });
    }

    private void themgiohang() {
        if (SERVER.manggiohang.size() > 0) {
            boolean flag = false;
            String soluongText = tvchitietSo.getText().toString();
            int soluong = Integer.parseInt(soluongText);
            for (int i = 0; i < SERVER.manggiohang.size(); i++) {
                if (SERVER.manggiohang.get(i).getIdsanpham() == dt.getIdsanpham()) {
                    SERVER.manggiohang.get(i).setSoluong(soluong + SERVER.manggiohang.get(i).getSoluong());
                    long gia = Long.parseLong(dt.getDongia()) * SERVER.manggiohang.get(i).getSoluong();
                    SERVER.manggiohang.get(i).setDongia(gia);
                    flag = true;
                }
            }
            if (!flag) {
                long gia = Long.parseLong(dt.getDongia()) * soluong;
                GIOHANG giohang = new GIOHANG();
                giohang.setDongia(gia);
                giohang.setSoluong(soluong);
                giohang.setIdsanpham(dt.getIdsanpham());
                giohang.setHinhsanpham(dt.getHinhsanpham());


                giohang.setMota(dt.getMota());
                SERVER.manggiohang.add(giohang);
            }
        } else {
            String soluongText = tvchitietSo.getText().toString();
            int soluong = Integer.parseInt(soluongText);
            long gia = Long.parseLong(dt.getDongia()) * soluong;
            GIOHANG giohang = new GIOHANG();
            giohang.setDongia(gia);
            giohang.setSoluong(soluong);
            giohang.setIdsanpham(dt.getIdsanpham());
            giohang.setHinhsanpham(dt.getHinhsanpham());

            giohang.setMota(dt.getMota());
            SERVER.manggiohang.add(giohang);
        }
        badge.setText(String.valueOf(SERVER.manggiohang.size()));
    }
}
