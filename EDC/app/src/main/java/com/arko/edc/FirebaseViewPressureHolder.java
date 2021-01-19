package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewPressureHolder extends RecyclerView.ViewHolder {

    public TextView pressResult, pulsResult, aboutPressure,date;
    public Button btn_del_press;

    public FirebaseViewPressureHolder(@NonNull View itemView) {
        super(itemView);
        pressResult = itemView.findViewById(R.id.dbPessResult);
        pulsResult = itemView.findViewById(R.id.dbPressPuls);
        aboutPressure = itemView.findViewById(R.id.dbaboutPress);
        date = itemView.findViewById(R.id.dbPressdate);

        btn_del_press = itemView.findViewById(R.id.btn_del_press);
    }

}
