package com.example.doan_cellphoneapp.MAIN;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doan_cellphoneapp.ADAPTER.DIENTHOAI_ADAPTER;
import com.example.doan_cellphoneapp.MODEL.DIENTHOAI;
import com.example.doan_cellphoneapp.MODEL.NHASANXUAT;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class THEONHASANXUAT extends AppCompatActivity {
    RecyclerView rvtheonhasanxuat;
    ArrayList<DIENTHOAI> dienthoai_data = new ArrayList<>();
    DIENTHOAI_ADAPTER dienthoaiAdapter;
    NHASANXUAT nhasanxuat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theonhasanxuat);
        rvtheonhasanxuat = findViewById(R.id.rvtheonhasanxuat);

        dienthoaiAdapter =  new DIENTHOAI_ADAPTER(this, dienthoai_data);

        Intent intent = getIntent();
        nhasanxuat = (NHASANXUAT) intent.getSerializableExtra("hangdienthoai");

        rvtheonhasanxuat.setAdapter(dienthoaiAdapter);
        rvtheonhasanxuat.setLayoutManager(new GridLayoutManager(this,2));

        loadhangdienthoai();
    }

    private void loadhangdienthoai() {
        Response.Listener<String> thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //chuyển thành TV UTF-8
                    String utfResponse = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                    JSONArray jsonArray = new JSONArray(utfResponse);

                    dienthoai_data.clear();

                    for (int i = 0 ; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i); // Sửa JSONArray thành jsonObject

                        DIENTHOAI dienthoai = new DIENTHOAI(
                                jsonObject.getInt("idsanpham"),
                                jsonObject.getInt("soluong"),
                                jsonObject.getString("idhang"),
                                jsonObject.getString("hinhsanpham"),
                                jsonObject.getString("tensanpham"),
                                jsonObject.getString("dongia"),
                                jsonObject.getString("noiban"),
                                jsonObject.getString("mausanpham"),
                                jsonObject.getString("ngaycapnhat"),
                                jsonObject.getString("mota")
                        );
                        dienthoai_data.add(dienthoai);
                    }
                    dienthoaiAdapter.notifyDataSetChanged();
                } catch (JSONException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Toast.makeText(THEONHASANXUAT.this, "Lỗi khi xử lý", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener thabai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(THEONHASANXUAT.this, "Lỗi khi xử lý thất bại", Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.urltheonhasanxuat, thanhcong, thabai){ // Sửa "thatbai" thành "thabai"
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("idhang", nhasanxuat.idhang);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}