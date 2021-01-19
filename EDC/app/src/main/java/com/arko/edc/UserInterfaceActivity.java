package com.arko.edc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class UserInterfaceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText e_txtUserName,e_txtAge;
    Button btn_delete, btn_save,btnAllergens,btnDoctor;
    String blood[] = {"Nie wybrano","0-", "0+","B-","B+","A-","A+","AB-","AB+"};


    private RecyclerView mRecyclerView;
//    private ArrayList<DataSetFireUser> mArrayList;
//    private FirebaseRecyclerOptions<DataSetFireUser> mOptions;
//    private FirebaseRecyclerAdapter<DataSetFireUser,FirebaseViewUserHolder> mAdapter;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private Spinner bloodSpiner;
    private String itemBlood;

    private DataSetFireUser mDataSetFireUser;


//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mAdapter.stopListening();
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);




        e_txtUserName=(EditText)findViewById(R.id.e_txtUserName);
        e_txtAge = findViewById(R.id.e_txtAge);
        btn_delete = (Button)findViewById(R.id.btnDelete);
        btn_save = (Button)findViewById(R.id.btnSave);
        bloodSpiner = findViewById(R.id.blood_spiner);
        btnAllergens = findViewById(R.id.btnAllergens);
        btnDoctor = findViewById(R.id.btnDoctor);


        bloodSpiner.setOnItemSelectedListener(this);
        mDataSetFireUser = new DataSetFireUser();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("User");

        ArrayAdapter arrayBlodAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,blood);
        arrayBlodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bloodSpiner.setAdapter(arrayBlodAdapter);
//        mDatabaseReference.keepSynced(true);
//
//        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireUser>().setQuery(mDatabaseReference,DataSetFireUser.class).build();
//        mAdapter = new FirebaseRecyclerAdapter<DataSetFireUser, FirebaseViewUserHolder>(mOptions) {
//            @Override
//            protected void onBindViewHolder(@NonNull FirebaseViewUserHolder holder, int position, @NonNull DataSetFireUser model) {
//                holder
//            }
//
//            @NonNull
//            @Override
//            public FirebaseViewUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
////        };
//
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("User");
//
//        String age = current_user_db.get().toString();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(btn_delete.getContext());
                builder.setTitle("USUń KONTO");
                builder.setMessage("Czy chcesz usunąc wszystkie swoje dane?");

                builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).removeValue();
                        //TODO wylogowanie i wymaganie autoryzacji
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


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemBlood = bloodSpiner.getSelectedItem().toString();

                String userName = e_txtUserName.getText().toString();
                String userAge = e_txtAge.getText().toString();
                String userBlood = itemBlood;


                Map newRec = new HashMap();
                newRec.put("userName",userName);
                newRec.put("userAge",userAge);
                newRec.put("userBlood",userBlood);
                current_user_db.setValue(newRec);
                Intent intent = new Intent(UserInterfaceActivity.this, UserInterfaceActivity.class);
                startActivity(intent);
                finish();


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
    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemBlood = bloodSpiner.getSelectedItem().toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
