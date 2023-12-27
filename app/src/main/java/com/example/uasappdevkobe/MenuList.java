package com.example.uasappdevkobe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuList extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<String> name, date, time, ml;
    DBHelper db;
    MenuListAdapter menuListAdapter;
    Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulist);
        buttonback = findViewById(R.id.buttonback);
        db = new DBHelper(this);
        name = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        ml = new ArrayList<>();
        rv = findViewById(R.id.rvMenuList);
        menuListAdapter = new MenuListAdapter(this, name, date, time, ml);
        rv.setAdapter(menuListAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        displayWaterRecord();

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuList.this, Menu.class);
                startActivity(intent);
            }
        });
    }

    private void displayWaterRecord() {
        Cursor cursor = db.getWaterRecord();
        if (cursor.getCount()==0) {
            Toast.makeText(MenuList.this,"No progress regarding water intake data. Please input the data first.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while(cursor.moveToNext()) {
                name.add(cursor.getString(1));
                date.add(cursor.getString(2));
                time.add(cursor.getString(3));
                ml.add(cursor.getString(4));
            }
        }
    }

}