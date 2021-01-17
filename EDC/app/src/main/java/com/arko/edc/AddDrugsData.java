package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.arko.edc.R.id.btn_datepicker_fettl;

public class AddDrugsData extends AppCompatActivity {

    private EditText e_txtTitleDrug, etxt_Drug_dosage, e_txt_dayPart_takeDrug,e_txt_DrugAction,e_txtDrugContraindications,e_txtDrugPeriod_of_use ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drugs_data);

        e_txtTitleDrug = findViewById(R.id.e_txtTitleDrug);
        etxt_Drug_dosage = findViewById(R.id.etxt_Drug_dosage);
        e_txt_dayPart_takeDrug = findViewById(R.id.e_txt_dayPart_takeDrug);
        e_txt_DrugAction = findViewById(R.id.e_txt_DrugAction);
        e_txtDrugContraindications = findViewById(R.id.e_txtDrugContraindications);
        e_txtDrugPeriod_of_use = findViewById(R.id.e_txtDrugPeriod_of_use);

        findViewById(R.id.send_Drug).setOnClickListener(new View.OnClickListener() {
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

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Drugs");


            String titleDrug = e_txtTitleDrug.getText().toString();
            String drug_dosage =etxt_Drug_dosage.getText().toString();
            String dayPart_takeDrug =e_txt_dayPart_takeDrug.getText().toString();
            String drugAction = e_txt_DrugAction.getText().toString();
            String drugContraindications =e_txtDrugContraindications.getText().toString();
            String drugPeriod_of_use = e_txtDrugPeriod_of_use.getText().toString();

            Map newRec = new HashMap();
            newRec.put("titleDrug",titleDrug);
            newRec.put("drug_dosage",drug_dosage);
            newRec.put("dayPart_takeDrug",dayPart_takeDrug);
            newRec.put("drugAction",drugAction);
            newRec.put("drugContraindications",drugContraindications);
            newRec.put("drugPeriod_of_use",drugPeriod_of_use);
            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddDrugsData.this, DrugsActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }

}