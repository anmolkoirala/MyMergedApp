package com.example.mymergedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymergedapp.dbfiles.DbHelper;
import com.example.mymergedapp.model.Student;

public class StudentUpdateActivity extends AppCompatActivity {

    private Button mUpdateBtn;
    private EditText nameEdittext;
    private EditText emailEdittext;
    private EditText phoneEdittext;
    private DbHelper dbHelper;
    private int receivedStudentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        nameEdittext  = findViewById(R.id.studentNameUpdate);
        emailEdittext = findViewById(R.id.studentEmailUpdate);
        phoneEdittext = findViewById(R.id.studentPhoneUpdate);
        mUpdateBtn    = findViewById(R.id.updateStudentButton);
        dbHelper      = new DbHelper(this);

        try {
            receivedStudentId = getIntent().getIntExtra("Student_ID", 1);
            Log.d("id",String.valueOf(receivedStudentId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Student queriedStudent = dbHelper.getStudentById(receivedStudentId);
        nameEdittext.setText(queriedStudent.getName());
        emailEdittext.setText(queriedStudent.getEmail());
        phoneEdittext.setText(queriedStudent.getPhone());

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent();
            }
        });
    }

    private void updateStudent(){
        String name = nameEdittext.getText().toString().trim();
        String email = emailEdittext.getText().toString().trim();
        String phone = phoneEdittext.getText().toString().trim();
        if(name.isEmpty()){
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
        if(email.isEmpty()){
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
        }
        if(phone.isEmpty()){
            Toast.makeText(this, "Please enter phone", Toast.LENGTH_SHORT).show();
        }
        Student updated = new Student(receivedStudentId,name,email,phone);
        if(dbHelper.updatStudent(updated)){
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
        goBackHome();
    }

    private void goBackHome(){
        startActivity(new Intent(this, ListStudentActivity.class));
    }
}

