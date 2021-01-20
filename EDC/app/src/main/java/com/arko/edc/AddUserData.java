package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class AddUserData extends AppCompatActivity{

    private EditText etxt_userName, etxt_Born, blood_spiner,male_spiner;
    private Button sendUser;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_data);

        etxt_userName = findViewById(R.id.etxt_userName);
        etxt_Born = findViewById(R.id.etxt_Born);
        blood_spiner = findViewById(R.id.blood_spiner);
        male_spiner = findViewById(R.id.male_spiner);
        sendUser = findViewById(R.id.send_User);





        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();





        sendUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user != null){

                    String uid = user.getUid();



                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("User");
                    String userName = etxt_userName.getText().toString();
                    String userAge = etxt_Born.getText().toString();
                    String userBlood = blood_spiner.getText().toString();
                    String userMale = male_spiner.getText().toString();




                    Map newRec = new HashMap();
                    newRec.put("userName",userName);
                    newRec.put("userAge",userAge);
                    newRec.put("userBlood",userBlood);
                    newRec.put("userMale",userMale);
                    current_user_db.push().setValue(newRec);
                    Intent intent = new Intent(AddUserData.this, UserInterfaceActivity.class);
                    startActivity(intent);
                    finish();


                }
                else finish();


            }
        });



    }






    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }






}