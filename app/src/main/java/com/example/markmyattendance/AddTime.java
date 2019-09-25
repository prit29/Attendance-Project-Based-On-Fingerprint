package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddTime extends AppCompatActivity {

    ListView l1;
    DatabaseHandler db;
    List<StudentClass> studentClasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        l1 = findViewById(R.id.list3);
        db = new DatabaseHandler(this);
        studentClasses = db.getAllClass();

        custom newCustom = new custom();
        l1.setAdapter(newCustom);
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.layers,parent,false);

            TextView t1 = convertView.findViewById(R.id.t1);
            TextView t2 = convertView.findViewById(R.id.t2);
            Button b1 = convertView.findViewById(R.id.del);
            Button b2 = convertView.findViewById(R.id.add);
            b1.setVisibility(View.GONE);
            b2.setVisibility(View.GONE);
            t1.setText(studentClasses.get(position).getStd());
            t2.setText(studentClasses.get(position).getLine());

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
                    Intent intent = new Intent(getApplicationContext(),AddTime2.class);
                    intent.putExtra("class",studentClasses.get(position).getStd());
                    intent.putExtra("line",""+studentClasses.get(position).getLine());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }
}
