package com.example.week06;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        String[] fruits = getResources().getStringArray(R.array.fruits);
        List<CustomItem> items = new ArrayList<>();
        items.add(new CustomItem(R.drawable.apple, fruits[0]));
        items.add(new CustomItem(R.drawable.pear, fruits[1]));
        items.add(new CustomItem(R.drawable.banana, fruits[2]));
        items.add(new CustomItem(R.drawable.strawberry, fruits[3]));
        items.add(new CustomItem(R.drawable.pineapple, fruits[4]));
        items.add(new CustomItem(R.drawable.mango, fruits[5]));
        items.add(new CustomItem(R.drawable.cherry, fruits[6]));
        items.add(new CustomItem(R.drawable.persimmon, fruits[7]));
        items.add(new CustomItem(R.drawable.melon, fruits[8]));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(RecyclerViewActivity.this, items);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }
}