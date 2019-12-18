package com.example.mymergedapp.dbfiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.mymergedapp.model.Student;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myfirst_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_Students = "students";

    private static final String COLUMN_ID = "id";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Student.TBL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public boolean deleteData(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            int result = db.delete(TABLE_Students, COLUMN_ID + "= ?", new String[]{String.valueOf(id)});
            db.close();
            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addStudents(Student student) {
        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", student.getName());
            values.put("email", student.getEmail());
            values.put("phone", student.getPhone());
            database.insert("students", null, values);
            return true;
        } catch (Exception e) {
            Log.d("DbEx", e.toString());
            return false;
        }
    }


    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();

        try {

            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM students", null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    studentList.add(new Student(cursor.getInt(0),
                            cursor.getString(1), cursor.getString(2),
                            cursor.getString(3)));

                }
            }
            return studentList;
        } catch (Exception e) {
            Log.d("Db_Ex", e.toString());

        }
        return studentList;

    }



    public boolean updatStudent(Student student) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("name", student.getName());
            cv.put("email", student.getEmail());
            cv.put("phone", student.getPhone());
            db.update(TABLE_Students, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});
            return true;
        } catch (Exception e) {
            Log.d("DbEx", e.toString());
            return false;
        }
    }

    /**
     * Query only 1 record
     **/
    public Student getStudentById(int stuid) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM students WHERE id = '" + stuid +"'";
        Cursor cursor = db.rawQuery(query, null);
        Student receivedStudent = new Student(stuid,"","","");
        if (cursor.moveToFirst()) {
            receivedStudent.setName(cursor.getString(cursor.getColumnIndex("name")));
            receivedStudent.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            receivedStudent.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
        }
        cursor.close();
        db.close();
        return receivedStudent;
    }
}