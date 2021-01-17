package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FettleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fettle);

        Button addSugarButton = (Button) findViewById(R.id.button_add_fettle);
        addSugarButton.setOnClickListener(v -> {
            Intent intent = new Intent(FettleActivity.this, AddFettleData.class);
            startActivity(intent);
            finish();
        });
    }
}