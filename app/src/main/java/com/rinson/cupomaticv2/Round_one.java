package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class Round_one extends AppCompatActivity {

    NumberPicker roundOneNumberPicker;
    //Saved variables
    SharedPreferences sharedPreferences;
    int minutes;
    int roundoneTimeSeconds;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("roundOneTime")) {
            roundoneTimeSeconds = sharedPreferences.getInt("roundOneTime", 20);
        } else {
            sharedPreferences.edit().putInt("roundOneTime", 840).apply();
        }
        minutes = roundoneTimeSeconds/60;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_one);
        setUpMemory();
        setupNumberPickers();

    }

    public void pressSetButton(View view){
        roundoneTimeSeconds = TimeConverters.convertMinutesToSeconds(minutes);
        sharedPreferences.edit().putInt("roundOneTime",roundoneTimeSeconds).apply();
        openSettingsActivity();
    }

    public void setupNumberPickers(){
        roundOneNumberPicker = findViewById(R.id.breakNumberPicker);

        roundOneNumberPicker.setMinValue(0);
        roundOneNumberPicker.setMaxValue(59);
        roundOneNumberPicker.setValue(minutes);
        roundOneNumberPicker.setWrapSelectorWheel(true);
        roundOneNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                Log.i("min picker choice", String.valueOf(newVal) );
                minutes = newVal;
            }
        });
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Round_one.this, Settings.class);
        startActivity(intent);
    }
}
