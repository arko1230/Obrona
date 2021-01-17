package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);


        Button addPresserButton = (Button) findViewById(R.id.button_add_weight);
        addPresserButton.setOnClickListener(v -> {
            Intent intent = new Intent(WeightActivity.this, AddWeightData.class);
            startActivity(intent);
            finish();
        });
    }
}