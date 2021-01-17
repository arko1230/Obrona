package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.arko.edc.R.id.btn_datepicker_pressure;
import static com.arko.edc.R.id.btn_datepicker_temp;

public class AddPressureData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView txt_datechose;

    private EditText etxtResultPress, etxtResultPuls, etxtPressAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pressure_data);



        txt_datechose = findViewById(R.id.txt_datechose_press);
        etxtResultPress = findViewById(R.id.result_etxt_preassure);
        etxtResultPuls = findViewById(R.id.e_txtabout_puls);
        etxtPressAbout = findViewById(R.id.e_txtabout_pressure);

        findViewById(btn_datepicker_pressure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        findViewById(R.id.send_press).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendValue();

            }
        });


    }

    public void showDatePickerDialog(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = (dayOfMonth) + "." + (month+1) + "." + year;
        txt_datechose.setText(date);
    }

    public void sendValue(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){

            String uid = user.getUid();

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Presser");



            String pressResult = etxtResultPress.getText().toString();
            String pulsResult = etxtResultPuls.getText().toString();
            String aboutPressure =etxtPressAbout.getText().toString();
            String date = txt_datechose.getText().toString();

            Map newRec = new HashMap();
            newRec.put("pressResult",pressResult);
            newRec.put("pulsResult",pulsResult);
            newRec.put("aboutPressure",aboutPressure);
            newRec.put("date",date);
            current_user_db.push().setValue(newRec);
            Intent intent = new Intent(AddPressureData.this, PressureActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }


}

