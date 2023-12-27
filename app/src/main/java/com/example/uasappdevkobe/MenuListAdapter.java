package com.example.uasappdevkobe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuListAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList name, date, time, ml;

    public MenuListAdapter(Context context, ArrayList name, ArrayList date, ArrayList time, ArrayList ml) {
        this.context = context;
        this.name = name;
        this.date = date;
        this.time = time;
        this.ml = ml;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_glass, parent, false);
        return new MenuListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MenuListHolder menuListHolder = (MenuListHolder) holder;
        menuListHolder.name.setText(String.valueOf(name.get(position)));
        menuListHolder.date.setText(String.valueOf(date.get(position)));
        menuListHolder.time.setText(String.valueOf(time.get(position)));
        menuListHolder.ml.setText((String.valueOf(ml.get(position))));
    }

    @Override
    public int getItemCount() {
        return ml.size();
    }

    public class MenuListHolder extends RecyclerView.ViewHolder {
        TextView name, date, time, ml;

        public MenuListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            date = itemView.findViewById(R.id.tvDate);
            time = itemView.findViewById(R.id.tvTime);
            ml = itemView.findViewById(R.id.tvML);
        }
    }
}