package com.example.codemail.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.codemail.R;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {

    Button buut1, buut2;
    ViewFlipper vFlipper, vFlipper2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        buut1 = (Button)root.findViewById(R.id.buut1);
        buut2 = (Button)root.findViewById(R.id.buut2);

        buut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Integer[] a = {R.drawable.book3, R.drawable.book2, R.drawable.book1, R.drawable.book};

        vFlipper = (ViewFlipper)root.findViewById(R.id.vFlipper);
        vFlipper2 = (ViewFlipper)root.findViewById(R.id.vFlipper2);

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