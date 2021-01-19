package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewPainHolder extends RecyclerView.ViewHolder {

    public TextView PainPlace,Pain_leve,Pain_metod,When_Pain,When_Pain_start,When_Pain_end;
    public Button btn_del_Pain;

    public FirebaseViewPainHolder(@NonNull View itemView) {
        super(itemView);
        PainPlace = itemView.findViewById(R.id.dbPainPlace);
        Pain_leve = itemView.findViewById(R.id.dbPain_level);
        Pain_metod = itemView.findViewById(R.id.dbPain_metod);
        When_Pain = itemView.findViewById(R.id.dbWhen_Pain);
        When_Pain_start = itemView.findViewById(R.id.dbWhen_Pain_start);
        When_Pain_end = itemView.findViewById(R.id.dbWhen_Pain_end);

        btn_del_Pain = itemView.findViewById(R.id.btn_del_Pain);
    }
}
