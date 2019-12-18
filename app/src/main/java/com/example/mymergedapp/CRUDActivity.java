package com.example.mymergedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymergedapp.dbfiles.DbHelper;
import com.example.mymergedapp.model.Student;

public class CRUDActivity extends AppCompatActivity {
    EditText editTextName,editTextEmail,editTextPhone;
    Button btnInsert, btnListStudent;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_operation);
        editTextName  = findViewById(R.id.stname);
        editTextEmail = findViewById(R.id.stemail);
        editTextPhone = findViewById(R.id.stphone);

        btnInsert      = findViewById(R.id.btnStSave);
        btnListStudent = findViewById(R.id.btnAllStudent);

        btnListStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CRUDActivity.this, ListStudentActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DbHelper(this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = editTextName.getText().toString();
                String e = editTextEmail.getText().toString();
                String p = editTextPhone.getText().toString();

                Student student = new Student(0,n,e,p);

                if(dbHelper.addStudents(student)){
                    Toast.makeText(CRUDActivity.this, "Student Successfully Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

