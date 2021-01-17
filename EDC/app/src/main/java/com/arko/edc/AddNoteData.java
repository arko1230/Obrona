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

public class AddNoteData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private TextView txt_datechose_note;

    private EditText etxt_title_note,e_txtabout_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_data);


        txt_datechose_note = findViewById(R.id.txt_datechose_note);
        etxt_title_note = findViewById(R.id.etxt_title_note);
        e_txtabout_note = findViewById(R.id.e_txtabout_note);



        findViewById(R.id.btn_datepicker_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        findViewById(R.id.send_note).setOnClickListener(new View.OnClickListener() {
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
        txt_datechose_note.setText(date);
    }

    public void sendValue(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){

            String uid = user.getUid();

            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Notes");



            String noteTitle = etxt_title_note.getText().toString();
            String aboutNote =e_txtabout_note.getText().toString();
            String date = txt_datechose_note.getText().toString();

            Map newRec = new HashMap();
            newRec.put("noteTitle",noteTitle);
            newRec.put("aboutNote",aboutNote);
            newRec.put("date",date);
            current_user_db.push().setValue(newRec);

            Intent intent = new Intent(AddNoteData.this, NotesActivity.class);
            startActivity(intent);
            finish();

        }
        else finish();


    }


}