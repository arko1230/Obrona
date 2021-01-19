package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewTempHolder extends RecyclerView.ViewHolder {

    public TextView date, tempResult, aboutTemperature;
    public Button btn_del_temperature;


    public FirebaseViewTempHolder(@NonNull View itemView) {
        super(itemView);
        aboutTemperature = itemView.findViewById(R.id.aboutTemperature);
       date = itemView.findViewById(R.id.date_t);
       tempResult = itemView.findViewById(R.id.temperatureResult);

       btn_del_temperature = itemView.findViewById(R.id.btn_del_temperature);
    }
}
