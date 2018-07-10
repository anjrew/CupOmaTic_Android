package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bowls extends AppCompatActivity {

    int numberOfBowls;
    EditText numberSelection;

    //Saved variables
    SharedPreferences sharedPreferences;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("bowlSetting")) {
            numberOfBowls = sharedPreferences.getInt("bowlSetting", 0);
        } else {
            sharedPreferences.edit().putInt("bowlSetting", 20).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowls);
        numberSelection = findViewById(R.id.bowlsSelection);
        setUpMemory();
    }

    public void startButton(View view) {

        if (numberSelection.getText().toString().matches("")) {
            Toast toast= Toast.makeText(getApplicationContext(),
                    "You did not enter a number.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }else {

            Log.i("Info", "Start Button pressed");
            numberOfBowls = Integer.parseInt(numberSelection.getEditableText().toString());
            sharedPreferences.edit().putInt("bowlSetting", numberOfBowls).apply();
            Log.i("Number of Bowls", String.valueOf(numberOfBowls));
            MainActivity.parentTimer.intervalTimer.setBowlAmount(numberOfBowls);
            goFromBowlsToStartTimer();
        }
    }

    public void openMainActivity() {

        Intent intent = new Intent(Bowls.this, MainActivity.class);

        startActivity(intent);
    }

    public void goFromBowlsToStartTimer(){
        MainActivity.invalidateAllTimers();
        openMainActivity();
        ParentTimer.startStartTimer();
        ParentTimer.switchParentTimerActiviationState();
        MainActivity.showStopButton();
    }
}

