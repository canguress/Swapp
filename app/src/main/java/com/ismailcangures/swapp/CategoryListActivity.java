package com.ismailcangures.swapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {

    ListView lsvCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        lsvCategoryList = findViewById(R.id.lsvCategoryList);
        ArrayList<Category> arrayList = new ArrayList<>();

        arrayList.add(new Category(R.drawable.img_housesandflats,"37.200 stuff is waiting to discovered.","Houses and Flats"));
        arrayList.add(new Category(R.drawable.img_carsandbikes,"42.730 stuff is waiting to discovered.","Cars and Bikes"));
        arrayList.add(new Category(R.drawable.img_landsandfields,"11.220 stuff is waiting to discovered.","Lands and Fields"));
        arrayList.add(new Category(R.drawable.img_electronics,"113.722 stuff is waiting to discovered.","Electronics "));
        arrayList.add(new Category(R.drawable.img_automotive_parts,"411.742 stuff is waiting to discovered.","Automotive Parts"));
        arrayList.add(new Category(R.drawable.img_sportequipments,"972.110 stuff is waiting to discovered.","Sport Equipments"));
        arrayList.add(new Category(R.drawable.img_toysgames,"8.972.114 stuff is waiting to discovered.","Toys and Games"));
        arrayList.add(new Category(R.drawable.img_bookscomicsmagazines,"145.117 stuff is waiting to discovered.","Books & Manga & Comics"));
        arrayList.add(new Category(R.drawable.img_arts,"1.742 stuff is waiting to discovered.","Arts"));
        arrayList.add(new Category(R.drawable.img_homekitchen,"415.964 stuff is waiting to discovered.","Home & Kitchen Decor "));
        arrayList.add(new Category(R.drawable.img_gardenoutdoor,"22.117 stuff is waiting to discovered.","Garden & Outdoor"));
        arrayList.add(new Category(R.drawable.img_officesupplies,"145.972 stuff is waiting to discovered.","Office Supplies"));
        arrayList.add(new Category(R.drawable.img_clothing,"97.117 stuff is waiting to discovered.","Clothing & Shoes"));
        arrayList.add(new Category(R.drawable.img_beautypersonalcare,"75.842 stuff is waiting to discovered.","Beauty & Personal Care"));
        arrayList.add(new Category(R.drawable.img_cleaningsupplies,"31.002 stuff is waiting to discovered.","Cleaning Supplies"));
        arrayList.add(new Category(R.drawable.img_petsupplies,"71.118 stuff is waiting to discovered.","Pet Supplies"));
















        CategoryAdapter categoryAdapter = new CategoryAdapter(this,0,arrayList);
        lsvCategoryList.setAdapter(categoryAdapter);


    }
}