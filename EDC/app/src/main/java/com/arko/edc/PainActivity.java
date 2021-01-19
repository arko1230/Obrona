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

public class PainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFirePain> mOptions;
    private FirebaseRecyclerAdapter<DataSetFirePain,FirebaseViewPainHolder> mAdapter;
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
        setContentView(R.layout.activity_pain);


        Button addPresserButton = (Button) findViewById(R.id.button_add_pain);
        addPresserButton.setOnClickListener(v -> {
            Intent intent = new Intent(PainActivity.this, AddPainData.class);
            startActivity(intent);
            finish();
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_pain);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Pain");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFirePain>().setQuery(mDatabaseReference, DataSetFirePain.class).build();


        mAdapter = new FirebaseRecyclerAdapter<DataSetFirePain, FirebaseViewPainHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewPainHolder holder, int position, @NonNull DataSetFirePain model) {
                holder.PainPlace.setText(model.getPainPlace());
                holder.Pain_leve.setText(model.getPain_leve());
                holder.Pain_metod.setText(model.getPain_metod());
                holder.When_Pain.setText(model.getWhen_Pain());
                holder.When_Pain_start.setText(model.getWhen_Pain_start());
                holder.When_Pain_end.setText(model.getWhen_Pain_end());

                holder.btn_del_Pain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_Pain.getContext());
                        builder.setTitle("Usuwanie wpisu z histori");
                        builder.setMessage("Usunąc wpis?");

                        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Pain").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewPainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewPainHolder(LayoutInflater.from(PainActivity.this).inflate(R.layout.pain_single_row, parent, false));

            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}