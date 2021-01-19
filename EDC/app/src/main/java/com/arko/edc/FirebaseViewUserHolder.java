package com.arko.edc;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewUserHolder extends RecyclerView.ViewHolder {

    private String blood;
    private EditText e_txtUserName,e_txtAge;

    public FirebaseViewUserHolder(@NonNull View itemView) {
        super(itemView);
              e_txtUserName = itemView.findViewById(R.id.e_txtUserName);
        e_txtAge = itemView.findViewById(R.id.e_txtAge);
    }
}
