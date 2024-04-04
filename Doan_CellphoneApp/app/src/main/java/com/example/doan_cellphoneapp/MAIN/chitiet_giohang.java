package com.example.doan_cellphoneapp.MAIN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.TextView;

import com.example.doan_cellphoneapp.R;

public class chitiet_giohang extends AppCompatActivity {

    AppCompatButton btnchitietCong, btnchitietTru;
    TextView tvchitietSo;
    int soluong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_giohang);
    }
}