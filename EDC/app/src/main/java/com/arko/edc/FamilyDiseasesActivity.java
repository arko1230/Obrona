package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FamilyDiseasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_diseases);



        Button button_add_family_diaseases = (Button) findViewById(R.id.button_add_family_diaseases);
        button_add_family_diaseases.setOnClickListener(v -> {
            Intent intent = new Intent(FamilyDiseasesActivity.this, AddFamilyDisease.class);
            startActivity(intent);
            finish();
        });

    }


}