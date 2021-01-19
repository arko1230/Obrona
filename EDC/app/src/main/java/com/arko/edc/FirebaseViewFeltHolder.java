package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewFeltHolder extends RecyclerView.ViewHolder  {

    public TextView fettleResult, fettleScale, fettleAbout,date;
    public Button btn_del_felt;

    public FirebaseViewFeltHolder(@NonNull View itemView) {
        super(itemView);
        fettleResult = itemView.findViewById(R.id.dbFeltWhat);
        fettleScale = itemView.findViewById(R.id.dbFeltScale);
        fettleAbout = itemView.findViewById(R.id.dbFeltAbout);
        date = itemView.findViewById(R.id.dbFeltDate);

        btn_del_felt = itemView.findViewById(R.id.btn_del_felt);
    }

}
