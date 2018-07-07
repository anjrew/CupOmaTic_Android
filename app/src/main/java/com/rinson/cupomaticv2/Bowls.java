package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Bowls extends AppCompatActivity {

    int numberOfBowls;
    EditText numberSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowls);
        numberSelection = findViewById(R.id.bowlsSelection);
    }

    public void startButton(View view) {
        openMainActivity();
        MainActivity.updatdateMainTimerDisplay("Ready");
        ParentTimer.startStartTimer();
        ParentTimer.switchParentTimerActiviationState();
        MainActivity.showStopButton();
        Log.i("Info", "Start Button pressed");
        numberOfBowls = Integer.parseInt(numberSelection.getEditableText().toString());
        Log.i("Number of Bowls",String.valueOf(numberOfBowls));
    }

    public void openMainActivity() {

        Intent intent = new Intent(Bowls.this, MainActivity.class);

        startActivity(intent);
    }
}

