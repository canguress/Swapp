package com.ismailcangures.swapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismailcangures.swapp.R;
import com.ismailcangures.swapp.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    Button btnSignUp, btnLogin;
    EditText  etPhoneNumber, etPassword;

    private String dbName = "Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        //Login Sessions
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();
            }
        });
    }

    private void LoginUser() {

        String phoneNumber = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();
         if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "Phone number can not be empty!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password can not be empty!", Toast.LENGTH_SHORT).show();
        }

         else {
             if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
                 Toast.makeText(this, "Your phone number is not valid.", Toast.LENGTH_SHORT).show();
             } else if (password.length() < 8) {
                 Toast.makeText(this, "Your password should be include 8 characters minimum..", Toast.LENGTH_SHORT).show();
             }

             else
             {
                 allowAccessUserToSignIn(phoneNumber,password);
             }
         }

    }

    private void allowAccessUserToSignIn(String phoneNumber, String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(dbName).child(phoneNumber).exists())
                {
                    UserModel userData = snapshot.child(dbName).child(phoneNumber).getValue(UserModel.class);
                    if(userData.getPhone().equals(phoneNumber))
                    {
                        if(userData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this,"Successfully logged in!",Toast.LENGTH_SHORT).show();
                            Intent i2 = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(i2);

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Incorrect password!",Toast.LENGTH_SHORT).show();

                        }
                    }

                    else
                    {
                        Toast.makeText(LoginActivity.this,"Incorrect phone number!",Toast.LENGTH_SHORT).show();

                    }
                }

                else
                {
                    Toast.makeText(LoginActivity.this,
                            "There is no account found by the phone number.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}