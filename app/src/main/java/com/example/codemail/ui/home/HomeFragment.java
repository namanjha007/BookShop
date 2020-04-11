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

import com.example.codemail.R;

public class HomeFragment extends Fragment {
    ViewFlipper vFlipper , vFlipper2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
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