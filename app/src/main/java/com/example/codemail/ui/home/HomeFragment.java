package com.example.codemail.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    RecyclerView recyclerView, recyclerView2;

    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;

    ArrayList<MainModel2> mainModel2s;
    MainAdapter2 mainAdapter2;

    ViewFlipper vFlipper , vFlipper2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView)root.findViewById(R.id.recycler_view2);

        Integer[] schoollogo = {R.drawable.loyola_logo, R.drawable.hilltop_logo, R.drawable.lfs_logo,
                R.drawable.vbcv_logo, R.drawable.dav_logo, R.drawable.dbms_logo, R.drawable.ghs_logo,
                R.drawable.rv_logo, R.drawable.vvs_logo, R.drawable.ksms_logo};

        String[] schoolname = {"Loyola","HTS","LFS","VBCV","DAV","DBMS","GHS","RV","Valley View","KSMS"};

        mainModels = new ArrayList<>();
        for(int i=0;i<schoollogo.length;i++)
        {
            MainModel model = new MainModel(schoollogo[i], schoolname[i]);
            mainModels.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mainAdapter = new MainAdapter(getContext(),mainModels);
        recyclerView.setAdapter(mainAdapter);




        recyclerView2 = (RecyclerView)root.findViewById(R.id.recycler_view1);

        Integer[] schoollogo2 = {R.drawable.ksms_logo, R.drawable.dav_logo, R.drawable.loyola_logo,
                R.drawable.dbms_logo, R.drawable.rv_logo, R.drawable.hilltop_logo, R.drawable.ghs_logo,
                R.drawable.lfs_logo, R.drawable.vvs_logo, R.drawable.vbcv_logo};

        String[] schoolname2 = {"KSMS","DAV","Loyola","DBMS","RV","HTS","GHS","LFS","Valley View","VBCV"};

        mainModel2s = new ArrayList<>();
        for(int j=0;j<schoollogo2.length;j++)
        {
            MainModel2 model2 = new MainModel2(schoollogo2[j], schoolname2[j]);
            mainModel2s.add(model2);
        }

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        mainAdapter2 = new MainAdapter2(getContext(),mainModel2s);
        recyclerView2.setAdapter(mainAdapter2);

        vFlipper = (ViewFlipper)root.findViewById(R.id.vFlipper);
        vFlipper2 = (ViewFlipper)root.findViewById(R.id.vFlipper2);

//        String[] a = {"https://firebasestorage.googleapis.com/v0/b/codemail-88765.appspot.com/o/book3.png?alt=media&token=2b1a85dc-48a9-4857-bca5-c81b3f831934",
//                "https://firebasestorage.googleapis.com/v0/b/codemail-88765.appspot.com/o/book2.jpg?alt=media&token=474eeea6-6472-4432-b044-370dcc0d68f4",
//        "https://firebasestorage.googleapis.com/v0/b/codemail-88765.appspot.com/o/book1.jpg?alt=media&token=16a8bbc6-24f8-4919-a479-19d82c6d4557",
//        "https://firebasestorage.googleapis.com/v0/b/codemail-88765.appspot.com/o/book.jpg?alt=media&token=7aab5efb-1d94-4629-b17d-7804907a3552"};

        Integer[] a = {R.drawable.book3, R.drawable.book2, R.drawable.book1, R.drawable.book};

        for(int i=0;i<4;i++)
        {
            flipperImages(a[i]);
        }


        return root;
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