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

public class WeightActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireWeight> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireWeight,FirebaseViewWeightHolder> mAdapter;
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
        setContentView(R.layout.activity_weight);


        Button addPresserButton = (Button) findViewById(R.id.button_add_weight);
        addPresserButton.setOnClickListener(v -> {
            Intent intent = new Intent(WeightActivity.this, AddWeightData.class);
            startActivity(intent);
            finish();
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_pressure);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Weight");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireWeight>().setQuery(mDatabaseReference, DataSetFireWeight.class).build();

        mAdapter = new FirebaseRecyclerAdapter<DataSetFireWeight, FirebaseViewWeightHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewWeightHolder holder, int position, @NonNull DataSetFireWeight model) {
                holder.weightResult.setText(model.getWeightResult());
                holder.weightAbout.setText(model.getWeightAbout());
                holder.date.setText(model.getDate());

                holder.btn_del_weight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_weight.getContext());
                        builder.setTitle("Usuwanie wpisu z histori");
                        builder.setMessage("Usunąc wpis?");

                        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Weight").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewWeightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewWeightHolder(LayoutInflater.from(WeightActivity.this).inflate(R.layout.weight_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }



}