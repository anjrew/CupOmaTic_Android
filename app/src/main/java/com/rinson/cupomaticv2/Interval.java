package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Interval extends AppCompatActivity {

    Button set;
    NumberPicker minutesNumberPicker, secondsNumberPicker;
    TextView minutesNumberPickerText, secondsNumberPickerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        set = (Button)findViewById(R.id.intervalSetButton);
        minutesNumberPicker = findViewById(R.id.minutesNumberPicker);
        secondsNumberPicker = findViewById(R.id.secondsNumberpicker);

    }

    public void pressSetButton(View view){
        openSettingsActivity();
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Interval.this, Settings.class);
        startActivity(intent);
    }
}

