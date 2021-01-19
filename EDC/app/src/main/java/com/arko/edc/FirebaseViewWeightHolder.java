package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewWeightHolder extends RecyclerView.ViewHolder {

    public TextView date, weightResult, weightAbout;
    public Button btn_del_weight;


    public FirebaseViewWeightHolder(@NonNull View itemView) {
        super(itemView);
        weightResult = itemView.findViewById(R.id.dbWeightResult);
        weightAbout = itemView.findViewById(R.id.dbWeightAbout);
        date = itemView.findViewById(R.id.dbWeghtDate);

        btn_del_weight = itemView.findViewById(R.id.btn_del_weight);
    }
}
