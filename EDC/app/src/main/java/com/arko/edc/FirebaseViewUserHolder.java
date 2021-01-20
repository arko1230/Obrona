package com.arko.edc;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewUserHolder extends RecyclerView.ViewHolder {

    public TextView userAge, userBlood, userMale, userName;

    public FirebaseViewUserHolder(@NonNull View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.dbuserName);
        userBlood = itemView.findViewById(R.id.dbUserBlod);
        userAge = itemView.findViewById(R.id.dbUserBorn);
        userMale = itemView.findViewById(R.id.dbUserMale);

    }
}
