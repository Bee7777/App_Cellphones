package com.example.doan_cellphoneapp.MAIN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.doan_cellphoneapp.FRAGMENT.Fragment_account;
import com.example.doan_cellphoneapp.FRAGMENT.Fragment_home;
import com.example.doan_cellphoneapp.FRAGMENT.Fragment_search;
import com.example.doan_cellphoneapp.FRAGMENT.Fragment_shopping;
import com.example.doan_cellphoneapp.R;
import com.example.doan_cellphoneapp.SERVER.SERVER;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomAppBar = findViewById(R.id.BottomAppBar);
        if (SERVER.manggiohang == null){
            SERVER.manggiohang = new ArrayList<>();
        }

        replaceFragment(new Fragment_home() );
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menuHome:
                    replaceFragment(new Fragment_home());
                    break;
                case R.id.menuSearch:
                    replaceFragment(new Fragment_search());
                    break;
                case R.id.menuShopping:
                    replaceFragment(new Fragment_shopping());
                    break;
                case R.id.menuAccount:
                    replaceFragment(new Fragment_account());
                    break;
            }
            return true;
        });
    }
    private void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmenthome, fragment);
        fragmentTransaction.commit();
    }

}