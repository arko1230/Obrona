package com.arko.edc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.PublicKey;

public class MySugarAdapter extends RecyclerView.Adapter<MySugarAdapter.holder> {




    String save[];
    ImageView deleteSugar;
    TextView data, sugarResult, aboutSugar;

    public MySugarAdapter(String[] save) {
        //super(options);
      this.save = save;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.sugar_single_row,parent,false);
        return new holder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.measure.setText(save[position]);
        holder.date.setText(save[position]);




    }

    @Override
    public int getItemCount() {
        return save.length;
    }


    class holder extends RecyclerView.ViewHolder{


        TextView date, measure, about;
        Button delete;
        FloatingActionButton add;

        public holder(@NonNull View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.sugar_date_stamp);
            measure=(TextView)itemView.findViewById(R.id.sugar_result);
            about=(TextView)itemView.findViewById(R.id.sugar_about);

            delete=(Button)itemView.findViewById(R.id.btnDeleteSugar);

        }
    }


}
