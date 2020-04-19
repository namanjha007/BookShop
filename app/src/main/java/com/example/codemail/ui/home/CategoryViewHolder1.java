package com.example.codemail.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;

public class CategoryViewHolder1 extends RecyclerView.ViewHolder {

    public TextView t2;
    public ImageView i2;

    public CategoryViewHolder1(@NonNull View itemView) {
        super(itemView);

        t2 = (TextView)itemView.findViewById(R.id.text_view2);
        i2 = (ImageView)itemView.findViewById(R.id.image_view2);
    }
}
