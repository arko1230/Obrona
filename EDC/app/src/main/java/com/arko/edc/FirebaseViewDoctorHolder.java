package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewDoctorHolder extends RecyclerView.ViewHolder {

    public TextView doctorName, doctorType, doctorAbout,doctorContakt;
    public Button btn_del_doctor;

    public FirebaseViewDoctorHolder(@NonNull View itemView) {
        super(itemView);
        doctorName = itemView.findViewById(R.id.dbDoctorName);
        doctorType = itemView.findViewById(R.id.dbDoctorType);
        doctorAbout = itemView.findViewById(R.id.dbDoctorabout);
        doctorContakt = itemView.findViewById(R.id.dbDoctorContakt);

        btn_del_doctor = itemView.findViewById(R.id.btn_del_doctor);
    }


}
