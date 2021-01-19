package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain);


        Button addPresserButton = (Button) findViewById(R.id.button_add_pain);
        addPresserButton.setOnClickListener(v -> {
            Intent intent = new Intent(PainActivity.this, AddPainData.class);
            startActivity(intent);
            finish();
        });

    }
}