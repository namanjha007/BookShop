package com.example.codemail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class BookList extends AppCompatActivity {
    TextView brd,scl,cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklist_activity);

        brd = (TextView)findViewById(R.id.brd);
        scl = (TextView)findViewById(R.id.scl);
        cls = (TextView)findViewById(R.id.cls);

        String a = getIntent().getStringExtra("BOARD");
        String b = getIntent().getStringExtra("SCHOOL");
        String c = getIntent().getStringExtra("CLASS");

        brd.setText(a);
        scl.setText(b);
        cls.setText(c);

    }
}
