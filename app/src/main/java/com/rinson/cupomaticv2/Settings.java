package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

public class Settings extends AppCompatActivity {


    int intervalTotalTimeInSeconds;
    int breaktime;
    int sampleTimeSeconds;
    int roundoneTimeSeconds;
    int roundTwoTimeSeconds;
    int roundThreeTimeSeconds;

    SharedPreferences sharedPreferences;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);


        if (sharedPreferences.contains("intervalTimeInSeconds")) {
            intervalTotalTimeInSeconds = sharedPreferences.getInt("intervalTimeInSeconds", 20);
        } else {
            sharedPreferences.edit().putInt("intervalTimeInSeconds", 20).apply();
        }
        if (sharedPreferences.contains("breakTime")) {
            breaktime = sharedPreferences.getInt("breakTime", 20);
        } else {
            sharedPreferences.edit().putInt("breakTime", 240).apply();
        }
        if (sharedPreferences.contains("sampleTime")) {
            sampleTimeSeconds = sharedPreferences.getInt("sampleTime", 20);
        } else {
            sharedPreferences.edit().putInt("sampleTime", 600).apply();
        }
        if (sharedPreferences.contains("roundOneTime")) {
            roundoneTimeSeconds = sharedPreferences.getInt("roundOneTime", 20);
        } else {
            sharedPreferences.edit().putInt("roundOneTime", 840).apply();
        }
        if (sharedPreferences.contains("roundTwoTime")) {
            roundTwoTimeSeconds = sharedPreferences.getInt("roundTwoTime", 20);
        } else {
            sharedPreferences.edit().putInt("roundTwoTime", 1080).apply();
        }
        if (sharedPreferences.contains("roundThreeTime")) {
            roundThreeTimeSeconds = sharedPreferences.getInt("roundThreeTime", 20);
        } else {
            sharedPreferences.edit().putInt("roundThreeTime", 1320).apply();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUpMemory();
        createListView();
    }


    public void saveButtonClick(View view) {

        openMainActivity();

    }


    public void openMainActivity() {

        Intent intent = new Intent(Settings.this, MainActivity.class);

        startActivity(intent);
    }

    public void createListView() {

        ListView settingsListView = (findViewById(R.id.settingListView));
        final String[] settingsNames = {"Advanced Mode", "Interval", "Break", "Sample", "Round One", "Round Two", "Round Three", "Vibrate"};
        final String[] settingsValues =
                {"Mode1",
                TimeConverters.convertIntSecStringsmmss(intervalTotalTimeInSeconds),
                TimeConverters.convertIntSecStringsmmss(breaktime),
                TimeConverters.convertIntSecStringsmmss(sampleTimeSeconds),
                TimeConverters.convertIntSecStringsmmss(roundoneTimeSeconds),
                TimeConverters.convertIntSecStringsmmss(roundTwoTimeSeconds),
                TimeConverters.convertIntSecStringsmmss(roundThreeTimeSeconds),
                "Vibrate1"};

        CustomListAdapter customListAdapter = new CustomListAdapter(this, settingsValues, settingsNames);

        settingsListView.setAdapter(customListAdapter);
        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Selected", settingsNames[position]);
                selectOpenActivity(position);
            }
        });
    }

    public void openIntervalActivity(){

        Intent intent = new Intent(Settings.this, Interval.class);

        startActivity(intent);
    }

    public void openBreakActivity() {

        Intent intent = new Intent(Settings.this, BreakTime.class);

        startActivity(intent);
    }

    public void openSampleActivity() {

        Intent intent = new Intent(Settings.this, Sample.class);

        startActivity(intent);
    }
    public void openRoundOneActivity() {

        Intent intent = new Intent(Settings.this, Round_one.class);

        startActivity(intent);
    }

    public void openRoundTwoActivity() {

        Intent intent = new Intent(Settings.this, Round_two.class);

        startActivity(intent);
    }

    public void openRoundThreeActivity() {

        Intent intent = new Intent(Settings.this, Round_three.class);

        startActivity(intent);
    }

    public void selectOpenActivity(int arrayNumber){

        switch (arrayNumber){
            case 1: openIntervalActivity();
                break;
            case 2: openBreakActivity();
                break;
            case 3: openSampleActivity();
                break;
            case 4: openRoundOneActivity();
                break;
            case 5: openRoundTwoActivity();
                break;
            case 6: openRoundThreeActivity();
                break;
        }


    }
}
