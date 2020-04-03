package com.example.codemail.ui.yourorders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.codemail.R;

public class YourordersFragment extends Fragment {

    private YourordersViewModel yourordersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        yourordersViewModel =
                ViewModelProviders.of(this).get(YourordersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_yourorders, container, false);
        final TextView textView = root.findViewById(R.id.text_yourorders);
        yourordersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}