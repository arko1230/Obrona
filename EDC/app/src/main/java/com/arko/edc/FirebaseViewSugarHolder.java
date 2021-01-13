package com.arko.edc;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewSugarHolder extends RecyclerView.ViewHolder {

    public TextView aboutSugar, date, sugarResult;
    public Button btn_del_sugar;



    public FirebaseViewSugarHolder(@NonNull View itemView) {
        super(itemView);

        aboutSugar = itemView.findViewById(R.id.aboutSugar);
        date = itemView.findViewById(R.id.date);
        sugarResult = itemView.findViewById(R.id.sugarResult);
        btn_del_sugar = itemView.findViewById(R.id.btn_del_sugar);
//        btn_edit_sugar = itemView.findViewById(R.id.btn_edit_sugar);

    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        String date1 = (dayOfMonth) + "." + (month+1) + "." + year;
//
//
//    }

}
