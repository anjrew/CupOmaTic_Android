package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int bowlSetting;
    int intervalTotalTimeInSeconds;
    int breaktime;
    int sampleTimeSeconds;
    int roundoneTimeSeconds;
    int roundTwoTimeSeconds;
    int roundThreeTimeSeconds;
    boolean advancedMode;
    boolean vibrate;

    SharedPreferences sharedPreferences;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("bowlSetting")){
            bowlSetting = sharedPreferences.getInt("bowlSetting", 0);
        } else {
            sharedPreferences.edit().putInt("bowlSetting", 20).apply();
        }

        if (sharedPreferences.contains("advancedMode")){
            advancedMode = sharedPreferences.getBoolean("advancedMode",false);
        } else {
            sharedPreferences.edit().putBoolean("advancedMode", false).apply();
        }
        //
        if (sharedPreferences.contains("intervalTotalTimeInSeconds")){
            intervalTotalTimeInSeconds = sharedPreferences.getInt("intervalTotalTimeInSeconds", 20);
        } else {
            sharedPreferences.edit().putInt("intervalTotalTimeInSeconds", 20).apply();
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
    }



    ParentTimer parentTimer;
//    IntervalTimer intervalTimer;
    public static TextView mainTimerDisplayText;
    public static Button getReadyButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting){
            openSettingsActivity();
            Log.i("Menu items selected", "Settings");
        }else{
            openHelpActivity();
            Log.i("Menu items selected", "Help");
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMemory();
        parentTimer = new ParentTimer( advancedMode, bowlSetting, intervalTotalTimeInSeconds, breaktime, sampleTimeSeconds, roundoneTimeSeconds, roundTwoTimeSeconds, roundThreeTimeSeconds, vibrate);
        mainTimerDisplayText = findViewById(R.id.mainTimerDisplay);
        getReadyButton = findViewById(R.id.getReadyButton);
        updateGetReadyStopButton();

    }


    public void clickButton(View view) {

        if (parentTimer.getRunningstaus() == true){
            parentTimer.setRunningStatusToFalse();
            updateGetReadyStopButton();
            showGetReadyButton();
            parentTimer.cancelCountdownTimer();

        }else {

            openBowlsActivity();
            updateGetReadyStopButton();
        }
        Log.i("Info", "Button was pressed");
    }


    // Open activity section
    public void openBowlsActivity() {
        Intent intent = new Intent(MainActivity.this,Bowls.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent settingsIntent = new Intent(MainActivity.this,Settings.class);
        startActivity(settingsIntent);
    }

    public void openHelpActivity(){
        Intent intent = new Intent(MainActivity.this,Help.class);
        startActivity(intent);
    }

    public void updatdateMainTimerUi(){
        mainTimerDisplayText.setText("Go!");
    }

    public static  void updatdateMainTimerDisplay(String text){
        mainTimerDisplayText.setText(text);
    }

    public static void showStopButton(){
        getReadyButton.setBackgroundResource(R.drawable.stop_button);
        getReadyButton.setText("Stop");
        getReadyButton.setTextColor(Color.WHITE);
    }

    private void showGetReadyButton(){
        getReadyButton.setBackgroundResource(R.drawable.get_ready_button);
        getReadyButton.setText("Get Ready");
        getReadyButton.setTextColor(Color.WHITE);
    }

    public void updateGetReadyStopButton() {
        if (parentTimer.getRunningstaus() == true) {
            showStopButton();
        } else {
            showGetReadyButton();
        }
    }

    static public void updateIntervalDisplayIntToString(int time){

    }
}
