package com.example.codemail.ui.paymentmethods;

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

public class PaymentmethodsFragment extends Fragment {

    private PaymentmethodsViewModel paymentmethodsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        paymentmethodsViewModel =
                ViewModelProviders.of(this).get(PaymentmethodsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_paymentmethods, container, false);
        final TextView textView = root.findViewById(R.id.text_paymentmethods);
        paymentmethodsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}