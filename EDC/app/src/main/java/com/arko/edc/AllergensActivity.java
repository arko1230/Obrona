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

public class AllergensActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<DataSetFireAllergen>mOptions;
    private FirebaseRecyclerAdapter<DataSetFireAllergen,FirebaseViewAllergenHolder> mAdapter;
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
        setContentView(R.layout.activity_allergens);


        Button addAllergenButton = (Button) findViewById(R.id.button_add_allergen);
        addAllergenButton.setOnClickListener(v -> {
            Intent intent = new Intent(AllergensActivity.this, AddAllergensData.class);
            startActivity(intent);
            finish();
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_allergens);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Allergens");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireAllergen>().setQuery(mDatabaseReference, DataSetFireAllergen.class).build();

        mAdapter = new FirebaseRecyclerAdapter<DataSetFireAllergen, FirebaseViewAllergenHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewAllergenHolder holder, int position, @NonNull DataSetFireAllergen model) {
                holder.allergenName.setText(model.getAllergenName());
                holder.allergenFactor.setText(model.getAllergenFactor());
                holder.allergenShows.setText(model.getAllergenShows());
                holder.allergenHide.setText(model.getAllergenHide());
                holder.date.setText(model.getDate());
                holder.btn_del_allergen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_allergen.getContext());
                        builder.setTitle("Usuwanie wpisu z histori");
                        builder.setMessage("Usunąc wpis?");

                        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Allergens").child(getRef(position).getKey()).removeValue();
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
            public FirebaseViewAllergenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewAllergenHolder(LayoutInflater.from(AllergensActivity.this).inflate(R.layout.allergens_single_row, parent, false));

            }
        };


        mRecyclerView.setAdapter(mAdapter);
    }
        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {
    }


}