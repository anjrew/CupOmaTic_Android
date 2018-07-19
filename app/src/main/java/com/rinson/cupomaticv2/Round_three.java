package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class Round_three extends AppCompatActivity {

    NumberPicker roundThreeNumberPicker;
    //Saved variables
    SharedPreferences sharedPreferences;
    int minutes;
    int roundThreeTimeSeconds;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("roundThreeTime")) {
            roundThreeTimeSeconds = sharedPreferences.getInt("roundThreeTime", 20);
        } else {
            sharedPreferences.edit().putInt("roundThreeTime", 1320).apply();
        }
        minutes = roundThreeTimeSeconds/60;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_three);
        setUpMemory();
        setupNumberPickers();

    }

    public void pressSetButton(View view){
        roundThreeTimeSeconds = TimeConverters.convertMinutesToSeconds(minutes);
        sharedPreferences.edit().putInt("roundThreeTime",roundThreeTimeSeconds).apply();
        openSettingsActivity();
    }

    public void setupNumberPickers(){
        roundThreeNumberPicker = findViewById(R.id.breakNumberPicker);
        roundThreeNumberPicker.setMinValue(0);
        roundThreeNumberPicker.setMaxValue(59);
        roundThreeNumberPicker.setValue(minutes);
        roundThreeNumberPicker.setWrapSelectorWheel(true);
        roundThreeNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                Log.i("min picker choice", String.valueOf(newVal) );
                minutes = newVal;
            }
        });
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Round_three.this, Settings.class);
        startActivity(intent);
    }
}
