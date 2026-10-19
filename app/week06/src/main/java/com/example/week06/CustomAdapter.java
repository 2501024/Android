package com.example.week06;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CustomItem> items;


    public CustomAdapter(Context context, ArrayList<CustomItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.customitem, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(items.get(position).getImage());
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(items.get(position).getName());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubActivity.class);
                intent.putExtra("title", "CustomListView 예제 결과");
                intent.putExtra("position", position + 1);
                intent.putExtra("select", items.get(position).getName());
                context.startActivity(intent);
            }
        });

        Button button = convertView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}