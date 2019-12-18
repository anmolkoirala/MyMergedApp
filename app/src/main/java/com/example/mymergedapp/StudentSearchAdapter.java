package com.example.mymergedapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymergedapp.dbfiles.DbHelper;
import com.example.mymergedapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentSearchAdapter extends RecyclerView.Adapter<StudentSearchAdapter.MystudentHolder>  {

    List<Student> mystudentlist = new ArrayList<>();
    Context context;
    DbHelper dbHelper;
    MystudentHolder myStudentHolder;

    public StudentSearchAdapter(List<Student> mystudentlist, Context context) {
        this.mystudentlist = mystudentlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MystudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlistdisp,parent,false);
        myStudentHolder = new MystudentHolder(view);
        return myStudentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MystudentHolder holder, final int position) {
        final  Student student = mystudentlist.get(position);
        holder.stdname.setText(student.getName());
        holder.stdemail.setText(student.getEmail());
        holder.stdphone.setText(student.getPhone());
        holder.stdupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent(student.getId());
            }
        });

        holder.stdremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm delete?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeStudent(student.getId(),position);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mystudentlist.size();
    }

    public class MystudentHolder extends RecyclerView.ViewHolder{

        TextView stdname, stdemail, stdphone;
        Button stdremove, stdupdate;

        public MystudentHolder(@NonNull View itemView) {
            super(itemView);
            stdname   = itemView.findViewById(R.id.name);
            stdemail  = itemView.findViewById(R.id.email);
            stdphone  = itemView.findViewById(R.id.phone);
            stdremove = itemView.findViewById(R.id.removeStdn);
            stdupdate = itemView.findViewById(R.id.updateStdn);
        }
    }

    private void removeStudent(int remID, int posID) {
        DbHelper db = new DbHelper(context);
        if(db.deleteData(remID)){
            mystudentlist.remove(posID);
            notifyDataSetChanged();
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStudent(int id){
        Intent update = new Intent(context, StudentUpdateActivity.class);
        update.putExtra("Student_ID", id);
        context.startActivity(update);
    }
}