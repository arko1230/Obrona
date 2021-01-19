package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewAllergenHolder extends RecyclerView.ViewHolder {

    public TextView allergenName, allergenFactor, allergenShows,allergenHide,date;
    public Button btn_del_allergen;

    public FirebaseViewAllergenHolder(@NonNull View itemView) {
        super(itemView);
        allergenName = itemView.findViewById(R.id.name_alergens);
        allergenFactor = itemView.findViewById(R.id.dbAllergenFactor);
        allergenShows = itemView.findViewById(R.id.dbAllergenShows);
        allergenHide = itemView.findViewById(R.id.dbAllergenHide);
        date = itemView.findViewById(R.id.dbdate_allergen);
        btn_del_allergen = itemView.findViewById(R.id.btn_del_allergen);
    }
}
