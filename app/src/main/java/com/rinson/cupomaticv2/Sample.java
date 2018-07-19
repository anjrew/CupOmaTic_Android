package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class Sample extends AppCompatActivity {


    NumberPicker sampleNumberPicker;
    //Saved variables
    SharedPreferences sharedPreferences;
    int minutes;
    int sampleTimeSeconds;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("sampleTime")) {
            sampleTimeSeconds = sharedPreferences.getInt("sampleTime", 20);
        } else {
            sharedPreferences.edit().putInt("sampleTime", 600).apply();
        }
        minutes = sampleTimeSeconds/60;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        setUpMemory();
        setupNumberPickers();
    }

    public void pressSetButton(View view){

        sampleTimeSeconds = TimeConverters.convertMinutesToSeconds(minutes);
        sharedPreferences.edit().putInt("sampleTime",sampleTimeSeconds).apply();
        openSettingsActivity();
    }

    public void setupNumberPickers(){
        sampleNumberPicker = findViewById(R.id.breakNumberPicker);

        sampleNumberPicker.setMinValue(0);
        sampleNumberPicker.setMaxValue(59);
        sampleNumberPicker.setValue(minutes);
        sampleNumberPicker.setWrapSelectorWheel(true);
        sampleNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                Log.i("min picker choice", String.valueOf(newVal) );
                minutes = newVal;
            }
        });
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Sample.this, Settings.class);
        startActivity(intent);
    }
}
