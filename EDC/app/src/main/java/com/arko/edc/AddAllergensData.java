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

import static com.arko.edc.R.id.btn_datepicker_oxygen;

public class AddAllergensData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private TextView txt_aboutdate_allergen;

    private EditText e_txtAllergenName,e_txt_AllergenFactor,e_txt_AllergenShows,e_txt_AllergenHide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_allergens_data);

        txt_aboutdate_allergen = findViewById(R.id.txt_aboutdate_allergen);
        e_txtAllergenName = findViewById(R.id.e_txtAllergenName);
        e_txt_AllergenFactor = findViewById(R.id.e_txt_AllergenFactor);
        e_txt_AllergenShows = findViewById(R.id.e_txt_AllergenShows);
        e_txt_AllergenHide = findViewById(R.id.e_txt_AllergenHide);

        findViewById(R.id.btn_datepicker_allergen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        findViewById(R.id.send_Allergen).setOnClickListener(new View.OnClickListener() {
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
        txt_aboutdate_allergen.setText(date);
    }

    public void sendValue(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){

            String uid = user.getUid();

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Allergens");
            txt_aboutdate_allergen = findViewById(R.id.txt_aboutdate_allergen);
            e_txtAllergenName = findViewById(R.id.e_txtAllergenName);
            e_txt_AllergenFactor = findViewById(R.id.e_txt_AllergenFactor);
            e_txt_AllergenShows = findViewById(R.id.e_txt_AllergenShows);
            e_txt_AllergenHide = findViewById(R.id.e_txt_AllergenHide);


            String allergenName = e_txtAllergenName.getText().toString();
            String allergenFactor =e_txt_AllergenFactor.getText().toString();

            String allergenShows = e_txt_AllergenShows.getText().toString();
            String allergenHide =e_txt_AllergenHide.getText().toString();
            String date = txt_aboutdate_allergen.getText().toString();

            Map newRec = new HashMap();
            newRec.put("allergenName",allergenName);
            newRec.put("allergenFactor",allergenFactor);
            newRec.put("allergenShows",allergenShows);
            newRec.put("allergenHide",allergenHide);
            newRec.put("date",date);
            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddAllergensData.this, AllergensActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }




}