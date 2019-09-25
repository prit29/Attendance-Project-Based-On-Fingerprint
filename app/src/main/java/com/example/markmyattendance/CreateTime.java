package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateTime extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button b1,start,end;
    EditText e2;
    TextView starttime,endtime;
    Spinner s1;
    DatabaseHandler db;
    String[] country = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" , "Saturday"};
    private int mHour, mMinute,mHour2,mMinute2;

    String DAY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time);

        db = new DatabaseHandler(this);
        b1 = findViewById(R.id.b1);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        starttime = findViewById(R.id.starttime);
        endtime = findViewById(R.id.endtime);
        s1 = findViewById(R.id.s1);
        e2 = findViewById(R.id.e2);

        Intent intent = getIntent();
        final String s10 = intent.getStringExtra("class");
        final String s11 = intent.getStringExtra("line");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width*.8),(int)(width*1.2));

        s1.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(aa);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e2.getText().toString().trim().equals("")){
                    Toast.makeText(CreateTime.this,"Enter Subject",Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addTimeTable(new TimeTable(s10,s11,DAY,mHour,mMinute,mHour2,mMinute2,e2.getText().toString()));
                    Intent intent = new Intent(getApplicationContext(),AddTime.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateTime.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            String format;
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                mHour = hourOfDay;
                                mMinute = minute;
                                starttime.setText(""+mHour+":"+mMinute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour2 = c.get(Calendar.HOUR_OF_DAY);
                mMinute2 = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateTime.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            String format;
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                mHour2 = hourOfDay;
                                mMinute2 = minute;
                                endtime.setText(""+mHour2+":"+mMinute2);
                            }
                        }, mHour2, mMinute2, true);
                timePickerDialog.show();
            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
        DAY = country[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
