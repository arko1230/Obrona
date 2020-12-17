package com.arko.edc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MySugarAdapter extends RecyclerView.Adapter<MySugarAdapter.holder> {




    String save[];

    public MySugarAdapter(String[] save) {
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

        public holder(@NonNull View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.sugar_date_stamp);
            measure=(TextView)itemView.findViewById(R.id.sugar_result);
            about=(TextView)itemView.findViewById(R.id.sugar_about);

        }
    }


}
