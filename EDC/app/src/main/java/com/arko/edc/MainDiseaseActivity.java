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

public class MainDiseaseActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireDiseases> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireDiseases,FirebaseViewMainDisease> mAdapter;
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
        setContentView(R.layout.activity_main_disease);




        Button button_add_main_disease = (Button) findViewById(R.id.button_add_main_disease);
        button_add_main_disease.setOnClickListener(v -> {
            Intent intent = new Intent(MainDiseaseActivity.this, AddMainDisease.class);
            startActivity(intent);
            finish();
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_main_disease);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("MainDisease");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireDiseases>().setQuery(mDatabaseReference, DataSetFireDiseases.class).build();


        mAdapter = new FirebaseRecyclerAdapter<DataSetFireDiseases, FirebaseViewMainDisease>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewMainDisease holder, int position, @NonNull DataSetFireDiseases model) {
                holder.MainDiseaseName.setText(model.getMainDiseaseName());
                holder.Time_disease.setText(model.getTime_disease());
                holder.TimeRecovery.setText(model.getTimeRecovery());
                holder.Symptoms.setText(model.getSymptoms());
                holder.Procedure.setText(model.getProcedure());

                holder.btn_del_Disease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_Disease.getContext());
                        builder.setTitle("USUWANIE WPISU Z HISTORI");
                        builder.setMessage("Czy napewno chcesz usunąć ten zapis?");

                        builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("MainDisease").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewMainDisease onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewMainDisease(LayoutInflater.from(MainDiseaseActivity.this).inflate(R.layout.maindiseases_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    }


