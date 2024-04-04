package com.example.doan_cellphoneapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_cellphoneapp.MODEL.QUANGCAO;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QUANGCAO_ADAPTER extends RecyclerView.Adapter<QUANGCAO_ADAPTER.VIEWHODER> {
    Context context;
    ArrayList<QUANGCAO> data;

    public QUANGCAO_ADAPTER(Context context, ArrayList<QUANGCAO> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHODER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_qc,parent,false);
        return new VIEWHODER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHODER holder, int position) {
        QUANGCAO quangcao = data.get(position);

        Picasso.get().load(SERVER.pathquangcao+quangcao.hinhquangcao).into(holder.imgslide1);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class VIEWHODER extends RecyclerView.ViewHolder{
        ImageView imgslide1;

        public VIEWHODER(@NonNull View itemView) {
            super(itemView);
            imgslide1 = itemView.findViewById(R.id.imgslide1);
        }
    }
}
