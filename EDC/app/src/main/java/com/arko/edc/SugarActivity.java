package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SugarActivity extends AppCompatActivity {

    RecyclerView rcvSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar);


        rcvSugar = (RecyclerView)findViewById(R.id.RecyclerViewSugar);
        rcvSugar.setLayoutManager(new LinearLayoutManager(this));


        String arr[]={"c++","c","java","php","c++","c","java","php","c++","c","java","php","c++","c","java","c++","c","java","php","c++","c","java",};
        rcvSugar.setAdapter(new MySugarAdapter(arr));


    }
}