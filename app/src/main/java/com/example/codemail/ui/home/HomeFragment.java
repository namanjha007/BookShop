package com.example.codemail.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.BuyActivity;
import com.example.codemail.R;
import com.example.codemail.sellbooks.SellActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {

    Button buut1, buut2;
    RecyclerView recyclerView, recyclerView1;
    DatabaseReference databaseReference, databaseReference1;
    FirebaseRecyclerOptions<LiveBooks> options;
    FirebaseRecyclerAdapter<LiveBooks,CategoryViewHolder> adapter;
    FirebaseRecyclerOptions<LiveBooks1> options1;
    FirebaseRecyclerAdapter<LiveBooks1,CategoryViewHolder1> adapter1;

    ViewFlipper vFlipper , vFlipper2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        buut1 = (Button)root.findViewById(R.id.buut1);
        buut2 = (Button)root.findViewById(R.id.buut2);

        buut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(getContext(), SellActivity.class);
                startActivity(int2);
            }
        });

        buut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(getContext(), BuyActivity.class);
                startActivity(int1);
            }
        });

        Integer[] a = {R.drawable.book3, R.drawable.book2, R.drawable.book1, R.drawable.book};

        vFlipper = (ViewFlipper)root.findViewById(R.id.vFlipper);
        vFlipper2 = (ViewFlipper)root.findViewById(R.id.vFlipper2);

        for(int i=0;i<4;i++)
        {
            flipperImages(a[i]);
        }

        recyclerView = (RecyclerView)root.findViewById(R.id.recycler_view2);
        recyclerView1 = (RecyclerView)root.findViewById(R.id.recycler_view1);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Live_School");
        options = new FirebaseRecyclerOptions.Builder<LiveBooks>()
                .setQuery(databaseReference,LiveBooks.class).build();
        adapter = new FirebaseRecyclerAdapter<LiveBooks, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i, LiveBooks liveBooks) {

                Picasso.get().load(liveBooks.geturl()).fit().centerCrop().into(categoryViewHolder.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),"Image Load Failed",Toast.LENGTH_LONG).show();
                    }
                });
                categoryViewHolder.t1.setText(liveBooks.getname());

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

                return new CategoryViewHolder(view);
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);




        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Upcoming_School");
        options1 = new FirebaseRecyclerOptions.Builder<LiveBooks1>()
                .setQuery(databaseReference1,LiveBooks1.class).build();
        adapter1 = new FirebaseRecyclerAdapter<LiveBooks1, CategoryViewHolder1>(options1) {
            @Override
            protected void onBindViewHolder(CategoryViewHolder1 categoryViewHolder1, int i, LiveBooks1 liveBooks1) {

                Picasso.get().load(liveBooks1.geturl()).into(categoryViewHolder1.i2, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),"Image Load Failed",Toast.LENGTH_LONG).show();
                    }
                });
                categoryViewHolder1.t2.setText(liveBooks1.getname());

            }

            @NonNull
            @Override
            public CategoryViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_itemm,parent,false);

                return new CategoryViewHolder1(view);
            }
        };

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView1.setLayoutManager(layoutManager2);
        adapter1.startListening();
        recyclerView1.setAdapter(adapter1);




        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(getContext());
        Picasso.get().load(image).into(imageView);

        vFlipper.addView(imageView);
        vFlipper.setFlipInterval(4000);
        vFlipper2.setFlipInterval(3999);
        vFlipper.setAutoStart(true);
        vFlipper2.setAutoStart(true);

        vFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        vFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);

        vFlipper2.setInAnimation(getContext(), android.R.anim.slide_in_left);
        vFlipper2.setOutAnimation(getContext(), android.R.anim.slide_out_right);
        return;
    }
}