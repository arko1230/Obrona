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

public class FamilyDiseasesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireFamDiseases> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireFamDiseases,FirebaseViewFamDiseasesHolder> mAdapter;
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
        setContentView(R.layout.activity_family_diseases);



        Button button_add_family_diaseases = (Button) findViewById(R.id.button_add_family_diaseases);
        button_add_family_diaseases.setOnClickListener(v -> {
            Intent intent = new Intent(FamilyDiseasesActivity.this, AddFamilyDisease.class);
            startActivity(intent);
            finish();
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_familydiaseases);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("FamilyDisease");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireFamDiseases>().setQuery(mDatabaseReference, DataSetFireFamDiseases.class).build();


        mAdapter = new FirebaseRecyclerAdapter<DataSetFireFamDiseases, FirebaseViewFamDiseasesHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewFamDiseasesHolder holder, int position, @NonNull DataSetFireFamDiseases model) {
                holder.DiseaseName.setText(model.getDiseaseName());
                holder.Who_disease.setText(model.getWho_disease());
                holder.ProgressDisease.setText(model.getProgressDisease());
                holder.DrugsDisease.setText(model.getDrugsDisease());
                holder.DiseaseInfoExtra.setText(model.getDiseaseInfoExtra());

                holder.btn_del_FamilyDisease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_FamilyDisease.getContext());
                        builder.setTitle("Usuwanie wpisu z histori");
                        builder.setMessage("Usunąc wpis?");

                        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("FamilyDisease").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewFamDiseasesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewFamDiseasesHolder(LayoutInflater.from(FamilyDiseasesActivity.this).inflate(R.layout.familydiseases_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }


}


