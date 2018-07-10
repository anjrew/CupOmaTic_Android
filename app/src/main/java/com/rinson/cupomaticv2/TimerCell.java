package com.rinson.cupomaticv2;

import android.os.Bundle;
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

    TimerCell(String label, int interval, int timerSetting, int bowlCount, String iD){

        this.label = label;
        this.bowlCount = bowlCount;
        this.timerSetting = timerSetting;
        this.active = false;
        this.interval = interval;
        this.timePassed = interval;
        this.bowlsPassed = 0;
        this.iD = iD;

    }

//    public void activate(){
//        if(active == !true){
//        bowlsPassed ++ ;}
//        this.active = true;
//        startTimerCell();    }

    public  void cancelTimer(){

        timer.cancel();
        totalResetTimer();
    }


    public void stageTimer(){
        Log.i("Inside","Timercell");
        timer = new CountDownTimer((interval * 1000), 1000) {


            @Override
            public void onTick(long millisecondsUntilDone) {
                //Code executed at every Interval
                timePassed = timePassed - 1;
                upDateIntervalTimer();
                Log.i("Bowls done for "+ iD,String.valueOf(bowlsPassed) + " Time Passed = " + String.valueOf(timePassed));

            }

            @Override
            public void onFinish() {
                //Code executed at finish
                if (bowlsPassed == (bowlCount - 1)){
                    bowlsPassed = bowlsPassed + 1 ;
                    timePassed = 0;
                    upDateIntervalTimer();

                    Log.i(label,"  timer has Finished");

                }else{
                    resetTimer();
                    startTimerCell();

                    Log.i(label,"  timer completed bowl " + String.valueOf(bowlsPassed));}
            }
        }.start();
    }

//    public void decreaseTimer(){
//        Log.i("Bowls "+ iD,String.valueOf(bowlsPassed) + " Time Passed = " + String.valueOf(timePassed));
//
//        if (active){
//
//            timePassed = timePassed - 1;
//
//            if (bowlsPassed == bowlCount){
//                timePassed = 0;
//                this.active = false;
//
//
//
//            }else if (timePassed == 0){
//                bowlsPassed = bowlsPassed + 1;
//                timePassed = interval;
//            }
//        }
//    }

    public void startTimerCell(){

        stageTimer();
        bowlsPassed = bowlsPassed + 1;
    }


    public void resetTimer(){
        timePassed = interval;
        this.active = false;
    }

    public void totalResetTimer(){

        bowlsPassed = 0;
        resetTimer();
    }

    public void resetForNextBowl(){

        active = false;
    }


    public String Label(){
        return this.label;
    }

    public int getBowlCount(){
        return this.bowlCount;
    }

    public int getTimerSetting(){
        return this.timerSetting;
    }

    public int getBowlsPassed(){
        return this.bowlsPassed;
    }

    public int getTimePassed(){
        return this.timePassed;
    }

    public String getId(){
        return this.iD;
    }



    public int getTimeUntil(int mainTimerTime){
        return mainTimerTime - timePassed;
    }


    public String getBowlsPassedString(int numberOfBowls){

        return String.valueOf(getBowlsPassed());

    }


    public int getDisplayTime(int mainTimer){
        if (mainTimer <= timerSetting) {

            return timerSetting - mainTimer;

        }else if(timerSetting == mainTimer){

            return 0;

        }else{

            return getTimePassed();
        }
    }

    public void upDateIntervalTimer(){
        MainActivity.intervalProgress.setProgress(timePassed);

    }


}
