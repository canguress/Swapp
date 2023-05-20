package com.ismailcangures.swapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismailcangures.swapp.R;
import com.ismailcangures.swapp.databinding.ActivityHomeBinding;
import com.ismailcangures.swapp.fragments.DiscoverFragment;
import com.ismailcangures.swapp.fragments.HomeFragment;
import com.ismailcangures.swapp.fragments.MessagesFragment;
import com.ismailcangures.swapp.fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    FloatingActionButton btnAddProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnAddProduct = findViewById(R.id.btnOpenAddProduct);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item->{

         if(item.getItemId()==R.id.menuHome)
         {
             replaceFragment(new HomeFragment());
         }
         else if(item.getItemId()==R.id.menuDiscover)
         {
             replaceFragment(new DiscoverFragment());
         }
         else if(item.getItemId()==R.id.menuMessages)
         {
             replaceFragment(new MessagesFragment());
         }
         else if(item.getItemId()==R.id.menuProfile)
         {
             replaceFragment(new ProfileFragment());
         }
         return true;

        });


        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AddProductActivity.class);
                startActivity(i);

            }
        });












    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }


}