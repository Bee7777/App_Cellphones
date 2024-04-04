package com.example.doan_cellphoneapp.FRAGMENT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doan_cellphoneapp.ADAPTER.ADAPTER_SEARCH;
import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_search extends Fragment {

    RecyclerView rvDienthoaibanchay;
    ADAPTER_SEARCH adapterSearch;
    ArrayList<DIENTHOAI> mangdienthoai = new ArrayList<>();
    MaterialToolbar materialToolbar;
    androidx.appcompat.widget.SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        searchView = view.findViewById(R.id.search_mainS);
        materialToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        adapterSearch = new ADAPTER_SEARCH(getContext(), mangdienthoai);
        rvDienthoaibanchay.setAdapter(adapterSearch);
        rvDienthoaibanchay.setLayoutManager(new GridLayoutManager(getContext(), 2));
        loadDienthoai();
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSearch.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void loadDienthoai() {

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int j = 0; j < response.length(); j++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(j);
                        DIENTHOAI dienthoai = new DIENTHOAI(jsonObject.getInt("idsanpham"),
                                jsonObject.getInt("soluong"),
                                jsonObject.getString("idhang"),
                                jsonObject.getString("hinhsanpham"),
                                jsonObject.getString("tensanpham"),
                                jsonObject.getString("dongia"),
                                jsonObject.getString("noiban"),
                                jsonObject.getString("mausanpham"),
                                jsonObject.getString("ngaycapnhat"),
                                jsonObject.getString("mota"));
                        mangdienthoai.add(dienthoai);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("loi", e.getMessage());
                        Toast.makeText(getContext(), "thanhcong", Toast.LENGTH_SHORT).show();
                    }
                }
                adapterSearch.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "thatbai", Toast.LENGTH_SHORT).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urldienthoai, thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

    }

    private void anhxa(View view) {
        materialToolbar = view.findViewById(R.id.toolbarSearch);
        rvDienthoaibanchay = view.findViewById(R.id.rvSearch);
    }
}

