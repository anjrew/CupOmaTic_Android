package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Bowls extends AppCompatActivity {

    int numberOfBowls;
    EditText numberSelection;

    //Saved variables
    SharedPreferences sharedPreferences;

    public void setUpMemory() {
        sharedPreferences = this.getSharedPreferences("com.rinson.cupomaticv2", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("numberOfBowls")) {
            numberOfBowls = sharedPreferences.getInt("numberOfBowls", 0);
        } else {
            sharedPreferences.edit().putInt("numberOfBowls", 20).apply();
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
        Log.i("Info", "Start Button pressed");
        numberOfBowls = Integer.parseInt(numberSelection.getEditableText().toString());
        Log.i("Number of Bowls",String.valueOf(numberOfBowls));
        MainActivity.parentTimer.intervalTimer.setBowlAmount(numberOfBowls);
        openMainActivity();
        ParentTimer.startStartTimer();
        ParentTimer.switchParentTimerActiviationState();
        MainActivity.showStopButton();

    }

    public void openMainActivity() {

        Intent intent = new Intent(Bowls.this, MainActivity.class);

        startActivity(intent);
    }
}

