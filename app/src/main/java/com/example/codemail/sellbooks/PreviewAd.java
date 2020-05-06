package com.example.codemail.sellbooks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.MainActivity;
import com.example.codemail.R;
import com.example.codemail.ui.home.CategoryViewHolder;
import com.example.codemail.ui.home.CategoryViewHolder1;
import com.example.codemail.ui.home.LiveBooks;
import com.example.codemail.ui.home.LiveBooks1;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PreviewAd extends AppCompatActivity {

    DatabaseReference databaseReference, databaseReference1;
    TextView nameText,yearText,subjectText,classText,priceText;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    Button buut1, buut2;
    FirebaseRecyclerOptions<AdImages> options;
    FirebaseRecyclerAdapter<AdImages,AdImageAdapter> adapter;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_previewad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sell Books");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading ..........");
        progressDialog.show();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String User = user.getUid();

        String c = getIntent().getStringExtra("db");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(User).child("SellBooks").child(c);

        buut1 = findViewById(R.id.butuon);
        buut2 = findViewById(R.id.butuon1);
        recyclerView = findViewById(R.id.imagePreview);
        nameText = findViewById(R.id.bookTitle);
        yearText = findViewById(R.id.year);
        subjectText = findViewById(R.id.subject);
        classText = findViewById(R.id.clas);
        priceText = findViewById(R.id.price);

        buut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue();
                Intent inty = new Intent(PreviewAd.this, SellActivity.class);
                startActivity(inty);
            }
        });

        buut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent inty1 = new Intent(PreviewAd.this, MainActivity.class);
               startActivity(inty1);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("bookname").getValue().toString();
                nameText.setText(name);
                String year = dataSnapshot.child("year").getValue().toString();
                yearText.setText(year);
                String subject = dataSnapshot.child("subject").getValue().toString();
                subjectText.setText(subject);
                String clas = dataSnapshot.child("class").getValue().toString();
                classText.setText(clas);
                String price = dataSnapshot.child("price").getValue().toString();
                priceText.setText(price);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference1 = databaseReference.child("Images");
        options = new FirebaseRecyclerOptions.Builder<AdImages>().setQuery(databaseReference1,AdImages.class).build();
        adapter = new FirebaseRecyclerAdapter<AdImages, AdImageAdapter>(options) {
            @Override
            protected void onBindViewHolder(AdImageAdapter adImageAdapter, int i,AdImages adImages) {

                Picasso.get().load(adImages.getLink()).into(adImageAdapter.i1, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(PreviewAd.this,"Image Load Failed",Toast.LENGTH_LONG).show();
                    }
                });

            }

            @NonNull
            @Override
            public AdImageAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_image,parent,false);

                return new AdImageAdapter(view);
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(
               PreviewAd.this, LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
