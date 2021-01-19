package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewSP02Holder extends RecyclerView.ViewHolder {


    public TextView oxygenResult, aboutOxygen, date;
    public Button btn_del_SP02;


    FirebaseViewSP02Holder(@NonNull View itemView) {
        super(itemView);
        oxygenResult = itemView.findViewById(R.id.dbResultSP02);
        aboutOxygen = itemView.findViewById(R.id.dbAboutSP02);
        date = itemView.findViewById(R.id.dbDateSp02);


        btn_del_SP02 = itemView.findViewById(R.id.btn_del_SP02);
    }



}
