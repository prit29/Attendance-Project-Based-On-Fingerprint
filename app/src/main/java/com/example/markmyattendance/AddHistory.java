package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddHistory extends AppCompatActivity {

    DatabaseHandler db;
    List<RecordClass> studentRecord = new ArrayList<>();
    List<RecordClass> studentRecord2 = new ArrayList<>();
    ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);

        l1 = findViewById(R.id.list5);
        db = new DatabaseHandler(this);
        studentRecord = db.getAllRecord();

        Intent intent = getIntent();
        final String s2 = intent.getStringExtra("class");
        final String s1 = intent.getStringExtra("line");
        final String s3 = intent.getStringExtra("name");
        final String s4 = intent.getStringExtra("roll");

        for (int i = 0; i < studentRecord.size(); i++) {
            if(studentRecord.get(i).getLine().equals(s1) && studentRecord.get(i).getStd().equals(s2) && studentRecord.get(i).getName().equals(s3) && studentRecord.get(i).getRoll().equals(s4))
            {
                studentRecord2.add(studentRecord.get(i));
            }
        }

        custom newCustom = new custom();
        l1.setAdapter(newCustom);
    }

    class custom extends BaseAdapter {

        @Override
        public int getCount() {
            return studentRecord2.size();
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
            convertView = getLayoutInflater().inflate(R.layout.layers3,parent,false);

            TextView t1 = convertView.findViewById(R.id.t1);
            TextView t2 = convertView.findViewById(R.id.t2);
            TextView t3 = convertView.findViewById(R.id.t3);
            TextView t4 = convertView.findViewById(R.id.t4);
            TextView t5 = convertView.findViewById(R.id.t5);
            TextView t6 = convertView.findViewById(R.id.t6);
            TextView t7 = convertView.findViewById(R.id.t7);
            t1.setText(studentRecord2.get(position).getName());
            t2.setText(studentRecord2.get(position).getRoll());
            t3.setText(studentRecord2.get(position).getStd());
            t4.setText(studentRecord2.get(position).getLine());
            t5.setText(studentRecord2.get(position).getLect());
            t6.setText(studentRecord2.get(position).getStatus());
            t7.setText(studentRecord2.get(position).getTime());

            return convertView;
        }
    }
}
