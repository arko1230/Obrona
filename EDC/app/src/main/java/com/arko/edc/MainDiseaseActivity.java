package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainDiseaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_disease);




        Button button_add_main_disease = (Button) findViewById(R.id.button_add_main_disease);
        button_add_main_disease.setOnClickListener(v -> {
            Intent intent = new Intent(MainDiseaseActivity.this, AddMainDisease.class);
            startActivity(intent);
            finish();
        });

    }



}