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

import java.util.ArrayList;

public class TemperatureActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<DataSetFireTemp> mArrayList;
    private FirebaseRecyclerOptions<DataSetFireTemp> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireTemp,FirebaseViewTempHolder> mAdapter;
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
        setContentView(R.layout.activity_temperature);

        Button addSugarButton = (Button) findViewById(R.id.button_add_temperature);
        addSugarButton.setOnClickListener(v -> {
            Intent intent = new Intent(TemperatureActivity.this, AddTemperatureData.class);
            startActivity(intent);
            finish();
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_temperature);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser= FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Temperature");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireTemp>().setQuery(mDatabaseReference,DataSetFireTemp.class).build();

        mAdapter = new FirebaseRecyclerAdapter<DataSetFireTemp, FirebaseViewTempHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewTempHolder holder, int position, @NonNull DataSetFireTemp model) {
                holder.tempResult.setText(model.getTempResult());
                holder.date.setText(model.getDate());
                holder.aboutTemperature.setText(model.getAboutTemperature());

                holder.btn_del_temperature.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_temperature.getContext());
                        builder.setTitle("USUWANIE WPISU Z HISTORI");
                        builder.setMessage("Czy napewno chcesz usunąć ten zapis?");

                        builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Temperature").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewTempHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewTempHolder(LayoutInflater.from(TemperatureActivity.this).inflate(R.layout.temperature_single_row,parent,false));

            }
        };





        mRecyclerView.setAdapter(mAdapter);



        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {
    }
}