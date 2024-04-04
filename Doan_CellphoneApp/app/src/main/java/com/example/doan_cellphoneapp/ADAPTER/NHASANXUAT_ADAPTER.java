package com.example.doan_cellphoneapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_cellphoneapp.MAIN.THEONHASANXUAT;
import com.example.doan_cellphoneapp.MODEL.NHASANXUAT;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NHASANXUAT_ADAPTER extends RecyclerView.Adapter<NHASANXUAT_ADAPTER.NHASANXUATVIEWHOLDER> {
    Context context;
    ArrayList<NHASANXUAT>data;

    public NHASANXUAT_ADAPTER(Context context, ArrayList<NHASANXUAT> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NHASANXUATVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_chude, null);
        return new NHASANXUATVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NHASANXUATVIEWHOLDER holder, int position) {
        NHASANXUAT nhasanxuat = data.get(position);
        Picasso.get().load(SERVER.pathImages + nhasanxuat.hinhhang).into(holder.imghinhchude);
        holder.tvtenchude.setText(nhasanxuat.tenhang);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, THEONHASANXUAT.class);
                intent.putExtra("hangdienthoai",nhasanxuat);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    class NHASANXUATVIEWHOLDER extends RecyclerView.ViewHolder{
        ImageView imghinhchude;
        TextView tvtenchude;

        public NHASANXUATVIEWHOLDER(@NonNull View itemView) {
            super(itemView);
            imghinhchude = itemView.findViewById(R.id.imghinhchude);
            tvtenchude = itemView.findViewById(R.id.tvtenchude);
        }
    }
}
