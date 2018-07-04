package com.rinson.cupomaticv2;

import android.content.Intent;
import android.graphics.Color;
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
    }


    public void clickButton(View view) {

        if (parentTimer.getRunningstaus() == true){

        }else {
            openBowlsActivity();
            updateButtonGetReadyStop();
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
        mainTimerDisplayText.setText("Start");
    }

    public void updateButtonGetReadyStop(){

        if (parentTimer.getRunningstaus() == true){
            Log.i("Timer Status", "Running");

        }else{
            Log.i("Timer Status", "Not running");
        }

    }


    public static  void updatdateMainTimerDisplay(String text){
        mainTimerDisplayText.setText(text);
    }

    public static void showStopButton(){
        getReadyButton.setBackgroundColor(Color.RED);
        getReadyButton.setText("Stop");
        getReadyButton.setTextColor(Color.WHITE);
    }

    private void showGetReadyButton(){
        getReadyButton.setBackgroundColor(Color.GREEN);
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
}
