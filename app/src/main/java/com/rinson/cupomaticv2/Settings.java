package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    int bowlSetting;
    int intervalTotalTimeInSeconds;
    int breaktime;
    int sampleTimeSeconds;
    int roundoneTimeSeconds;
    int roundTwoTimeSeconds;
    int roundThreeTimeSeconds;
    boolean advancedMode;
    boolean vibrate;
    boolean voicePrompts;
    ToggleButton advancedModeToggle;
    ToggleButton vibrateToggle;
    ToggleButton voicePromptsToggle;
    String[] settingsValues;

    SharedPreferences sharedPreferences;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("vibrate")) {
            vibrate = sharedPreferences.getBoolean("vibrate", false);
        } else {
            sharedPreferences.edit().putBoolean("vibrate", false).apply();
        }

        if (sharedPreferences.contains("bowlSetting")) {
            bowlSetting = sharedPreferences.getInt("bowlSetting", 0);
        } else {
            sharedPreferences.edit().putInt("bowlSetting", 20).apply();
        }

        if (sharedPreferences.contains("advancedMode")) {
            advancedMode = sharedPreferences.getBoolean("advancedMode", false);
        } else {
            sharedPreferences.edit().putBoolean("advancedMode", false).apply();
        }
        //
        if (sharedPreferences.contains("intervalTimeInSeconds")) {
            intervalTotalTimeInSeconds = sharedPreferences.getInt("intervalTimeInSeconds", 20);
        } else {
            sharedPreferences.edit().putInt("intervalTimeInSeconds", 20).apply();
        }
        //
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
        if (sharedPreferences.contains("voicePrompts")) {
            voicePrompts = sharedPreferences.getBoolean("voicePrompts", true);
        } else {
            sharedPreferences.edit().putBoolean("voicePrompts", true).apply();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUpMemory();
        createToggleButtons();
        setupSettingsValues();
        createListView();
    }


    public void saveButtonClick(View view) {
        updateToggleVariables();
        openMainActivity();
        Log.i("Break = ", TimeConverters.convertIntSecStringsmmss(breaktime));
        Log.i("Sample = ", TimeConverters.convertIntSecStringsmmss(sampleTimeSeconds));
        Log.i("Round 1  = ", TimeConverters.convertIntSecStringsmmss(roundoneTimeSeconds));
        Log.i("Round 2 = ", TimeConverters.convertIntSecStringsmmss(roundTwoTimeSeconds));
        Log.i("Round 3 = ", TimeConverters.convertIntSecStringsmmss(roundThreeTimeSeconds));

    }


    public void openMainActivity() {

        Intent intent = new Intent(Settings.this, MainActivity.class);

        startActivity(intent);
    }

    public void createListView() {

        ListView settingsListView = (findViewById(R.id.settingListView));
        final ToggleButton[] toggleButtons = {advancedModeToggle, voicePromptsToggle, vibrateToggle};
        final String[] settingsNames = {"Advanced mode", "Voice prompts", "Vibrate", "Interval", "Break", "Sample", "Round one", "Round two", "Round three"};

        CustomListAdapter customListAdapter = new CustomListAdapter(this, settingsValues, settingsNames, toggleButtons, this);
        settingsListView.setAdapter(customListAdapter);
        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Selected", settingsNames[position]);
                if (position >= toggleButtons.length) {
                    selectOpenActivity(position);
                } else {
                }

            }
        });
    }

    public void openIntervalActivity() {

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

    public void selectOpenActivity(int arrayNumber) {

        switch (arrayNumber) {
            case 3:
                openIntervalActivity();
                break;
            case 4:
                openBreakActivity();
                break;
            case 5:
                openSampleActivity();
                break;
            case 6:
                openRoundOneActivity();
                break;
            case 7:
                openRoundTwoActivity();
                break;
            case 8:
                openRoundThreeActivity();
                break;
        }
    }

    public void createToggleButtons() {
        advancedModeToggle = new ToggleButton(getApplicationContext());
        advancedModeToggle.setChecked(advancedMode);

        vibrateToggle = new ToggleButton(getApplicationContext());
        vibrateToggle.setChecked(vibrate);

        voicePromptsToggle = new ToggleButton(getApplicationContext());
        voicePromptsToggle.setChecked(voicePrompts);

    }

    public void updateToggleVariables() {


        sharedPreferences.edit().putBoolean("voicePrompts", voicePrompts).apply();
        sharedPreferences.edit().putBoolean("vibrate", vibrate).apply();
        sharedPreferences.edit().putBoolean("advancedMode", advancedMode).apply();

        Log.i("Toggles / ", " AdvancedMode = " + String.valueOf(advancedMode) + " / Vibrate = =" + String.valueOf(vibrate) + " / Voice = " + String.valueOf(voicePrompts));

    }

    public void updateAdvancedMode(Boolean state) {
        advancedMode = state;
        sharedPreferences.edit().putBoolean("advancedMode", advancedMode).apply();
        Log.i("Set Advanced Mode = ", String.valueOf(advancedMode));

    }

    public void updateVibrateMode(Boolean state) {

        vibrate = state;
        sharedPreferences.edit().putBoolean("vibrate", vibrate).apply();
        Log.i("Set Vibrate Mode = ", String.valueOf(vibrate));

    }

    public void updateVoiceMode(Boolean state) {

        voicePrompts = state;
        sharedPreferences.edit().putBoolean("voicePrompts", voicePrompts).apply();
        Log.i("Set voice Mode = ", String.valueOf(voicePrompts));
    }

    public void setupSettingsValues() {
        if (advancedMode) {
            settingsValues =
                    new String[]{"Mode1", "Vibrate1", "Voice prompts",
                            TimeConverters.convertIntSecStringsmmss(intervalTotalTimeInSeconds),
                            TimeConverters.convertIntSecStringsmmss(breaktime),
                            TimeConverters.convertIntSecStringsmmss(sampleTimeSeconds),
                            TimeConverters.convertIntSecStringsmmss(roundoneTimeSeconds),
                            TimeConverters.convertIntSecStringsmmss(roundTwoTimeSeconds),
                            TimeConverters.convertIntSecStringsmmss(roundThreeTimeSeconds)
                    };
        }else {
            settingsValues =
                    new String[]{"Mode1", "Vibrate1", "Voice prompts",
                            TimeConverters.convertIntSecStringsmmss(intervalTotalTimeInSeconds),
                            TimeConverters.convertIntSecStringsmmss(breaktime),
                            getString(R.string.NA_in_advanced_mode),
                            getString(R.string.NA_in_advanced_mode),
                            getString(R.string.NA_in_advanced_mode),
                            getString(R.string.NA_in_advanced_mode),
                    };
        }
    }
}

