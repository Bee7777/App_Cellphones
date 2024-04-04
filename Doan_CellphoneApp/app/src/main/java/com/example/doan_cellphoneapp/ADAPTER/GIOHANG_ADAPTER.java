package com.example.doan_cellphoneapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import lombok.NonNull;

public class GIOHANG_ADAPTER extends RecyclerView.Adapter<GIOHANG_ADAPTER.GIOHANGVIEWHOLDER> {
    private List<DIENTHOAI> data;
    private Context context;

    public GIOHANG_ADAPTER(List<DIENTHOAI> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @androidx.annotation.NonNull
    @Override
    public GIOHANGVIEWHOLDER onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chitiet_giohang, parent, false);
        return new GIOHANGVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull GIOHANGVIEWHOLDER holder, int position) {
        DIENTHOAI dienthoai = data.get(position);

        Picasso.get().load(SERVER.pathImages + dienthoai.hinhsanpham).into(holder.imghinhgiohang);
        holder.tvtengiohang.setText(dienthoai.tensanpham);

        String dongia = NumberFormat.getNumberInstance(Locale.getDefault()).format(Double.parseDouble(dienthoai.dongia));
        holder.tvgiagiohang.setText(dongia);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


   static class GIOHANGVIEWHOLDER extends RecyclerView.ViewHolder{
            ImageView imghinhgiohang;
            TextView tvtengiohang, tvgiagiohang;

            public GIOHANGVIEWHOLDER(@NonNull View itemView){
                super(itemView);
                imghinhgiohang = itemView.findViewById(R.id.imghinhgiohang);
                tvtengiohang = itemView.findViewById(R.id.tvtengiohang);
                tvgiagiohang = itemView.findViewById(R.id.tvtengiohang);
            }

        }
    }

