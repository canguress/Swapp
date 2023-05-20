package com.ismailcangures.swapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ismailcangures.swapp.models.Category;
import com.ismailcangures.swapp.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Category> arrayList;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = new ArrayList<>(objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_category,null); //????


        }
        if(arrayList.size()>0)
        {
            Category category = arrayList.get(position);
            ImageView imageView = convertView.findViewById(R.id.imgCategory2);
            imageView.setImageResource(category.img);
            TextView tvcategoryName = convertView.findViewById(R.id.tvcategoryName);
            tvcategoryName.setText(category.name);
            TextView tvNumberItem = convertView.findViewById(R.id.tvnumberitems);
            tvNumberItem.setText(category.numItem);


        }
        return convertView;
    }
}
