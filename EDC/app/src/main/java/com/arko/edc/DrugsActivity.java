package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DrugsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);



        Button addDrugButton = (Button) findViewById(R.id.button_add_drugs);
        addDrugButton.setOnClickListener(v -> {
            Intent intent = new Intent(DrugsActivity.this, AddDrugsData.class);
            startActivity(intent);
            finish();
        });
    }
}