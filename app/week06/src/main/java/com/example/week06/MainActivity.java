package com.example.week06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> items = new ArrayList<>();
        items.add("CustomListView");
        items.add("RecyclerView");
        items.add("ExpandableListView");
        items.add("Spinner");
        items.add("SustomSpinner");
        items.add("GridView");
        items.add("Gallery");
        items.add("ListActivity");

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleItemClick(position);
            }
        });

    }

    private void handleItemClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, CustomListViewActivity.class);
                break;
            case 1:
                intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}