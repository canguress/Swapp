package com.ismailcangures.swapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.ismailcangures.swapp.R;

public class AddNewProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        Toast.makeText(AddNewProductActivity.this,"Welcome to the product adding screen",Toast.LENGTH_SHORT).show();

    }
}