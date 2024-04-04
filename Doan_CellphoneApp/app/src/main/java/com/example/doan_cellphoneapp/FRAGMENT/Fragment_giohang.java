package com.example.doan_cellphoneapp.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_cellphoneapp.ADAPTER.GIOHANG_ADAPTER;
import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;

public class Fragment_giohang extends Fragment {

    public static ArrayList<DIENTHOAI> ListGiohang = new ArrayList<>();
    private RecyclerView recyclerView;
    private GIOHANG_ADAPTER giohangAdapter;
    private TextView tvGiohang;


@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

    View view = inflater.inflate(R.layout.fragment_shopping, container, false);
    tvGiohang = view.findViewById(R.id.tv2);
    recyclerView = view.findViewById(R.id.RVframegiohang);

    setupRecycleview(ListGiohang);
    return view;

}

    private void setupRecycleview(List<DIENTHOAI>list){

        giohangAdapter = new GIOHANG_ADAPTER(list, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(giohangAdapter);

    }

}
