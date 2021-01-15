package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
    private ArrayList<DataSetFireSug> mArrayList;
    private FirebaseRecyclerOptions<DataSetFireSug> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireSug,FirebaseViewSugarHolder> mAdapter;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;


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


//        FirebaseRecyclerOptions<DataSetFireSug> options =
////                new FirebaseRecyclerOptions.Builder<DataSetFireSug>()
////                .setQuery(query,DataSetFireSug.class)
////                .build();
////



    }
}