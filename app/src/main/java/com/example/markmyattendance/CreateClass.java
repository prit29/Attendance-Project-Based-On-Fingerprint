package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateClass extends AppCompatActivity {

    Button b1;
    EditText e1,e2;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        db = new DatabaseHandler(this);
        b1 = findViewById(R.id.b1);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width*.8),(int)(width*1.2));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().trim().equals("") || e2.getText().toString().trim().equals("")){
                    Toast.makeText(CreateClass.this,"Enter Class And Line",Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addClass(new StudentClass(e1.getText().toString(),e2.getText().toString()));
//                    String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//                    String time2 = time.substring(0,4).concat("/").concat(time.substring(4,6)).concat("/").concat(time.substring(6,8)).concat("_").concat(time.substring(9,11)).concat(":").concat(time.substring(11,13)).concat(":").concat(time.substring(13,15));
//                    db.addRecord(new Record(e1.getText().toString(),""+(Integer.valueOf(s1)-Integer.valueOf(s)),time2,""+(Integer.valueOf(s1)-Integer.valueOf(s))));
                    Intent intent = new Intent(getApplicationContext(),AddClass.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
