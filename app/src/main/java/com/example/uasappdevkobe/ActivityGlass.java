package com.example.uasappdevkobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityGlass extends AppCompatActivity {

    EditText editTextNama, editTextTanggal, editTextWaktu;
    Button btnAddGlass, buttonback;
    DBHelper db;
    RadioButton rb1, rb2, rb3;
    RadioGroup glassRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glass);

        editTextWaktu = findViewById(R.id.inputTime);
        editTextNama = findViewById(R.id.inputName);
        editTextTanggal = findViewById(R.id.inputDate);
        btnAddGlass = findViewById(R.id.buttonglass);
        buttonback = findViewById(R.id.buttonback);
        glassRadioGroup = findViewById(R.id.glassRadioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);

        setDateTime();

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGlass.this, Menu.class);
                startActivity(intent);
            }
        });

        btnAddGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBHelper(ActivityGlass.this);
                String name = editTextNama.getText().toString();
                String date = editTextTanggal.getText().toString();
                String time = editTextWaktu.getText().toString();
                String ml = getSelectedGlass();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(date) || TextUtils.isEmpty(time))  {
                    Toast.makeText(ActivityGlass.this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkInsertData = db.addWaterRecord(name, date, time, ml);

                if (checkInsertData == true) {
                    Toast.makeText(ActivityGlass.this, "Water intake data has been saved successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityGlass.this, Menu.class));
                } else {
                    Toast.makeText(ActivityGlass.this, "Failed to save water intake data.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getSelectedGlass() {
        int selectedId = glassRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        return selectedRadioButton.getText().toString();
    }

    private void setDateTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

        editTextTanggal.setText(dateFormatter.format(calendar.getTime()));
        editTextWaktu.setText(timeFormatter.format(calendar.getTime()));
    }
}