package com.ismailcangures.swapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Button btnBacktoLogin, btnSignUp;
    EditText etUsername, etPhoneNumber, etPassword, etPasswordConfirmation;
    boolean isOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnBacktoLogin = findViewById(R.id.btnBacktoLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        etUsername = findViewById(R.id.etUsername);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirmation = findViewById(R.id.etPasswordConfirmation);
        FirebaseApp.initializeApp(this);
        btnBacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        String username = etUsername.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPasswordConfirmation.getText().toString();


        //Sign Up Session Tests

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username can not be empty!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "Phone number can not be empty!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password can not be empty!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(passwordConfirm)) {
            Toast.makeText(this, "Password confirmation can not be empty!", Toast.LENGTH_SHORT).show();
        } else {

            if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
                Toast.makeText(this, "Your phone number is not valid.", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 8) {
                Toast.makeText(this, "Your password should be include 8 characters minimum..", Toast.LENGTH_SHORT).show();
            } else if (passwordConfirm.length() < 8) {
                Toast.makeText(this, "Your password confirmation should be include 8 characters minimum..", Toast.LENGTH_SHORT).show();
            } else if (!passwordConfirm.equals(password)) {
                Toast.makeText(this, "Password and confirmation does not match!", Toast.LENGTH_SHORT).show();
            } else {


                  validatePhoneNumber(phoneNumber,username,password);


            }
        }
    }

    //Signin user up to our Firebase Realtime DB

    private void validatePhoneNumber(String phoneNumber, String username, String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.child("Users").child(phoneNumber).exists()) {
                    Toast.makeText(RegisterActivity.this, "Your phone number is already in use. Please login.", Toast.LENGTH_SHORT).show();
                } else if (snapshot.child("Users").child(username).exists()) {
                    Toast.makeText(RegisterActivity.this, "Username is already in use. Please pick another username.", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("phone", phoneNumber);
                    userDataMap.put("username", username);
                    userDataMap.put("password", password);
                    RootRef.child("Users").child(username).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                        Toast.makeText(RegisterActivity.this, "You registered succesfully!", Toast.LENGTH_SHORT).show();


                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Problem been occured while registering. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}