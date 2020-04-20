package com.example.codemail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codemail.R;

public class BuyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button bt;
    Spinner board, school, spinclass;
    TextView boardtxt, classtxt, schooltxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_main);
        bt = findViewById(R.id.nextbutton);
        board = findViewById(R.id.boardspin);
        school = findViewById(R.id.schoolspin);
        spinclass = findViewById(R.id.classspin);
        boardtxt=findViewById(R.id.boardtxt);
        classtxt=findViewById(R.id.classtxt);
        final ArrayAdapter<CharSequence> adapterschool= ArrayAdapter.createFromResource(this,
                R.array.school_list, android.R.layout.simple_spinner_item);
        adapterschool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        school.setAdapter(adapterschool);
        school.setOnItemSelectedListener(this);
        final ArrayAdapter<CharSequence> adapterboard = ArrayAdapter.createFromResource(this,
                R.array.board_list, android.R.layout.simple_spinner_item);
        adapterboard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        board.setAdapter(adapterboard);
        board.setOnItemSelectedListener(this);
        final ArrayAdapter<CharSequence> adapterclass = ArrayAdapter.createFromResource(this,
                R.array.class_list, android.R.layout.simple_spinner_item);
        adapterclass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinclass.setAdapter(adapterclass);
        spinclass.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show();
        switch (parent.getId()) {
            case R.id.boardspin:

                String boardbyuser = parent.getItemAtPosition(position).toString();
                Toast.makeText(this, "Board Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.schoolspin:
                schooltxt = findViewById(R.id.schooltxt);
                String schoolbyuser = parent.getItemAtPosition(position).toString();
                Toast.makeText(this, "School Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.classspin:
                String classp = parent.getItemAtPosition(position).toString();

                Toast.makeText(this, "class Selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "aye yo", Toast.LENGTH_LONG);
                break;
        }
    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }
}