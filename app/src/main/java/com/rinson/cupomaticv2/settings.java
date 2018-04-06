package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void saveButtonClick(View view){

        openMainActivity();

    }


    public void openMainActivity() {

        Intent intent = new Intent(settings.this, MainActivity.class);

        startActivity(intent);
    }
}