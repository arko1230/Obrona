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

public class OxygenationActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireSP02> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireSP02,FirebaseViewSP02Holder> mAdapter;
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
        setContentView(R.layout.activity_oxygenation);

        Button addSugarButton = (Button) findViewById(R.id.button_add_oxygenation);
        addSugarButton.setOnClickListener(v -> {
            Intent intent = new Intent(OxygenationActivity.this, AddOxygenation.class);
            startActivity(intent);
            finish();
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_SP02);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Oxygen");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireSP02>().setQuery(mDatabaseReference, DataSetFireSP02.class).build();


        mAdapter = new FirebaseRecyclerAdapter<DataSetFireSP02, FirebaseViewSP02Holder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewSP02Holder holder, int position, @NonNull DataSetFireSP02 model) {
                holder.oxygenResult.setText(model.getOxygenResult());
                holder.aboutOxygen.setText(model.getAboutOxygen());
                holder.date.setText(model.getDate());

                holder.btn_del_SP02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_SP02.getContext());
                        builder.setTitle("USUWANIE WPISU Z HISTORI");
                        builder.setMessage("Czy napewno chcesz usunąć ten zapis?");

                        builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Oxygen").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewSP02Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewSP02Holder(LayoutInflater.from(OxygenationActivity.this).inflate(R.layout.oxygenation_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}