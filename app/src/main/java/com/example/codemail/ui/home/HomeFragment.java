package com.example.codemail.ui.home;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codemail.R;
import com.example.codemail.StationaryActivity;
import com.example.codemail.stationary_model.CategoryModel;
import com.example.codemail.stationary_model.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {

    DatabaseReference databaseReference1;
    Button buut1, buut2, buut3, buut4;
    ViewFlipper vFlipper, vFlipper2;
    ImageButton img_btn1, img_btn2, img_btn3, img_btn4, img_btn5;
    ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        buut1 = (Button)root.findViewById(R.id.buut1);
        buut2 = (Button)root.findViewById(R.id.buut2);
        buut3 = (Button)root.findViewById(R.id.buut3);
        buut4 = (Button)root.findViewById(R.id.buut4);
        img_btn1 = (ImageButton)root.findViewById(R.id.img_btn1);
        img_btn2 = (ImageButton)root.findViewById(R.id.img_btn2);
        img_btn3 = (ImageButton)root.findViewById(R.id.img_btn3);
        img_btn4 = (ImageButton)root.findViewById(R.id.img_btn4);
        img_btn5 = (ImageButton)root.findViewById(R.id.img_btn5);
        final String but_message1 = "You can buy books before the new academic year";
        final String but_message2 = "You can sell books before the new academic year";

        buut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),but_message1,Toast.LENGTH_LONG).show();
            }
        });

        buut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),but_message2,Toast.LENGTH_LONG).show();
            }
        });

        buut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
            }
        });

        buut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
            }
        });

        Integer[] a = {0, 1, 2, 3};

        vFlipper = (ViewFlipper)root.findViewById(R.id.vFlipper);
        vFlipper2 = (ViewFlipper)root.findViewById(R.id.vFlipper2);

        for(int i=0;i<4;i++)
        {
            flipperImages(a[i]);
        }
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void flipperImages(final int image)
    {
        final ImageView imageView = new ImageView(getContext());
        final TextView textView = new TextView(getContext());
        textView.setTextSize(18);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextAppearance(FontStyle.FONT_SLANT_ITALIC);
        if(image==0) {
            databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Home_Flipper");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String link1 = dataSnapshot.child("Image1").getValue(String.class);
                    Picasso.get().load(link1).fit().into(imageView);
                    String title = dataSnapshot.child("Title1").getValue(String.class);
                    textView.setText(title);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(image==1){
            databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Home_Flipper");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String link1 = dataSnapshot.child("Image2").getValue(String.class);
                    Picasso.get().load(link1).fit().into(imageView);
                    String title = dataSnapshot.child("Title2").getValue(String.class);
                    textView.setText(title);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(image == 2)
        {
            databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Home_Flipper");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String link1 = dataSnapshot.child("Image3").getValue(String.class);
                    Picasso.get().load(link1).fit().into(imageView);
                    String title = dataSnapshot.child("Title3").getValue(String.class);
                    textView.setText(title);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(image==3){
            databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Home_Flipper");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String link1 = dataSnapshot.child("Image4").getValue(String.class);
                    Picasso.get().load(link1).fit().into(imageView);
                    String title = dataSnapshot.child("Title4").getValue(String.class);
                    textView.setText(title);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        vFlipper.addView(imageView);
        vFlipper2.addView(textView);
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

    public void nextPage()
    {
        Intent intent = new Intent(getContext(), StationaryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Stationary").child("Buttons");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link1 = dataSnapshot.child("Image1").getValue(String.class);
                Picasso.get().load(link1).fit().centerCrop().into(img_btn1);

                String link2 = dataSnapshot.child("Image2").getValue(String.class);
                Picasso.get().load(link2).fit().centerCrop().into(img_btn2);

                String link3 = dataSnapshot.child("Image3").getValue(String.class);
                Picasso.get().load(link3).fit().centerCrop().into(img_btn3);

                String link4 = dataSnapshot.child("Image4").getValue(String.class);
                Picasso.get().load(link4).fit().centerCrop().into(img_btn4);

                String link5 = dataSnapshot.child("Image5").getValue(String.class);
                Picasso.get().load(link5).fit().centerCrop().into(img_btn5);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}