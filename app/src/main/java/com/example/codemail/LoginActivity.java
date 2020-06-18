package com.example.codemail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;

    public String phonenumber,UID="";
    private Spinner spinner;
    public Intent intent;
    private String verificationid, name;
    private FirebaseAuth mAuth, mAuth2;
    private ProgressBar progressBar;
    private EditText editText,phone;
    LinearLayout linlay;
    RelativeLayout rellay1, rellay2;
    Button pwcbtn;
    TextView tvgo;
    Handler handler=new Handler();
    Runnable runnable=new Runnable(){
        @Override
        public void run()
        {

            rellay1.setVisibility(View.VISIBLE);
            tvgo.setVisibility(View.INVISIBLE);
        }
    };

    Handler handler2=new Handler();
    Runnable runnable2=new Runnable(){
        @Override
        public void run()
        {

            linlay.setVisibility(View.VISIBLE);
            pwcbtn.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
        }


        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        tvgo = (TextView) findViewById(R.id.tvgone);
        linlay = (LinearLayout)findViewById(R.id.linlay4);
        pwcbtn = (Button)findViewById(R.id.btnSigni);

        handler.postDelayed(runnable, 2000);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        editText = findViewById(R.id.editTextCode);

        phone = findViewById(R.id.editTextPhone);

        findViewById(R.id.btnSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = "+91";
                phonenumber = "+"+code+phone.getText().toString();
                if(phonenumber.isEmpty()||phonenumber.length()<10)
                {
                    phone.setError("Valid Number is Required");
                    phone.requestFocus();
                    return;
                }
                else {
                    handler2.postDelayed(runnable2, 500);
                    sendVerificationCode(phonenumber);
                }
//                final DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("User");
//                DatabaseReference childreff = reff.child("User_"+name+"_"+phonenumber);
//                DatabaseReference chichildreff = childreff.child("Personal");
//                DatabaseReference chichichildreff = chichildreff.child("Name");
//                chichichildreff.setValue(name);
//                DatabaseReference chichichildreff1 = chichildreff.child("Phone");
//                chichichildreff1.setValue(phonenumber);

            }
        });

        findViewById(R.id.btnSigni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editText.getText().toString().trim();
                if ((code.isEmpty() || code.length() < 6)) {

                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                } else {
                    verifyCode(code);
                }

            }
        });
    }


    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        final int[] flag = {0};
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Phone");

                            reference.child(phonenumber).addListenerForSingleValueEvent(new ValueEventListener() {
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() != null) {
                                        //username found
                                        Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent2);

                                    }else{
                                        // username not found
                                        Intent intent = new Intent(LoginActivity.this, CreateAccount.class);
                                        intent.putExtra("numberUser",phonenumber);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }

                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                        }

                               else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };
}
