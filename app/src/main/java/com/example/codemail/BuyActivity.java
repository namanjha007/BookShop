package com.example.codemail;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity  {
    Button bt;
    Spinner board, school, sclass;

    DatabaseReference db1;
    DatabaseReference vv [] = new DatabaseReference[100];
    ArrayList<String>[] xx= new ArrayList[100];
    ArrayList<String> arrayList_board= new ArrayList<>();
    ArrayAdapter<String> arrayAdapter_board, arrayAdapter_school, arrayAdapter_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_main);


        bt = findViewById(R.id.nextbutton);
        board = findViewById(R.id.boardspin);
        school = findViewById(R.id.schoolspin);
        sclass = findViewById(R.id.classspin);

        db1 = FirebaseDatabase.getInstance().getReference().child("BOARD");





        db1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String x = areaSnapshot.getValue(String.class);
                    arrayList_board.add(x);

                }

                arrayAdapter_board = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_board);
                arrayAdapter_board.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                board.setAdapter(arrayAdapter_board);
                int l = arrayList_board.size();
                for (int i=0;i<l;i++)
                {
                    vv[i] = FirebaseDatabase.getInstance().getReference().child(arrayList_board.get(i));
                    final int finalI = i;
                    vv[i].addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                String x = areaSnapshot.getValue(String.class);
                                xx[finalI].add(x);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    xx[i] = new ArrayList<>();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        board.setAdapter(arrayAdapter_board);



        board.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                arrayAdapter_school = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,xx[position]);

                school.setAdapter(arrayAdapter_school);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}