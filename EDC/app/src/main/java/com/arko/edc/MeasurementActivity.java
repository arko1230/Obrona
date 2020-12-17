package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MeasurementActivity extends AppCompatActivity {

    private Button btnSugar, btnTemperature, btnBlood_pressure, btnBlood_oxygenation, btnFettle, btnWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        initWidgets();
        setOnCilickListeners();
    }

    private void setOnCilickListeners(){
        btnSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeasurementActivity.this, SugarActivity.class );
                startActivity(intent);
            }
        });


        //TODO do all of measurement buttons and views
        //TODO add recyclerview for all


    }


    private void initWidgets(){

        btnSugar = (Button) findViewById(R.id.btnSugar);
        btnTemperature = (Button) findViewById(R.id.btnTemperature);
        btnBlood_pressure = (Button) findViewById(R.id.btnBlood_pressure);
        btnBlood_oxygenation = (Button) findViewById(R.id.btnBlood_oxygenation);
        btnFettle = (Button) findViewById(R.id.btnFettle);
        btnWeight = (Button) findViewById(R.id.btnWeight);

    }

}