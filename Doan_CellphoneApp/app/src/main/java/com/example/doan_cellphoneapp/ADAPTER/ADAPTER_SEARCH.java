package com.example.doan_cellphoneapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_cellphoneapp.MAIN.ChiTietSanPham;
import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ADAPTER_SEARCH extends RecyclerView.Adapter<SEARCHVIEWHOLDER> implements Filterable {
    Context context;
    ArrayList<DIENTHOAI> data;
    ArrayList<DIENTHOAI> fillterData;

    public ADAPTER_SEARCH(Context context, ArrayList<DIENTHOAI> data) {
        this.context = context;
        this.data = data;
        this.fillterData=new ArrayList<>(data);
    }

    @NonNull
    @Override
    public SEARCHVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_sanpham, null);
        return new SEARCHVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SEARCHVIEWHOLDER holder, int position) {
        DIENTHOAI dienthoai = fillterData.get(position);

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
        return fillterData.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            //được ghi đè để thực hiện quá trình lọc dữ liệu dựa trên ràng buộc
            protected FilterResults performFiltering(CharSequence constraint) {

                String query = constraint.toString().toLowerCase();//chuyen doi thanh chu thuong, thanh chuoi ky tu
                ArrayList<DIENTHOAI> filteredList = new ArrayList<>();

                if (query.isEmpty()) {
                    filteredList.addAll(data);
                } else {
                    for (DIENTHOAI room : data) {
                        if (room.tensanpham.toLowerCase().contains(query) || room.dongia.toLowerCase().contains(query)) {
                            filteredList.add(room);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                fillterData.clear();
                fillterData.addAll((ArrayList<DIENTHOAI>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
class SEARCHVIEWHOLDER extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView tensanpham, giasanpham;

    public SEARCHVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imghinhminhhoa);
        tensanpham = itemView.findViewById(R.id.tvtendt);
        giasanpham = itemView.findViewById(R.id.tvdongia);
    }
}
