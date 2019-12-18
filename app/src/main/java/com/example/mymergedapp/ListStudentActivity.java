package com.example.mymergedapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mymergedapp.dbfiles.DbHelper;
import com.example.mymergedapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity {

    DbHelper dbHelper;
    List<Student> updateStudent = new ArrayList<>();

    public static StudentSearchAdapter searchStudentAdapter;
    public static RecyclerView recyclerview;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        recyclerview         = findViewById(R.id.student_edit);
        dbHelper             = new DbHelper(this);
        updateStudent        = dbHelper.getStudents();

        searchStudentAdapter = new StudentSearchAdapter(updateStudent,this);
        layoutManager        = new LinearLayoutManager(this);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(searchStudentAdapter);
    }
}
