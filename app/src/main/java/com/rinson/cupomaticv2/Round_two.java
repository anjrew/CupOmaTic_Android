package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Round_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_two);
    }

    public void pressSetButton(View view){
        openSettingsActivity();
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Round_two.this, Settings.class);
        startActivity(intent);
    }
}
