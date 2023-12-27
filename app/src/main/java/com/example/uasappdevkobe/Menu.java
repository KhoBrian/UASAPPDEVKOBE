package com.example.uasappdevkobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button buttonseeprogress, buttonglass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonglass = findViewById(R.id.buttonglass);
        buttonseeprogress = findViewById(R.id.buttonseeprogress);

        buttonseeprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MenuList.class);
                startActivity(intent);
            }
        });

        buttonglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ActivityGlass.class);
                startActivity(intent);
            }
        });


    }
}

