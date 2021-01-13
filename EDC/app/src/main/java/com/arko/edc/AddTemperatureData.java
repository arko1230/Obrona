package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AddTemperatureData extends AppCompatActivity {

    private TextView txt_datechose;

    private EditText  etxtResult, etxtSugAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_temperature_data);


        txt_datechose = findViewById(R.id.date_t);
        etxtSugAbout = findViewById(R.id.e_txtabout);
        etxtResult = findViewById(R.id.result_etxt_s);
    }
}