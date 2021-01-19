package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewMainDisease extends RecyclerView.ViewHolder {

    public TextView MainDiseaseName,Time_disease,TimeRecovery,Symptoms,Procedure;
    public Button btn_del_Disease;

    public FirebaseViewMainDisease(@NonNull View itemView) {
        super(itemView);
        MainDiseaseName = itemView.findViewById(R.id.dbDisease_Name);
        Time_disease = itemView.findViewById(R.id.dbTime_disease);
        TimeRecovery = itemView.findViewById(R.id.dbTimeRecovery);
        Symptoms = itemView.findViewById(R.id.dbSymptoms);
        Procedure = itemView.findViewById(R.id.dbProcedure);

        btn_del_Disease = itemView.findViewById(R.id.btn_del_Disease);
    }

}
