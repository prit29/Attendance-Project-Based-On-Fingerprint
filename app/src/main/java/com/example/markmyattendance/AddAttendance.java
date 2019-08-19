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

public class AddAttendance extends AppCompatActivity {

    ListView l1;
    DatabaseHandler db;
    List<StudentClass> studentClasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);

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

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),AddAttendance3.class);
                    intent.putExtra("class",studentClasses.get(position).getStd());
                    intent.putExtra("line",""+studentClasses.get(position).getLine());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }
}
