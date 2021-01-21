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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInterfaceActivity extends AppCompatActivity {

    Button btn_delete, addInfoButton, btnAllergens, btnDoctor;


    private RecyclerView mRecyclerView;
    private ArrayList<DataSetFireUser> mArrayList;
    private FirebaseRecyclerOptions<DataSetFireUser> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireUser,FirebaseViewUserHolder> mAdapter;
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
        setContentView(R.layout.activity_user_interface);


        mRecyclerView = findViewById(R.id.recycle_view_user);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_delete = (Button) findViewById(R.id.btnDelete);
        btnAllergens = findViewById(R.id.btnAllergens);
        btnDoctor = findViewById(R.id.btnDoctor);
        addInfoButton = (Button) findViewById(R.id.btnAddUserInfo);

        addInfoButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInterfaceActivity.this, AddUserData.class);
            startActivity(intent);
            finish();
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(btn_delete.getContext());
                builder.setTitle("USUN KONTO");
                builder.setMessage("Czy chcesz usunąc wszystkie swoje dane?");

                builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("User");

                        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).removeValue();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(btn_delete.getContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
//
                builder.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });

        btnAllergens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInterfaceActivity.this, AllergensActivity.class);
                startActivity(intent);
            }
        });

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInterfaceActivity.this, DoctorActivity.class);
                startActivity(intent);
            }
        });


        mUser= FirebaseAuth.getInstance().getCurrentUser();

        String uid = mUser.getUid();

        mArrayList = new ArrayList<DataSetFireUser>();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("User");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireUser>().setQuery(mDatabaseReference,DataSetFireUser.class).build();

        mAdapter = new FirebaseRecyclerAdapter<DataSetFireUser, FirebaseViewUserHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewUserHolder holder, int position, @NonNull DataSetFireUser model) {
                holder.userName.setText(model.getUserName());
                holder.userBlood.setText(model.getUserBlood());
                holder.userAge.setText(model.getUserAge());
                holder.userMale.setText(model.getUserMale());
            }


            @NonNull
            @Override
            public FirebaseViewUserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new FirebaseViewUserHolder(LayoutInflater.from(UserInterfaceActivity.this).inflate(R.layout.user_single_row,viewGroup,false));
            }
        };


        mRecyclerView.setAdapter(mAdapter);




    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}




