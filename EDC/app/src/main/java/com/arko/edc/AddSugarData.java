package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class AddSugarData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView txt_datechose;

    private EditText etxtResult, etxtSugAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_sugar_data);

        txt_datechose = findViewById(R.id.txt_datechose);
        etxtSugAbout = findViewById(R.id.e_txtabout);
        etxtResult = findViewById(R.id.result_etxt_s);


        findViewById(R.id.btn_datepicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

      findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
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

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Sugar");



              String sugarResult = etxtResult.getText().toString();
              String aboutSugar =etxtSugAbout.getText().toString();
              String date = txt_datechose.getText().toString();

              Map newRec = new HashMap();
              newRec.put("sugarResult",sugarResult);
              newRec.put("aboutSugar",aboutSugar);
              newRec.put("date",date);
              current_user_db.push().setValue(newRec);
              finish();

            }
        else finish();


    }




}