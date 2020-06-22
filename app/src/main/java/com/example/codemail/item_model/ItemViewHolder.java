package com.example.codemail.item_model;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView t1, t2, t3;
    public ImageView i1;

    public ItemViewHolder(View itemView)
    {
        super(itemView);
        t3 = (TextView)itemView.findViewById(R.id.cutprice);
        t3.setPaintFlags(t3.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        t2 = (TextView)itemView.findViewById(R.id.sellprice);
        t1 = (TextView)itemView.findViewById(R.id.item_name);
        i1 = (ImageView)itemView.findViewById(R.id.item_image);
    }

}
