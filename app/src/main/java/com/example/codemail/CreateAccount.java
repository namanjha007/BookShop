package com.example.codemail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {
    public String number, nam;
    private Spinner spinner;
    private EditText editText;

    EditText name, emailId, password;
    Button btnSignup, tvSignIn;
    FirebaseAuth mFirebaseAuth, mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        mFirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.edt1);
        emailId = findViewById(R.id.edt2);
        tvSignIn = findViewById(R.id.tvSignin);
        btnSignup = findViewById(R.id.signupbt);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString();
                final String phonenumber= getIntent().getStringExtra("numberUser");
                final String nam = name.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(nam.isEmpty()){
                    name.setError("Please enter your password");
                    name.requestFocus();
                }
                else  if(email.isEmpty() && nam.isEmpty()){
                    Toast.makeText(CreateAccount.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && nam.isEmpty())){
                    DatabaseReference dbb = FirebaseDatabase.getInstance().getReference().child("Phone");
                    dbb.child(phonenumber).setValue(phonenumber);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String User = user.getUid();
                    final DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("User");
                    DatabaseReference childreff = reff.child(User);
                    DatabaseReference chichildreff = childreff.child("Personal");
                    DatabaseReference chichichildreff = chichildreff.child("Name");
                    chichichildreff.setValue(nam);
                    DatabaseReference chichichildreff1 = chichildreff.child("Phone");
                    chichichildreff1.setValue(phonenumber);
                    DatabaseReference chichichildreff2 = chichildreff.child("Email");
                    chichichildreff2.setValue(email);
                    Intent inte=new Intent(CreateAccount.this, MainActivity.class);
                    startActivity(inte);
                }
                else{
                    Toast.makeText(CreateAccount.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }

        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAccount.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // you may be using a signIn with phone number like this, now here you can save the phone number in your database
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("phone");

                    ref.child(number).setValue(number);

                }
                else if(task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(CreateAccount.this, "OTP is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
