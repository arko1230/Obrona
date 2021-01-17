package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DiseasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_activty);



        Button btnFamily_diseases = (Button) findViewById(R.id.btnFamily_diseases);
        btnFamily_diseases.setOnClickListener(v -> {
            Intent intent = new Intent(DiseasesActivity.this, FamilyDiseasesActivity.class);
            startActivity(intent);
            finish();
        });


        Button btnMain_diaseases = (Button) findViewById(R.id.btnDiseases);
        btnMain_diaseases.setOnClickListener(v -> {
            Intent intent = new Intent(DiseasesActivity.this, MainDiseaseActivity.class);
            startActivity(intent);
            finish();
        });
    }
}