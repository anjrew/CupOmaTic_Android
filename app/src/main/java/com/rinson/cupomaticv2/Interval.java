package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Interval extends AppCompatActivity {

//    Button set = (Button)findViewById(R.id.intervalSetButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
    }

    public void pressSetButton(View view){
        openSettingsActivity();
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Interval.this, Settings.class);
        startActivity(intent);
    }
}

