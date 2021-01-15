package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import static com.arko.edc.R.id.btn_datepicker_temp;
import static com.arko.edc.R.id.txt_datechose_temp;

public class AddTemperatureData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView txt_datechose;

    private EditText  etxtResultTemp, etxtTempAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_temperature_data);


        txt_datechose = findViewById(R.id.txt_datechose_temp);
        etxtTempAbout = findViewById(R.id.e_txtabout_temp);
        etxtResultTemp = findViewById(R.id.result_etxt_temp);

        findViewById(btn_datepicker_temp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        findViewById(R.id.send_temp).setOnClickListener(new View.OnClickListener() {
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

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Temperature");



            String sugarResult = etxtResultTemp.getText().toString();
            String aboutSugar =etxtTempAbout.getText().toString();
            String date = txt_datechose.getText().toString();

            Map newRec = new HashMap();
            newRec.put("tempResult",sugarResult);
            newRec.put("aboutTemperature",aboutSugar);
            newRec.put("date",date);
            current_user_db.push().setValue(newRec);
            finish();

        }
        else finish();


    }



}