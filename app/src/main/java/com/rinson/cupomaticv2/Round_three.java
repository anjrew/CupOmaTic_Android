package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Round_three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_three);
    }

    public void pressSetButton(View view){
        openSettingsActivity();
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Round_three.this, Settings.class);
        startActivity(intent);
    }
}
