package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class BreakTime extends AppCompatActivity {


    NumberPicker breakNumberPicker;
    //Saved variables
    SharedPreferences sharedPreferences;
    int minutes;
    int breakTimeSeconds;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("breakTime")) {
            breakTimeSeconds = sharedPreferences.getInt("breakTime", 20);
        } else {
            sharedPreferences.edit().putInt("breakTime", 240).apply();
        }
        minutes = breakTimeSeconds/60;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_time);
        setUpMemory();
        setupNumberPickers();
    }

    public void pressSetButton(View view){
        breakTimeSeconds = TimeConverters.convertMinutesToSeconds(minutes);
        sharedPreferences.edit().putInt("breakTime",breakTimeSeconds).apply();
        openSettingsActivity();
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(BreakTime.this, Settings.class);
        startActivity(intent);
    }

    public void setupNumberPickers(){
        breakNumberPicker = findViewById(R.id.breakNumberPicker);

        breakNumberPicker.setMinValue(0);
        breakNumberPicker.setMaxValue(59);
        breakNumberPicker.setValue(minutes);
        breakNumberPicker.setWrapSelectorWheel(true);
        breakNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                Log.i("min picker choice", String.valueOf(newVal) );
                minutes = newVal;
            }
        });
    }
}


