package com.arko.edc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireNote> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireNote,FirebaseViewNoteData> mAdapter;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }



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




        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_notes);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Notes");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireNote>().setQuery(mDatabaseReference, DataSetFireNote.class).build();


        mAdapter = new FirebaseRecyclerAdapter<DataSetFireNote, FirebaseViewNoteData>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewNoteData holder, int position, @NonNull DataSetFireNote model) {
                holder.noteTitle.setText(model.getNoteTitle());
                holder.aboutNote.setText(model.getAboutNote());
                holder.date.setText(model.getDate());

                holder.btn_del_note.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_note.getContext());
                        builder.setTitle("Usuwanie wpisu z histori");
                        builder.setMessage("Usunąc wpis?");

                        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Notes").child(getRef(position).getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();

                    }
                });


            }

            @NonNull
            @Override
            public FirebaseViewNoteData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewNoteData(LayoutInflater.from(NotesActivity.this).inflate(R.layout.note_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }



}