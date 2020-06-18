package com.example.codemail;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.stationary_model.CategoryModel;
import com.example.codemail.stationary_model.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class StationaryActivity extends AppCompatActivity {

    RecyclerView recyclerView1;
    DatabaseReference databaseReference, databaseReference1;
    FirebaseRecyclerOptions<CategoryModel> options;
    FirebaseRecyclerAdapter<CategoryModel, CategoryViewHolder> adapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_stationary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Stationary");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));

        progressDialog = new ProgressDialog(StationaryActivity.this);
        progressDialog.setMessage("Loading Stationary...");
        progressDialog.show();

        recyclerView1 = (RecyclerView)findViewById(R.id.recyView_category);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Stationary").child("Category");
        options = new FirebaseRecyclerOptions.Builder<CategoryModel>()
                .setQuery(databaseReference,CategoryModel.class).build();
        adapter = new FirebaseRecyclerAdapter<CategoryModel, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i, CategoryModel liveBooks) {

                Picasso.get().load(liveBooks.getimglink()).fit().centerCrop().into(categoryViewHolder.i1, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(StationaryActivity.this,"Image Load Failed",Toast.LENGTH_LONG).show();
                    }
                });
                categoryViewHolder.t1.setText(liveBooks.getimgname());

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_stationary_category,parent,false);

                return new CategoryViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView1.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
                adapter.startListening();
    }

    @Override
    protected void onStop() {
        if(adapter!=null)
                adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }

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
