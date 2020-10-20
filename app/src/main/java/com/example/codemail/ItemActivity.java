package com.example.codemail;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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

import com.example.codemail.item_model.ItemModel;
import com.example.codemail.item_model.ItemViewHolder;
import com.example.codemail.stationary_model.CategoryModel;
import com.example.codemail.stationary_model.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ItemActivity extends AppCompatActivity {

    RecyclerView recyclerView1;
    DatabaseReference databaseReference, databaseReference1;
    FirebaseRecyclerOptions<ItemModel> options;
    FirebaseRecyclerAdapter<ItemModel, ItemViewHolder> adapter;
    ProgressDialog progressDialog;
    public View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Items");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));

        progressDialog = new ProgressDialog(ItemActivity.this);
        progressDialog.setMessage("Loading Items...");
        progressDialog.show();

        recyclerView1 = (RecyclerView)findViewById(R.id.recyView_item);
        String keyCat = getIntent().getStringExtra("keyCat");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Stationary").child("Item").child(keyCat);
        options = new FirebaseRecyclerOptions.Builder<ItemModel>()
                .setQuery(databaseReference,ItemModel.class).build();
        adapter = new FirebaseRecyclerAdapter<ItemModel, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ItemViewHolder categoryViewHolder, int i, ItemModel liveBooks) {

                Picasso.get().load(liveBooks.getimglink()).fit().centerCrop().into(categoryViewHolder.i1, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(ItemActivity.this,"Image Load Failed",Toast.LENGTH_LONG).show();
                    }
                });
                categoryViewHolder.t1.setText(liveBooks.getBrand().toString()+" "+liveBooks.getitemname().toString()+" "+
                        liveBooks.getcolour().toString()+" "+liveBooks.gettype().toString());
                String cp = "\u20B9"+liveBooks.getcostprice().toString();
                String sp = "\u20B9" + liveBooks.getsellprice().toString();
                categoryViewHolder.t2.setText(sp);
                categoryViewHolder.t3.setText(cp);
                Log.e("1", "onBindViewHolder: "+i );

                final String post = getRef(i).getKey();
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_stationary_item,parent,false);
                return new ItemViewHolder(view);
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
