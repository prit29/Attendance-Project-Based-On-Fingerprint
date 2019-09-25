package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddAttendance3 extends AppCompatActivity {

    TextView t1;
    EditText e1;
    Button b1;
    DatabaseHandler db;
    List<TimeTable> timeTables = new ArrayList<>();
    List<TimeTable> timeTables2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance3);

        t1 = findViewById(R.id.t1);
        e1 = findViewById(R.id.e1);
        b1 = findViewById(R.id.b1);

        Intent intent = getIntent();
        final String s2 = intent.getStringExtra("class");
        final String s1 = intent.getStringExtra("line");
        t1.setText(s1 + " " + s2);

        db = new DatabaseHandler(this);
        timeTables = db.getAllTimeTable();

        for (int i = 0; i < timeTables.size(); i++) {
            if(timeTables.get(i).getLine().equals(s1) && timeTables.get(i).getStd().equals(s2))
            {
                timeTables2.add(timeTables.get(i));
            }
        }

        Calendar c = Calendar.getInstance();
        int a1 = c.get(Calendar.HOUR_OF_DAY);
        int a2 = c.get(Calendar.MINUTE);
        int a3 = c.get(Calendar.DAY_OF_WEEK);

        for (int i = 0 ; i< timeTables2.size() ; i++)
        {
            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.HOUR_OF_DAY,timeTables2.get(i).getStarth());
            c1.set(Calendar.MINUTE,timeTables2.get(i).getStartm());

            Calendar c2 = Calendar.getInstance();
            c2.set(Calendar.HOUR_OF_DAY,timeTables2.get(i).getEndh());
            c2.set(Calendar.MINUTE,timeTables2.get(i).getEndm());

            long time=   c.getTimeInMillis();
            long time2=   c1.getTimeInMillis();
            long time3=   c2.getTimeInMillis();

            if(a3 == 2 && timeTables2.get(i).getDay().equals("Monday") && time <= time3 && time2 <= time)
            {
                e1.setText(timeTables2.get(i).getSub());
                break;
            }
            else if(a3 == 3 && timeTables2.get(i).getDay().equals("Tuesday") && time <= time3 && time2 <= time)
            {
                e1.setText(timeTables2.get(i).getSub());
                break;
            }
            else if(a3 == 4 && timeTables2.get(i).getDay().equals("Wednesday") && time <= time3 && time2 <= time)
            {
                e1.setText(timeTables2.get(i).getSub());
                break;
            }
            else if(a3 == 5 && timeTables2.get(i).getDay().equals("Thursday") && time <= time3 && time2 <= time)
            {
                e1.setText(timeTables2.get(i).getSub());
                break;
            }
            else if(a3 == 6 && timeTables2.get(i).getDay().equals("Friday") && time <= time3 && time2 <= time)
            {
                e1.setText(timeTables2.get(i).getSub());
                break;
            }
            else if(a3 == 7 && timeTables2.get(i).getDay().equals("Saturday") && time <= time3 && time2 <= time)
            {
                e1.setText(timeTables2.get(i).getSub());
                break;
            }
            else
            {
                e1.setText("No Lect now..");
            }
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddAttendance2.class);
                intent.putExtra("class",s2);
                intent.putExtra("line",s1);
                intent.putExtra("lect",e1.getText().toString());
                startActivity(intent);
            }
        });
    }
}
