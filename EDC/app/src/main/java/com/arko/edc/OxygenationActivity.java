package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class OxygenationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygenation);

        Button addSugarButton = (Button) findViewById(R.id.button_add_oxygenation);
        addSugarButton.setOnClickListener(v -> {
            Intent intent = new Intent(OxygenationActivity.this, AddOxygenation.class);
            startActivity(intent);
            finish();
        });


    }
}