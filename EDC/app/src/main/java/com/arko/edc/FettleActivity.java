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

public class FettleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireFelt> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireFelt,FirebaseViewFeltHolder> mAdapter;
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
        setContentView(R.layout.activity_fettle);

        Button addFettleButton = (Button) findViewById(R.id.button_add_fettle);
        addFettleButton.setOnClickListener(v -> {
            Intent intent = new Intent(FettleActivity.this, AddFettleData.class);
            startActivity(intent);
            finish();
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_felt);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Fettle");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireFelt>().setQuery(mDatabaseReference, DataSetFireFelt.class).build();


        mAdapter = new FirebaseRecyclerAdapter<DataSetFireFelt, FirebaseViewFeltHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewFeltHolder holder, int position, @NonNull DataSetFireFelt model) {
                holder.fettleResult.setText(model.getFettleResult());
                holder.fettleScale.setText(model.getFettleScale());
                holder.fettleAbout.setText(model.getFettleAbout());
                holder.date.setText(model.getDate());

                holder.btn_del_felt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_felt.getContext());
                        builder.setTitle("Usuwanie wpisu z histori");
                        builder.setMessage("Usunąc wpis?");

                        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Fettle").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewFeltHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewFeltHolder(LayoutInflater.from(FettleActivity.this).inflate(R.layout.fettle_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }


}