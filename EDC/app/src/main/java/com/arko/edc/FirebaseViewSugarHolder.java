package com.arko.edc;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewSugarHolder extends RecyclerView.ViewHolder {

    public TextView aboutSugar, date, sugarResult;



    public FirebaseViewSugarHolder(@NonNull View itemView) {
        super(itemView);

        aboutSugar = itemView.findViewById(R.id.aboutSugar);
        date = itemView.findViewById(R.id.date);
        sugarResult = itemView.findViewById(R.id.sugarResult);


    }
}
