package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewFamDiseasesHolder extends RecyclerView.ViewHolder  {

    public TextView DiseaseName, Who_disease, ProgressDisease,DrugsDisease,DiseaseInfoExtra;
    public Button btn_del_FamilyDisease;

    public FirebaseViewFamDiseasesHolder(@NonNull View itemView) {
        super(itemView);
        DiseaseName = itemView.findViewById(R.id.dbFamDisease_Name);
        Who_disease = itemView.findViewById(R.id.dbFamDisease_Who);
        ProgressDisease = itemView.findViewById(R.id.dbFamDisease_Progres);
        DrugsDisease = itemView.findViewById(R.id.dbFamDisease_Drugs);
        DiseaseInfoExtra = itemView.findViewById(R.id.dbFamDisease_ExtraInfo);

        btn_del_FamilyDisease = itemView.findViewById(R.id.btn_del_FamilyDisease);
    }

}
