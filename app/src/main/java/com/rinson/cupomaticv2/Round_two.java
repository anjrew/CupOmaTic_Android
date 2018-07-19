package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class Round_two extends AppCompatActivity {

    NumberPicker roundTwoNumberPicker;
    //Saved variables
    SharedPreferences sharedPreferences;
    int minutes;
    int roundTwoTimeSeconds;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("roundTwoTime")) {
            roundTwoTimeSeconds = sharedPreferences.getInt("roundTwoTime", 20);
        } else {
            sharedPreferences.edit().putInt("roundTwoTime", 840).apply();
        }
        minutes = roundTwoTimeSeconds/60;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_two);
        setUpMemory();
        setupNumberPickers();
    }

    public void pressSetButton(View view){
        roundTwoTimeSeconds = TimeConverters.convertMinutesToSeconds(minutes);
        sharedPreferences.edit().putInt("roundTwoTime",roundTwoTimeSeconds).apply();
        openSettingsActivity();
    }

    public void setupNumberPickers(){
        roundTwoNumberPicker = findViewById(R.id.breakNumberPicker);
        roundTwoNumberPicker.setMinValue(0);
        roundTwoNumberPicker.setMaxValue(59);
        roundTwoNumberPicker.setValue(minutes);
        roundTwoNumberPicker.setWrapSelectorWheel(true);
        roundTwoNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                Log.i("min picker choice", String.valueOf(newVal) );
                minutes = newVal;
            }
        });
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Round_two.this, Settings.class);
        startActivity(intent);
    }
}
