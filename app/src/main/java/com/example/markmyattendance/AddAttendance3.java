package com.example.markmyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddAttendance3 extends AppCompatActivity {

    TextView t1;
    EditText e1;
    Button b1;
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
