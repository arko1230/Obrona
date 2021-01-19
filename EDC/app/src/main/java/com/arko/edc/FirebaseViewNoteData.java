package com.arko.edc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewNoteData extends RecyclerView.ViewHolder  {

    public TextView noteTitle, aboutNote, date;
    public Button btn_del_note;

    public FirebaseViewNoteData(@NonNull View itemView) {
        super(itemView);
        noteTitle = itemView.findViewById(R.id.dbNoteTitle);
        aboutNote = itemView.findViewById(R.id.dbAboutNote);
        date = itemView.findViewById(R.id.dbNoteDate);


        btn_del_note = itemView.findViewById(R.id.btn_del_note);
    }
}
