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
import android.widget.NumberPicker.OnValueChangeListener;

public class Interval extends AppCompatActivity {

    Button set;
    NumberPicker minutesNumberPicker, secondsNumberPicker;
    int intervalTotalTimeInSeconds;
    int minutes;
    int seconds;

    //Saved variables
    SharedPreferences sharedPreferences;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        ;
        if (sharedPreferences.contains("intervalTimeInSeconds")) {
            intervalTotalTimeInSeconds = sharedPreferences.getInt("intervalTimeInSeconds", 20);
        } else {
            sharedPreferences.edit().putInt("intervalTimeInSeconds", 20).apply();
            convertTotalTimeIntSecondsToMinutesAndSeconds(intervalTotalTimeInSeconds);
        }
        convertTotalTimeIntSecondsToMinutesAndSeconds(intervalTotalTimeInSeconds);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpMemory();

        setContentView(R.layout.activity_interval);
        set = findViewById(R.id.intervalSetButton);
        minutesNumberPicker = findViewById(R.id.minutesNumberPicker);
        secondsNumberPicker = findViewById(R.id.secondsNumberpicker);

        minutesNumberPicker.setMinValue(0);
        minutesNumberPicker.setMaxValue(59);
        minutesNumberPicker.setValue(minutes);
        minutesNumberPicker.setWrapSelectorWheel(true);

        secondsNumberPicker.setMinValue(0);
        secondsNumberPicker.setMaxValue(59);
        secondsNumberPicker.setValue(seconds);
        secondsNumberPicker.setWrapSelectorWheel(true);

        minutesNumberPicker.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // TODO Auto-generated method stub

                Log.i("min picker choice", String.valueOf(newVal) );
                minutes = newVal;
            }
        });

        secondsNumberPicker.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // TODO Auto-generated method stub

                Log.i("sec picker choice", String.valueOf(newVal) );
                seconds = newVal;
            }
        });
    }


    public void convertTotalTimeIntSecondsToMinutesAndSeconds(int intervalTotalTimeInSeconds){
        minutes = intervalTotalTimeInSeconds / 60;
        seconds = intervalTotalTimeInSeconds % 60;
    }


    public void saveChoiceInTotalSeconds(){
        intervalTotalTimeInSeconds= (minutes * 60) + seconds;
    }


    public void pressSetButton(View view){
        saveChoiceInTotalSeconds();
        sharedPreferences.edit().putInt("intervalTimeInSeconds",intervalTotalTimeInSeconds).apply();
        openSettingsActivity();
    }


    public void openSettingsActivity() {
        Intent intent = new Intent(Interval.this, Settings.class);
        startActivity(intent);
    }
}

