package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Button addNoteButton = (Button) findViewById(R.id.button_add_notes);
        addNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(NotesActivity.this, AddNoteData.class);
            startActivity(intent);
            finish();
        });

    }


}