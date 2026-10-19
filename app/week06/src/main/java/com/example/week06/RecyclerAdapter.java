package com.example.week06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Context context;
    private List<CustomItem> items;

    public RecyclerAdapter(Context context, List<CustomItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleritem, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.textView.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}