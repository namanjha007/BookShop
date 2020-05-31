package com.example.codemail.sellbooks;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codemail.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class UploadimageActivity extends AppCompatActivity {

    private static final int PICK_IMG = 1;
    private ArrayList<Uri> ImageList = new ArrayList<Uri>();
    private int uploads = 0;
    private DatabaseReference databaseReference, databaseReference1;
    private ProgressDialog progressDialog;
    int index = 0;
    TextView textView;
    Button choose,send;
    public int i=0,zz=0;
    String User,linky;
    public HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_uploadimage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sell Books");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        User = user.getUid();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading ..........");
        textView = findViewById(R.id.text);
        choose = findViewById(R.id.choose);
        send = findViewById(R.id.upload);
    }

    public void choose(View view) {
        //we will pick images
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, PICK_IMG);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMG) {
            if (resultCode == RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();

                    int CurrentImageSelect = 0;

                    while (CurrentImageSelect < count) {
                        Uri imageuri = data.getClipData().getItemAt(CurrentImageSelect).getUri();
                        ImageList.add(imageuri);
                        CurrentImageSelect = CurrentImageSelect + 1;
                    }
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("You Have Selected "+ ImageList.size() +" Pictures" );
                    send.setVisibility(View.VISIBLE);
                    choose.setVisibility(View.GONE);
                }

            }

        }

    }

    @SuppressLint("SetTextI18n")
    public void upload(View view) {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(User).child("SellBooks").push();
        String c = databaseReference.getKey();

        SendLink2();

        textView.setText("Please Wait ... If Uploading takes Too much time please the button again ");
        progressDialog.show();
        final StorageReference ImageFolder =  FirebaseStorage.getInstance().getReference().child("ImageFolder");
        for (uploads=0; uploads < ImageList.size(); uploads++) {
            Uri Image  = ImageList.get(uploads);
            final String x = "Image "+uploads;
            final StorageReference imagename = ImageFolder.child("image/"+Image.getLastPathSegment());

            imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String url = String.valueOf(uri);

                            if(zz ==0)
                            {
                                databaseReference.child("imagelink").setValue(url);
                            }

                            SendLink(url,x);
                        }
                    });

                }
            });


        }
        Intent intent = new Intent(UploadimageActivity.this, PreviewAd.class);
        intent.putExtra("db",c);
        startActivity(intent);


    }

    private void SendLink(String url, String c) {
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("link",url);
        databaseReference.child("Images").push().setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                textView.setText("Image Uploaded Successfully");
                send.setVisibility(View.GONE);
                ImageList.clear();
            }
        });
    }

    private void SendLink2() {
        String name = getIntent().getStringExtra("name");
        String clas = getIntent().getStringExtra("clas");
        String year = getIntent().getStringExtra("year");
        String subject = getIntent().getStringExtra("subject");
        String price = getIntent().getStringExtra("price");
        hashMap.put("bookname", name);
        hashMap.put("class", clas);
        hashMap.put("year", year);
        hashMap.put("subject", subject);
        hashMap.put("price",price);
        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
}