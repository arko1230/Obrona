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

import static com.arko.edc.R.id.btn_datepicker_fettl;
import static com.arko.edc.R.id.btn_datepicker_oxygen;

public class AddFettleData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private TextView txt_datechose;

    private EditText result_etxt_fettle, e_txtScale_fettle, e_txtabout_fettle ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fettle_data);


        txt_datechose = findViewById(R.id.txt_datechose_fettle);
        result_etxt_fettle = findViewById(R.id.result_etxt_fettle);
        e_txtScale_fettle = findViewById(R.id.e_txtScale_fettle);
        e_txtabout_fettle = findViewById(R.id.e_txtabout_fettle);


        findViewById(btn_datepicker_fettl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        findViewById(R.id.send_fettle).setOnClickListener(new View.OnClickListener() {
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

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Fettle");


            String fettleResult = result_etxt_fettle.getText().toString();
            String fettleScale =e_txtScale_fettle.getText().toString();
            String fettleAbout =e_txtabout_fettle.getText().toString();
            String date = txt_datechose.getText().toString();

            Map newRec = new HashMap();
            newRec.put("fettleResult",fettleResult);
            newRec.put("fettleScale",fettleScale);
            newRec.put("fettleAbout",fettleAbout);
            newRec.put("date",date);
            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddFettleData.this, FettleActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }


}