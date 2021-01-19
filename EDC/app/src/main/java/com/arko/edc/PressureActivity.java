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

public class PressureActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFirePress> mOptions;
    private FirebaseRecyclerAdapter<DataSetFirePress,FirebaseViewPressureHolder> mAdapter;
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
        setContentView(R.layout.activity_pressure);


        Button addPresserButton = (Button) findViewById(R.id.button_add_pressure);
        addPresserButton.setOnClickListener(v -> {
            Intent intent = new Intent(PressureActivity.this, AddPressureData.class);
            startActivity(intent);
            finish();
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_pressure);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Presser");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFirePress>().setQuery(mDatabaseReference, DataSetFirePress.class).build();

        mAdapter = new FirebaseRecyclerAdapter<DataSetFirePress, FirebaseViewPressureHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewPressureHolder holder, int position, @NonNull DataSetFirePress model) {
                holder.pressResult.setText(model.getPressResult());
                holder.pulsResult.setText(model.getPressResult());
                holder.aboutPressure.setText(model.getAboutPressure());
                holder.date.setText(model.getDate());

                holder.btn_del_press.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_press.getContext());
                        builder.setTitle("Usuwanie wpisu z histori");
                        builder.setMessage("Usunąc wpis?");

                        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Presser").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewPressureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewPressureHolder(LayoutInflater.from(PressureActivity.this).inflate(R.layout.pressure_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}