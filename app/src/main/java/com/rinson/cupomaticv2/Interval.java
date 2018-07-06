package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.NumberPicker.OnValueChangeListener;

public class Interval extends AppCompatActivity {

    Button set;
    NumberPicker minutesNumberPicker, secondsNumberPicker;
    TextView minutesNumberPickerText, secondsNumberPickerText;
    int intervalTotalTimeInSeconds;
    int minutes;
    int seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadUserSettings();

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
        openSettingsActivity();
    }

    public void loadUserSettings(){

//        minutes =
//        seconds =
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Interval.this, Settings.class);
        startActivity(intent);
    }
}

