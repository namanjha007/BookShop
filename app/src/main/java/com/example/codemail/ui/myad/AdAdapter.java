package com.example.codemail.ui.myad;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;

public class AdAdapter extends RecyclerView.ViewHolder {

    public TextView t1,t2,t3;
    public ImageView i1;

    public AdAdapter(@NonNull View itemView) {
        super(itemView);

        t1 = (TextView)itemView.findViewById(R.id.period);
        t2 = (TextView) itemView.findViewById(R.id.adbookname);
        t3 = (TextView) itemView.findViewById(R.id.price);
        i1 = (ImageView) itemView.findViewById(R.id.myad);
    }
}
