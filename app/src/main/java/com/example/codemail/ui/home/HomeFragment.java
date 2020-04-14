package com.example.codemail.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;

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




        int images[] = {R.drawable.book3, R.drawable.book2, R.drawable.book1, R.drawable.book};
        vFlipper = (ViewFlipper)root.findViewById(R.id.vFlipper);
        vFlipper2 = (ViewFlipper)root.findViewById(R.id.vFlipper2);
        for(int i=0;i<images.length;i++)
        {
            flipperImages(images[i]);
        }

        return root;
    }
    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        vFlipper.addView(imageView);
        vFlipper.setFlipInterval(4000);
        vFlipper2.setFlipInterval(3999);
        vFlipper.setAutoStart(true);
        vFlipper2.setAutoStart(true);

        vFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        vFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);

        vFlipper2.setInAnimation(getContext(), android.R.anim.slide_in_left);
        vFlipper2.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }
}