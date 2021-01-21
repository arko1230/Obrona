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

public class DoctorActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireDoctor> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireDoctor,FirebaseViewDoctorHolder> mAdapter;
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
        setContentView(R.layout.activity_doctor);

        Button addDoctorButton = (Button) findViewById(R.id.button_add_doctor);
        addDoctorButton.setOnClickListener(v -> {
            Intent intent = new Intent(DoctorActivity.this, AddDoctorsData.class);
            startActivity(intent);
            finish();
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_doctor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Doctors");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireDoctor>().setQuery(mDatabaseReference, DataSetFireDoctor.class).build();


        mAdapter = new FirebaseRecyclerAdapter<DataSetFireDoctor, FirebaseViewDoctorHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewDoctorHolder holder, int position, @NonNull DataSetFireDoctor model) {
                holder.doctorName.setText(model.getDoctorName());
                holder.doctorType.setText(model.getDoctorType());
                holder.doctorAbout.setText(model.getDoctorAbout());
                holder.doctorContakt.setText(model.getDoctorContakt());

                holder.btn_del_doctor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_doctor.getContext());
                        builder.setTitle("USUWANIE WPISU Z HISTORI");
                        builder.setMessage("Czy napewno chcesz usunąć ten zapis?");

                        builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Doctors").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewDoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewDoctorHolder(LayoutInflater.from(DoctorActivity.this).inflate(R.layout.doctor_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}