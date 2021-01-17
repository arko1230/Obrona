package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddFamilyDisease extends AppCompatActivity {


    private EditText e_txtDiseaseName, e_txtWho_disease, e_txtProgressDisease, e_txtDrugsDisease, e_txtDiseaseInfoExtra ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family_disease);

        e_txtDiseaseName = findViewById(R.id.e_txtDiseaseName);
        e_txtWho_disease = findViewById(R.id.e_txtWho_disease);
        e_txtProgressDisease = findViewById(R.id.e_txtProgressDisease);
        e_txtDrugsDisease = findViewById(R.id.e_txtDrugsDisease);
        e_txtDiseaseInfoExtra = findViewById(R.id.e_txtDiseaseInfoExtra);

        findViewById(R.id.send_FamilyDisease).setOnClickListener(new View.OnClickListener() {
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

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("FamilyDisease");


            String DiseaseName = e_txtDiseaseName.getText().toString();
            String Who_disease =e_txtWho_disease.getText().toString();
            String ProgressDisease =e_txtProgressDisease.getText().toString();
            String DrugsDisease = e_txtDrugsDisease.getText().toString();
            String DiseaseInfoExtra =e_txtDiseaseInfoExtra.getText().toString();


            Map newRec = new HashMap();
            newRec.put("DiseaseName",DiseaseName);
            newRec.put("Who_disease",Who_disease);
            newRec.put("ProgressDisease",ProgressDisease);
            newRec.put("DrugsDisease",DrugsDisease);
            newRec.put("DiseaseInfoExtra",DiseaseInfoExtra);

            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddFamilyDisease.this, FamilyDiseasesActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }


}