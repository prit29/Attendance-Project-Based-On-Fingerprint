package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddStudent extends AppCompatActivity {

    DatabaseHandler db;
    EditText e1;
    Button b1;
    TextView t1;
    List<StudentList> studentLists = new ArrayList<>();
    List<StudentList> studentLists2 = new ArrayList<>();
    int roll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        e1 = findViewById(R.id.name);
        b1 = findViewById(R.id.add2);
        t1 = findViewById(R.id.t1);
        db = new DatabaseHandler(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width*.8),(int)(width*1.2));

        Intent intent = getIntent();
        final String s2 = intent.getStringExtra("class");
        final String s1 = intent.getStringExtra("line");

        studentLists = db.getAllStudentList();
        for (int i = 0; i < studentLists.size(); i++) {
            if(studentLists.get(i).getLine().equals(s1) && studentLists.get(i).getStd().equals(s2))
            {
                studentLists2.add(studentLists.get(i));
            }
        }

        if(studentLists2.size()==0){
            roll = 1;
        }else{
            roll = studentLists2.size()+1;
        }

        t1.setText("Class : " + s2 + "\nLine : " + s1 + "\nRoll : " + roll);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentList ss = new StudentList(String.valueOf(roll),e1.getText().toString(),s2,s1);
                db.addStudent(ss);
                e1.setText("");
                roll++;
                t1.setText("Class : " + s2 + "\nLine : " + s1 + "\nRoll : " + roll);
                //Toast.makeText(getApplicationContext(),ss.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
