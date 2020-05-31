package com.example.codemail.sellbooks;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codemail.R;

import java.util.ArrayList;

public class SellActivity extends AppCompatActivity {
    Spinner Yearspinner, Classspinner, Subjectspinner;
    EditText bookname, price;
    Button butuon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_sellbooks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sell Books");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));

        Yearspinner = findViewById(R.id.Yearspinner);
        Classspinner = findViewById(R.id.Classspinner);
        Subjectspinner = findViewById(R.id.Subjectspinner);
        price = findViewById(R.id.price);
        bookname  = findViewById(R.id.bookname);
        butuon = findViewById(R.id.butuon);
        price = findViewById(R.id.price);

        ArrayList<Integer> yearList = new ArrayList<>();

        for(int i=1900;i<=2100;i++)
        {
            yearList.add(i);
        }

        ArrayAdapter<Integer> arrayAdapter1 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_item, yearList);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Yearspinner.setAdapter(arrayAdapter1);
        int position1 = arrayAdapter1.getPosition(2000);
        Yearspinner.setSelection(position1);

        ArrayList<String> classlist = new ArrayList<>();
        for(int j=1;j<=12;j++)
        {
            classlist.add("Class "+j);
        }

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,classlist);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Classspinner.setAdapter(arrayAdapter2);

        ArrayList<String> subjectlist = new ArrayList<>();
        subjectlist.add("Accountancy");
        subjectlist.add("Biology");
        subjectlist.add("Business");
        subjectlist.add("Chemistry");
        subjectlist.add("Civics");
        subjectlist.add("Commerce");
        subjectlist.add("Economics");
        subjectlist.add("English");
        subjectlist.add("English Language");
        subjectlist.add("English Literature");
        subjectlist.add("Geography");
        subjectlist.add("Hindi");
        subjectlist.add("Hindi Language");
        subjectlist.add("Hindi Literature");
        subjectlist.add("History");
        subjectlist.add("Mathematics");
        subjectlist.add("Physics");
        subjectlist.add("Science");
        subjectlist.add("Statistics");

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjectlist);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Subjectspinner.setAdapter(arrayAdapter3);

        butuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bookname.getText().toString();
                String year = Yearspinner.getSelectedItem().toString();
                String clas = Classspinner.getSelectedItem().toString();
                String subject = Subjectspinner.getSelectedItem().toString();
                String pric = price.getText().toString();
                if((name.isEmpty())&&(pric.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"Fiels are Empty",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(name.isEmpty())
                {
                    bookname.setError("Enter Book's Name");
                    bookname.requestFocus();
                    return;
                }
                else if(pric.isEmpty())
                {
                    price.setError("Enter Book's Price");
                    price.requestFocus();
                    return;
                }
                else{
                    Intent intent = new Intent(SellActivity.this, UploadimageActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("year", year);
                    intent.putExtra("clas", clas);
                    intent.putExtra("subject", subject);
                    intent.putExtra("price", pric);
                    startActivity(intent);
                }
            }
        });

    }


    private AdapterView.OnItemSelectedListener Subject = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
            ((TextView) parent.getChildAt(0)).setTextSize(5);
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            }
        return super.onOptionsItemSelected(item);
    }
}