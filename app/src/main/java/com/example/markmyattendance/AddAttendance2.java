package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddAttendance2 extends AppCompatActivity {

    DatabaseHandler db;
    List<StudentList> studentLists = new ArrayList<>();
    List<StudentList> studentLists2 = new ArrayList<>();
    ListView l1;
    TextView t1,t2;
    EditText e1;
    Button b1;
    int roll=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance2);

        l1 = findViewById(R.id.list2);
        t1 = findViewById(R.id.t1);
        e1 = findViewById(R.id.mark);
        t2 = findViewById(R.id.lect);
        b1 = findViewById(R.id.submit);

        Intent intent = getIntent();
        final String s2 = intent.getStringExtra("class");
        final String s1 = intent.getStringExtra("line");
        final String s3 = intent.getStringExtra("lect");
        t1.setText(s1 + " " + s2);
        t2.setText("SUB : " + s3);

        db = new DatabaseHandler(this);
        studentLists = db.getAllStudentList();

        //Toast.makeText(getApplicationContext(),""+studentLists.size(),Toast.LENGTH_LONG).show();
        for (int i = 0; i < studentLists.size(); i++) {
            if(studentLists.get(i).getLine().equals(s1) && studentLists.get(i).getStd().equals(s2))
            {
                studentLists2.add(studentLists.get(i));
            }
        }

        custom newCustom = new custom();
        l1.setAdapter(newCustom);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll = Integer.valueOf(e1.getText().toString());

                for (int i = 0; i <studentLists2.size() ; i++) {
                    if(Integer.valueOf(studentLists2.get(i).getRoll())==roll & studentLists2.get(i).getId()!=100001){
                        studentLists2.get(i).setId(100001);

                        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                        String time2 = time.substring(0,4).concat("/").concat(time.substring(4,6)).concat("/").concat(time.substring(6,8)).concat("_").concat(time.substring(9,11)).concat(":").concat(time.substring(11,13)).concat(":").concat(time.substring(13,15));

                        db.addRecord(new RecordClass(studentLists2.get(i).getName(),
                                studentLists2.get(i).getRoll(),
                                studentLists2.get(i).getStd(),
                                studentLists2.get(i).getLine(),
                                "Accepted",s3,time2));

                        break;
                    }
                }
                Toast.makeText(getApplicationContext(),"Accepted",Toast.LENGTH_SHORT).show();
                e1.setText("");
                custom newCustom = new custom();
                l1.setAdapter(newCustom);
            }
        });
    }
    class custom extends BaseAdapter {

        @Override
        public int getCount() {
            return studentLists2.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.layers2,parent,false);

            TextView t1 = convertView.findViewById(R.id.t1);
            TextView t2 = convertView.findViewById(R.id.t2);
            TextView t3 = convertView.findViewById(R.id.t3);
            TextView t4 = convertView.findViewById(R.id.t4);
            t1.setText(studentLists2.get(position).getName());
            t2.setText(studentLists2.get(position).getRoll());

            if(studentLists2.get(position).getId()==100001) {
                t3.setText("Accepted");
                t3.setTextColor(Color.parseColor("#00FF00"));
            }else {
                t3.setVisibility(View.GONE);
            }

            t4.setVisibility(View.GONE);

            return convertView;
        }
    }
}
