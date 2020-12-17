package com.arko.edc;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.arko.edc.ui.login.LoginViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnUser, btnMeasurement,btnDiseases,btnPains,btnHistory,btnNotes, btnDrugs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initWidgets();
        setOnCilickListeners();


    }

    private void setOnCilickListeners(){
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserInterfaceActivity.class);
                startActivity(intent);
            }
        });

        btnMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeasurementActivity.class );
                startActivity(intent);
            }
        });

        btnDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiseasesActivity.class );
                startActivity(intent);
            }
        });

        btnPains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PainActivity.class );
                startActivity(intent);
            }
        });


        btnDrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrugsActivity.class );
                startActivity(intent);
            }
        });


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class );
                startActivity(intent);
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotesActivity.class );
                startActivity(intent);
            }
        });



    }


    private void initWidgets(){

        btnUser = (Button) findViewById(R.id.btnUser);
        btnMeasurement = (Button) findViewById(R.id.btnMeasurement);
        btnDiseases = (Button) findViewById(R.id.btnDiseases);
        btnPains = (Button) findViewById(R.id.btnPains);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnNotes = (Button) findViewById(R.id.btnNotes);
        btnDrugs = (Button) findViewById(R.id.btnDrugs);

    }

}