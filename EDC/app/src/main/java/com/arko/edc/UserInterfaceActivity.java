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
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInterfaceActivity extends AppCompatActivity {

    EditText e_txtUserName, e_txtBlood;
    Button btn_delete, btn_save;
    private RecyclerView mRecyclerView;
    private ArrayList<DataSetFireSug> mArrayList;
    private FirebaseRecyclerOptions<DataSetFireSug> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireSug,FirebaseViewSugarHolder> mAdapter;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);

        e_txtUserName=(EditText)findViewById(R.id.e_txtUserName);
        e_txtBlood = (EditText)findViewById(R.id.e_txtBlodChoose);
        btn_delete = (Button)findViewById(R.id.btnDelete);
        btn_save = (Button)findViewById(R.id.btnSave);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

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
                String userName = e_txtUserName.getText().toString().trim();
                String bloodChoose = e_txtBlood.getText().toString().trim();

                current_user_db.child("userName").setValue(userName);
                current_user_db.child("bloodGroup").setValue(bloodChoose);

                }

        });




    }






}
