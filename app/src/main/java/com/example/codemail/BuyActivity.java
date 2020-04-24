package com.example.codemail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button bt;
    Spinner board, school, spinclass;
    TextView boardtxt, classtxt, schooltxt;
    DatabaseReference dr,drschool,drclass;
    String boardbyuser,schoolbyuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_main);
        bt = findViewById(R.id.nextbutton);
        board = findViewById(R.id.boardspin);
        school = findViewById(R.id.schoolspin);
        spinclass = findViewById(R.id.classspin);
        boardtxt = findViewById(R.id.boardtxt);
        classtxt = findViewById(R.id.classtxt);
        school.setOnItemSelectedListener(this);
        board.setOnItemSelectedListener(this);
        spinclass.setOnItemSelectedListener(this);
        dr = FirebaseDatabase.getInstance().getReference().child("spinners").child("board");
        String key = dr.getKey();
        Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
        Log.e("Count " ,""+key);
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                Toast.makeText(getApplicationContext(),"enter on data change",Toast.LENGTH_SHORT).show();
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(),"inside for loop",Toast.LENGTH_LONG).show();
                    String titlename = dataSnapshot1.getKey();
                    titleList.add(titlename);
                    Toast.makeText(getApplicationContext(),titlename,Toast.LENGTH_LONG).show();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                board.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuyActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.boardspin:
                boardbyuser = parent.getItemAtPosition(position).toString();
                Toast.makeText(this, "Board Selected", Toast.LENGTH_SHORT).show();
                boardselected();
                break;
            case R.id.schoolspin:
                schooltxt = findViewById(R.id.schooltxt);
                schoolbyuser = parent.getItemAtPosition(position).toString();
                Toast.makeText(this, "School Selected", Toast.LENGTH_SHORT).show();
                schoolselected();
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
    public void boardselected(){
        drschool= FirebaseDatabase.getInstance().getReference().child("spinners").child("board").child(boardbyuser);
        String keyschool = dr.getKey();
        Toast.makeText(getApplicationContext(), keyschool, Toast.LENGTH_LONG).show();
        Log.e("Count of school " ,""+keyschool);
        drschool.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                Toast.makeText(getApplicationContext(),"enter on data change",Toast.LENGTH_SHORT).show();
                Log.e("Count of school " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(),"inside for loop",Toast.LENGTH_LONG).show();
                    String titlename = dataSnapshot1.getKey();
                    titleList.add(titlename);
                    Toast.makeText(getApplicationContext(),titlename,Toast.LENGTH_LONG).show();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                school.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuyActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void schoolselected(){
        drclass= FirebaseDatabase.getInstance().getReference().child("spinners").child("board").child(boardbyuser).child(schoolbyuser);
        String keyschool = drclass.getKey();
        Toast.makeText(getApplicationContext(), keyschool, Toast.LENGTH_LONG).show();
        drclass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                Toast.makeText(getApplicationContext(),"enter on data change",Toast.LENGTH_SHORT).show();
                Log.e("Count of class" ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(),"inside for loop",Toast.LENGTH_LONG).show();
                    String titlename = dataSnapshot1.getKey();
                    titleList.add(titlename);
                    Toast.makeText(getApplicationContext(),titlename,Toast.LENGTH_LONG).show();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinclass.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuyActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}