package com.example.codemail.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView t1;
    public ImageView i1;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        t1 = (TextView)itemView.findViewById(R.id.text_view);
        i1 = (ImageView)itemView.findViewById(R.id.image_view);
    }
}
