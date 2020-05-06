package com.example.codemail.ui.myad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;
import com.example.codemail.ui.home.CategoryViewHolder;
import com.example.codemail.ui.home.CategoryViewHolder1;
import com.example.codemail.ui.home.LiveBooks;
import com.example.codemail.ui.home.LiveBooks1;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MyadFragment extends Fragment {

    RecyclerView recyclerView3;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<AdImage> options;
    FirebaseRecyclerAdapter<AdImage,AdAdapter> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myad, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String User = user.getUid();

        recyclerView3 = (RecyclerView)root.findViewById(R.id.recycler_view3);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(User).child("SellBooks");
        options = new FirebaseRecyclerOptions.Builder<AdImage>()
                .setQuery(databaseReference,AdImage.class).build();
        adapter = new FirebaseRecyclerAdapter<AdImage, AdAdapter>(options) {
            @Override
            protected void onBindViewHolder(AdAdapter adAdapter, int i, AdImage adImage) {

                Picasso.get().load(adImage.getimagelink()).fit().centerCrop().into(adAdapter.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),"Image Load Failed",Toast.LENGTH_LONG).show();
                    }
                });
                adAdapter.t2.setText(adImage.getbookname());
                adAdapter.t3.setText(adImage.getprice());

            }

            @NonNull
            @Override
            public AdAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myad_item,parent,false);

                return new AdAdapter(view);
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL,false
        );
        recyclerView3.setLayoutManager(layoutManager);
        adapter.startListening();
        recyclerView3.setAdapter(adapter);


        return root;
    }
}