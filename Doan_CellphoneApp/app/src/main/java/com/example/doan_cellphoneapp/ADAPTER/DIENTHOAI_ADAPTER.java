package com.example.doan_cellphoneapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_cellphoneapp.MAIN.ChiTietSanPham;
import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DIENTHOAI_ADAPTER extends RecyclerView.Adapter<DIENTHOAI_ADAPTER.ViewHolder> {
    Context context;
    ArrayList<DIENTHOAI> mangdienthoai;

    public DIENTHOAI_ADAPTER(Context context, ArrayList<DIENTHOAI> mangdienthoai) {
        this.context = context;
        this.mangdienthoai = mangdienthoai;
    }
    //Dua layout vao bien view de su dung nhieu lan
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_sanpham, parent, false);
        return new ViewHolder(view);
    }

    //lay tu Server
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DIENTHOAI dienthoai = mangdienthoai.get(position);

        Picasso.get().load(SERVER.pathsanpham+dienthoai.getHinhsanpham()).into(holder.imageView);
        holder.tensanpham.setText(dienthoai.getTensanpham());
        holder.giasanpham.setText(dienthoai.getDongia());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("sanpham",dienthoai);

                context.startActivity(intent);

                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangdienthoai.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tensanpham, giasanpham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imghinhminhhoa);
            tensanpham = itemView.findViewById(R.id.tvtendt);
            giasanpham = itemView.findViewById(R.id.tvdongia);

        }
    }
}
