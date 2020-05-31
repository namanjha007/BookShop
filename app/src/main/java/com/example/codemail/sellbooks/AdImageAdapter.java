package com.example.codemail.sellbooks;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;

public class AdImageAdapter extends RecyclerView.ViewHolder {

    public ImageView i1;

    public AdImageAdapter(@NonNull View itemView) {
        super(itemView);

        i1 = (ImageView)itemView.findViewById(R.id.adimage);
    }
}
