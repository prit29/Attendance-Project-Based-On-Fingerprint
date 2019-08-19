package com.example.markmyattendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddClass extends AppCompatActivity {

    DatabaseHandler db;
    List<StudentClass> studentClasses;
    List<StudentList> studentLists = new ArrayList<>();
    List<Integer> stud = new ArrayList<>();
    ListView l1;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        l1 = findViewById(R.id.list1);

        db = new DatabaseHandler(this);
        studentClasses = db.getAllClass();
        studentLists = db.getAllStudentList();

        custom newCustom = new custom();
        l1.setAdapter(newCustom);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddClass.this,CreateClass.class);
                startActivity(i);
            }
        });
    }

    class custom extends BaseAdapter {

        @Override
        public int getCount() {
            return studentClasses.size();
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
        public View getView(final int position,View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.layers,parent,false);

            TextView t1 = convertView.findViewById(R.id.t1);
            TextView t2 = convertView.findViewById(R.id.t2);
            Button b1 = convertView.findViewById(R.id.del);
            Button b2 = convertView.findViewById(R.id.add);
            t1.setText(studentClasses.get(position).getStd());
            t2.setText(studentClasses.get(position).getLine());

            builder = new AlertDialog.Builder(AddClass.this);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.setMessage("Are You Sure?") .setTitle("Delete");

                    builder.setMessage("Are You Sure?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    //Toast.makeText(getApplicationContext(),""+studentLists.size(),Toast.LENGTH_SHORT).show();

                                    for (int i = 0; i < studentLists.size() ; i++) {

                                        if(studentLists.get(i).getStd().equals(studentClasses.get(position).getStd()) && studentLists.get(i).getLine().equals(studentClasses.get(position).getLine()))
                                        {
                                            db.deleteStudent(studentLists.get(i).getId());
                                        }
                                    }

                                    //Toast.makeText(getApplicationContext(),stud.toString(),Toast.LENGTH_LONG).show();
                                    db.deleteClass(studentClasses.get(position).getId());
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
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),AddStudent.class);
                    intent.putExtra("class",studentClasses.get(position).getStd());
                    intent.putExtra("line",""+studentClasses.get(position).getLine());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
