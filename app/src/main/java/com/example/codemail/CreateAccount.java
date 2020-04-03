package com.example.codemail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    private Spinner spinner;
    private EditText editText;

    EditText name, emailId, password;
    Button btnSignup, tvSignIn;
    FirebaseAuth mFirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editText= findViewById(R.id.edt6);

        mFirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.edt1);
        emailId = findViewById(R.id.edt2);
        password = findViewById(R.id.edt3);
        tvSignIn = findViewById(R.id.tvSignin);
        btnSignup = findViewById(R.id.signupbt);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString();
                final String pwd = password.getText().toString();
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number = editText.getText().toString().trim();

                if(number.isEmpty()||number.length()<10)
                {
                    editText.setError("Valid Number is Required");
                    editText.requestFocus();
                    return;
                }

                final String phonenumber = "+" + code + number;

                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(CreateAccount.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    Intent inte=new Intent(CreateAccount.this, OtpRegister.class);
                    inte.putExtra("phonenumber",phonenumber);
                    inte.putExtra("pwd",pwd);
                    inte.putExtra("email",email);
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
}
