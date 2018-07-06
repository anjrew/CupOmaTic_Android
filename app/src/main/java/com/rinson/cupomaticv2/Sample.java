package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    public void pressSetButton(View view){
        openSettingsActivity();
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(Sample.this, Settings.class);
        startActivity(intent);
    }
}
