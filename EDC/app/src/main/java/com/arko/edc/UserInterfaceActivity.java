package com.arko.edc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInterfaceActivity extends AppCompatActivity {

    EditText e_txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
       // e_txtUserName.setOnFocusChangeListener((View.OnFocusChangeListener) this);
    }


//    EditText getE_txtUserName = (EditText) findViewById(R.id.e_txtUserName);
//    public void onFocusChanges(EditText e_txtUserName, boolean hasFocus){
//        if (hasFocus ){
//            e_txtUserName.setText("");
//        }
//        if (!hasFocus){
//
//        }
//
//    }

//USUNAC PRZYCISK WYSYLALNIA DO BAZY

    public void proces(View view){

        e_txtUserName=(EditText)findViewById(R.id.e_txtUserName);

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference root=db.getReference();

        root.setValue(e_txtUserName.getText().toString());
        e_txtUserName.setText(" ");
        Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();




    }
}