package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class bowls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowls);
    }

    public void startButton(View view) {
        openBowlsActivity();
        Log.i("Info", "Start Button pressed");
    }

    public void openBowlsActivity() {

        Intent intent = new Intent(bowls.this, MainActivity.class);

        startActivity(intent);
    }
}
