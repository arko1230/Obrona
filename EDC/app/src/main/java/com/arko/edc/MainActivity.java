package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnUser, btnMeasurement,btnDiseases,btnPains,btnHistory,btnNotes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initWidgets();


    }

    private void initWidgets(){

        btnUser = (Button) findViewById(R.id.btnUser);



    }

}