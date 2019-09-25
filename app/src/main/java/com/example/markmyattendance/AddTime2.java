package com.example.markmyattendance;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddTime2 extends AppCompatActivity {

    ListView l1;
    DatabaseHandler db;
    List<TimeTable> timeTables = new ArrayList<>();
    List<TimeTable> timeTables2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String s2 = intent.getStringExtra("class");
        final String s1 = intent.getStringExtra("line");

        l1 = findViewById(R.id.list1);
        db = new DatabaseHandler(this);
        timeTables = db.getAllTimeTable();

        for (int i = 0; i < timeTables.size(); i++) {
            if(timeTables.get(i).getLine().equals(s1) && timeTables.get(i).getStd().equals(s2))
            {
                timeTables2.add(timeTables.get(i));
            }
        }

        custom newCustom = new custom();
        l1.setAdapter(newCustom);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CreateTime.class);
                intent.putExtra("class",s2);
                intent.putExtra("line",s1);
                startActivity(intent);
            }
        });
    }

    class custom extends BaseAdapter {

        @Override
        public int getCount() {
            return timeTables2.size();
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
            convertView = getLayoutInflater().inflate(R.layout.layers4,parent,false);

            TextView t1 = convertView.findViewById(R.id.t1);
            TextView t2 = convertView.findViewById(R.id.t2);
            TextView t3 = convertView.findViewById(R.id.t3);
            t1.setText(timeTables2.get(position).getDay() + " " + timeTables2.get(position).getSub());
            t2.setText("Start : "+timeTables2.get(position).getStarth()+" "+timeTables2.get(position).getStartm());
            t3.setText("End : "+timeTables2.get(position).getEndh()+" "+timeTables2.get(position).getEndm());

            return convertView;
        }
    }

}
