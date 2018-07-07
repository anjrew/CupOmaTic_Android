package com.rinson.cupomaticv2;

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


    ParentTimer parentTimer = new ParentTimer();
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
        mainTimerDisplayText = findViewById(R.id.mainTimerDisplay);
        getReadyButton = findViewById(R.id.getReadyButton);
        updateGetReadyStopButton();
//        intervalTimer = new IntervalTimer(intervalTotalTimeInSeconds, bowlSetitng);

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
