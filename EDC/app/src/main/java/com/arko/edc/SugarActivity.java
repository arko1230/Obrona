package com.arko.edc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.OnClickListener;

public class SugarActivity extends AppCompatActivity {

    RecyclerView rcvSugar;
       FloatingActionButton addSugarActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sugar);
        rcvSugar = (RecyclerView)findViewById(R.id.RecyclerViewSugar);
        rcvSugar.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton addSugarButton = (FloatingActionButton) findViewById(R.id.AddSugarActionButton);
        addSugarButton.setOnClickListener(v -> {
            Intent intent = new Intent(SugarActivity.this, AddSugarData.class);
            startActivity(intent);
            finish();
        });




    }


}