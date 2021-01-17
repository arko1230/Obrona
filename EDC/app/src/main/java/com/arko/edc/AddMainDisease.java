package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddMainDisease extends AppCompatActivity {
    private EditText e_txtDiseaseN, e_Time_disease, e_txtTimeRecovery, e_txtSymptoms, e_txtProcedure ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main_disease);

        e_txtDiseaseN = findViewById(R.id.e_txtDiseaseN);
        e_Time_disease = findViewById(R.id.e_Time_disease);
        e_txtTimeRecovery = findViewById(R.id.e_txtTimeRecovery);
        e_txtSymptoms = findViewById(R.id.e_txtSymptoms);
        e_txtProcedure = findViewById(R.id.e_txtProcedure);

        findViewById(R.id.send_MainDisease).setOnClickListener(new View.OnClickListener() {
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

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("MainDisease");


            String MainDiseaseName = e_txtDiseaseN.getText().toString();
            String Time_disease =e_Time_disease.getText().toString();
            String TimeRecovery =e_txtTimeRecovery.getText().toString();
            String Symptoms = e_txtSymptoms.getText().toString();
            String Procedure =e_txtProcedure.getText().toString();


            Map newRec = new HashMap();
            newRec.put("MainDiseaseName",MainDiseaseName);
            newRec.put("Time_disease",Time_disease);
            newRec.put("TimeRecovery",TimeRecovery);
            newRec.put("Symptoms",Symptoms);
            newRec.put("Procedure",Procedure);

            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddMainDisease.this, MainDiseaseActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }


}