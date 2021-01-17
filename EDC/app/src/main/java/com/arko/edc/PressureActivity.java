package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PressureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);


        Button addPresserButton = (Button) findViewById(R.id.button_add_pressure);
        addPresserButton.setOnClickListener(v -> {
            Intent intent = new Intent(PressureActivity.this, AddPressureData.class);
            startActivity(intent);
            finish();
        });
    }
}