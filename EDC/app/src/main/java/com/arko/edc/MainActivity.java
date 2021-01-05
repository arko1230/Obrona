package com.arko.edc;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btnUser, btnMeasurement,btnDiseases,btnPains,btnHistory,btnNotes, btnDrugs, btnLogout ;
    private ImageView imgLogUser;
    private TextView txtUserName, txtUserEmail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        setContentView(R.layout.activity_main);
        initWidgets();
        setOnCilickListeners();




        txtUserEmail.setText(account.getEmail());
       Glide.with(this).load(account.getPhotoUrl()).into(imgLogUser);


    }


    private void setOnCilickListeners(){


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });



        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserInterfaceActivity.class);
                startActivity(intent);
            }
        });



        btnMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeasurementActivity.class );
                startActivity(intent);
            }
        });

        btnDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiseasesActivity.class );
                startActivity(intent);
            }
        });

        btnPains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PainActivity.class );
                startActivity(intent);
            }
        });


        btnDrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrugsActivity.class );
                startActivity(intent);
            }
        });


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class );
                startActivity(intent);
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotesActivity.class );
                startActivity(intent);
            }
        });



    }


    private void initWidgets(){

        //imgLogUser = (ImageView) findViewById(R.id.imgViewUser);
        txtUserEmail = (TextView) findViewById(R.id.txtUserEmail);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        imgLogUser = (ImageView) findViewById(R.id.imgViewUser);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnUser = (Button) findViewById(R.id.btnUser);
        btnMeasurement = (Button) findViewById(R.id.btnMeasurement);
        btnDiseases = (Button) findViewById(R.id.btnDiseases);
        btnPains = (Button) findViewById(R.id.btnPains);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnNotes = (Button) findViewById(R.id.btnNotes);
        btnDrugs = (Button) findViewById(R.id.btnDrugs);


    }


    private void getPhoto(){

    }

}