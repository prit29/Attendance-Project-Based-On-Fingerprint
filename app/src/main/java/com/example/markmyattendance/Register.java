package com.example.markmyattendance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class Register extends AppCompatActivity {

    DatabaseHandler db;
    List<StudentList> studentLists = new ArrayList<>();
    List<StudentList> studentLists2 = new ArrayList<>();
    ListView l1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        l1 = findViewById(R.id.list2);
        t1 = findViewById(R.id.t1);

        Intent intent = getIntent();
        final String s2 = intent.getStringExtra("class");
        final String s1 = intent.getStringExtra("line");
        t1.setText(s1 + " " + s2);

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
            t3.setVisibility(View.GONE);
            t4.setVisibility(View.GONE);

           /* builder = new AlertDialog.Builder(AddClass.this);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.setMessage("Are You Sure?") .setTitle("Delete");

                    builder.setMessage("Are You Sure?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    db.deletecon(studentClasses.get(position).getId());
                                    finish();
                                    startActivity(getIntent());
                                    notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete");
                    alert.show();
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),AddStudent.class);
                    intent.putExtra("class",studentClasses.get(position).getStd());
                    intent.putExtra("line",""+studentClasses.get(position).getLine());
                    startActivity(intent);
                }
            }); */

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),""+studentLists2.get(position).getId(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),AddHistory.class);
                    intent.putExtra("class",studentLists2.get(position).getStd());
                    intent.putExtra("line",""+studentLists2.get(position).getLine());
                    intent.putExtra("name",studentLists2.get(position).getName());
                    intent.putExtra("roll",""+studentLists2.get(position).getRoll());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }
}
