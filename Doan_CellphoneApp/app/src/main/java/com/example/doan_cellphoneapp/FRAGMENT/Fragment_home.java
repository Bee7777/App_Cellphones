package com.example.doan_cellphoneapp.FRAGMENT;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doan_cellphoneapp.ADAPTER.DIENTHOAI_ADAPTER;
import com.example.doan_cellphoneapp.ADAPTER.NHASANXUAT_ADAPTER;
import com.example.doan_cellphoneapp.ADAPTER.QUANGCAO_ADAPTER;
import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.MODEL.NHASANXUAT;
import com.example.doan_cellphoneapp.MODEL.QUANGCAO;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_home extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView rvchude, rvDienthoaibanchay;
    ArrayList<NHASANXUAT> chude_data = new ArrayList<>();
    NHASANXUAT_ADAPTER nhasanxuat_adapter;

    DIENTHOAI_ADAPTER dienthoaiAdapter;
    ArrayList<DIENTHOAI> mangdienthoai = new ArrayList<>();

    QUANGCAO_ADAPTER quangcaoAdapter;
    ArrayList<QUANGCAO> layquangcao = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        nhasanxuat_adapter = new NHASANXUAT_ADAPTER(getContext(), chude_data);
        rvchude.setAdapter(nhasanxuat_adapter);
        rvchude.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        dienthoaiAdapter = new DIENTHOAI_ADAPTER(getContext(), mangdienthoai);
        rvDienthoaibanchay.setAdapter(dienthoaiAdapter);
        rvDienthoaibanchay.setLayoutManager(new GridLayoutManager(getContext(), 2));

        quangcaoAdapter = new QUANGCAO_ADAPTER(getContext(), layquangcao);

        loadchude();
        loadDienthoai();
        loadquangcao();
    }

    private void loadquangcao() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject quangcaoobject = response.getJSONObject(i);
                        QUANGCAO quangcao = new QUANGCAO(quangcaoobject.getString("idquangcao"),
                                quangcaoobject.getString("idsanpham"),
                                quangcaoobject.getString("tenquangcao"),
                                quangcaoobject.getString("hinhquangcao"));
                        layquangcao.add(quangcao);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("loiquuangcao", e.getMessage());
                        Toast.makeText(getContext(), "Loi phuong thuc thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
                // Load quangcao
                quangcaoAdapter.notifyDataSetChanged();
                loadQuangcaoToViewFlipper();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi phuong thuc that bai", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlquangcao, thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void loadQuangcaoToViewFlipper() {
        for (QUANGCAO quangcao : layquangcao) {
            ImageView imageView = new ImageView(getActivity());
            Picasso.get().load(SERVER.pathquangcao + quangcao.hinhquangcao).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
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
                dienthoaiAdapter.notifyDataSetChanged();
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

    private void loadchude() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        NHASANXUAT nhasanxuat = new NHASANXUAT(jsonObject.getString("idhang"),
                                jsonObject.getString("tenhang"),
                                jsonObject.getString("hinhhang")
                        );
                        chude_data.add(nhasanxuat);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi thanh cong", Toast.LENGTH_LONG).show();
                    }
                }
                nhasanxuat_adapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi that bai" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlnhasanxuat, thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void anhxa(View view) {
        viewFlipper = view.findViewById(R.id.viewflipper);
        rvchude = view.findViewById(R.id.rvChude);
        rvDienthoaibanchay = view.findViewById(R.id.rvDienthoaibanchay);
    }
}
