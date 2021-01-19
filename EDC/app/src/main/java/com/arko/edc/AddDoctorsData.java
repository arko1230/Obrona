package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddDoctorsData extends AppCompatActivity {

    private EditText e_txtDoctorName,e_txt_DoctorType,etxt_DoctorAbout,etxt_DoctorContakt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctors_data);

        e_txtDoctorName = findViewById(R.id.e_txtDoctorName);
        e_txt_DoctorType = findViewById(R.id.e_txt_DoctorType);
        etxt_DoctorAbout = findViewById(R.id.etxt_DoctorAbout);
        etxt_DoctorContakt = findViewById(R.id.etxt_DoctorContakt);



        findViewById(R.id.send_Doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendValue();
            }
        });

    }

    public void sendValue(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){

            String uid = user.getUid();

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Doctors");



            String doctorName = e_txtDoctorName.getText().toString();
            String doctorType =e_txt_DoctorType.getText().toString();
            String doctorAbout = etxt_DoctorAbout.getText().toString();
            String doctorContakt = etxt_DoctorContakt.getText().toString();


            Map newRec = new HashMap();
            newRec.put("doctorName",doctorName);
            newRec.put("doctorType",doctorType);
            newRec.put("doctorAbout",doctorAbout);
            newRec.put("doctorContakt",doctorContakt);
            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddDoctorsData.this, DoctorActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }

}