package com.rinson.cupomaticv2;

import android.os.CountDownTimer;
import android.util.Log;

public class TimerCell {

    String label;
    int timerSetting;
    int bowlCount;
    Boolean active;
    int interval;
    int timePassed;
    int bowlsPassed;
    String iD;
    static CountDownTimer timer;
    ParentTimer parentTimer;


    TimerCell(String label, int interval, int timerSetting, int bowlCount, String iD, ParentTimer parentTimer){

        this.label = label;
        this.bowlCount = bowlCount;
        this.timerSetting = timerSetting;
        this.active = false;
        this.interval = interval;
        this.timePassed = interval;
        this.bowlsPassed = 0;
        this.iD = iD;
        this.parentTimer = parentTimer;
    }


    public  void cancelTimer(){
        active = false;
        timer.cancel();
        totalResetTimer();
    }


    public void stageTimer(){

        if(active = true) {

            timer = new CountDownTimer((interval * 1000), 1000) {
                @Override
                public void onTick(long millisecondsUntilDone) {
                    //Code executed at every Interval
                    timePassed = timePassed - 1;
//                    Log.i("Bowls done for " + iD, String.valueOf(bowlsPassed) + " Time Passed = " + String.valueOf(timePassed) + " - Active = " + String.valueOf(active));

                }

                @Override
                public void onFinish() {
                    //Code executed at finish
                    if (bowlsPassed == (bowlCount - 1)) {
                        bowlsPassed = bowlsPassed + 1;
                        timePassed = 0;
                        active = false;


//                        Log.i(label, "  timer has Finished");

                    } else {
                        timePassed = timePassed - 1;
                        resetTimer();
                        ReStartTimerCell();

//                        Log.i(label, "  timer completed bowl " + String.valueOf(bowlsPassed) + " Time = " + String.valueOf(timePassed));
                    }
                }
            }.start();
        }
    }

    public void startTimerCell(){
        if (parentTimer.running){
            if(!active){
        active = true;
        stageTimer();
        parentTimer.startIntervalTimer(iD);
        bowlsPassed = bowlsPassed + 1;}}
    }

    public void ReStartTimerCell(){
        if (parentTimer.running){
                active = true;
                stageTimer();
//                parentTimer.startIntervalTimer(iD);
                bowlsPassed = bowlsPassed + 1;}
    }


    public void resetTimer(){
        timePassed = interval;
    }

    public void totalResetTimer(){

        bowlsPassed = 0;
        resetTimer();
    }


    public int getTimerSetting(){
        return this.timerSetting;
    }

    public String getId(){
        return this.iD;
    }

    public boolean getActiveStatus(){
        return active;
    }


    public void upDateIntervalTimer(){
        parentTimer.mainActivity.intervalProgress.setProgress(timePassed);

    }

    public void upDateIntervalTimerToZero(){
        parentTimer.mainActivity.intervalProgress.setProgress(0);

    }

}
