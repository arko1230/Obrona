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

public class AddPainData extends AppCompatActivity {

    private EditText e_txtPainPlace,e_txtPain_level,e_txt_Pain_metod,e_txt_When_Pain,e_txt_When_Pain_start, e_txt_When_Pain_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pain_data);

        e_txtPainPlace = findViewById(R.id.e_txtPainPlace);
        e_txtPain_level = findViewById(R.id.e_txtPain_level);
        e_txt_Pain_metod = findViewById(R.id.e_txt_Pain_metod);
        e_txt_When_Pain = findViewById(R.id.e_txt_When_Pain);
        e_txt_When_Pain_start = findViewById(R.id.e_txt_When_Pain_start);
        e_txt_When_Pain_end = findViewById(R.id.e_txt_When_Pain_end);


        findViewById(R.id.send_Pain).setOnClickListener(new View.OnClickListener() {
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

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Pain");



            String PainPlace = e_txtPainPlace.getText().toString();
            String Pain_leve =e_txtPain_level.getText().toString();
            String Pain_metod = e_txt_Pain_metod.getText().toString();
            String When_Pain = e_txt_When_Pain.getText().toString();
            String When_Pain_start =e_txt_When_Pain_start.getText().toString();
            String When_Pain_end = e_txt_When_Pain_end.getText().toString();

            Map newRec = new HashMap();
            newRec.put("PainPlace",PainPlace);
            newRec.put("Pain_leve",Pain_leve);
            newRec.put("Pain_metod",Pain_metod);
            newRec.put("When_Pain",When_Pain);
            newRec.put("When_Pain_start",When_Pain_start);
            newRec.put("When_Pain_end",When_Pain_end);

            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddPainData.this, PainActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }



}
