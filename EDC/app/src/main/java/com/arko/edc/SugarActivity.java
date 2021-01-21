package com.arko.edc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SugarActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<DataSetFireSug> mArrayList;
    private FirebaseRecyclerOptions<DataSetFireSug> mOptions;
    private FirebaseRecyclerAdapter<DataSetFireSug,FirebaseViewSugarHolder> mAdapter;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;


    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar);

        mRecyclerView = findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button addSugarButton = (Button) findViewById(R.id.AddSugarActionButton);
        addSugarButton.setOnClickListener(v -> {
            Intent intent = new Intent(SugarActivity.this, AddSugarData.class);
            startActivity(intent);
            finish();
        });

        mUser= FirebaseAuth.getInstance().getCurrentUser();

        String uid = mUser.getUid();

        mArrayList = new ArrayList<DataSetFireSug>();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Sugar");
        mDatabaseReference.keepSynced(true);

        mOptions = new FirebaseRecyclerOptions.Builder<DataSetFireSug>().setQuery(mDatabaseReference,DataSetFireSug.class).build();

        mAdapter = new FirebaseRecyclerAdapter<DataSetFireSug, FirebaseViewSugarHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewSugarHolder holder, int position, @NonNull DataSetFireSug model) {
                holder.aboutSugar.setText(model.getAboutSugar());
                holder.date.setText(model.getDate());
                holder.sugarResult.setText(model.getSugarResult());

                holder.btn_del_sugar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.btn_del_sugar.getContext());
                        builder.setTitle("USUWANIE WPISU Z HISTORI");
                        builder.setMessage("Czy napewno chcesz usunąć ten zapis?");

                        builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Sugar").child(getRef(position).getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();

                    }
                });


            }


            @NonNull
            @Override
            public FirebaseViewSugarHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new FirebaseViewSugarHolder(LayoutInflater.from(SugarActivity.this).inflate(R.layout.sugar_single_row,viewGroup,false));
            }
        };


        mRecyclerView.setAdapter(mAdapter);




    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}