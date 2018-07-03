package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Bowls extends AppCompatActivity {

//    ParentTimer parentTimer = new ParentTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowls);
    }

    public void startButton(View view) {
        openMainActivity();
        ParentTimer.startStartTimer();
        ParentTimer.switchParentTimerActiviationState();
        Log.i("Info", "Start Button pressed");
    }

    public void openMainActivity() {

        Intent intent = new Intent(Bowls.this, MainActivity.class);

        startActivity(intent);
    }
}


//    ParentTimer parentTimer;
////    MainActivity mainActivity;
////
////    Bowls(ParentTimer parentTimer, MainActivity mainActivity){
////        this.mainActivity = mainActivity;
////        this.parentTimer = parentTimer;
////    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bowls);
//    }
//
//    public void startButton(View view) {
//        parentTimer.startStartTimer();
//        openBowlsActivity();
//        Log.i("Info", "Start Button pressed");
//    }
//
//    public void openBowlsActivity() {
//
//        Intent intent = new Intent(Bowls.this, MainActivity.class);
//
//        startActivity(intent);
//    }
//}
