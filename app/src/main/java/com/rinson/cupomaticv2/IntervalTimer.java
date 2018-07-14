package com.rinson.cupomaticv2;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import java.util.Timer;

public class IntervalTimer {

    int timeSetting;
    int bowlSetting;
    Timer timer;
    int bowlAmount;
    int intervalTimeInSeconds;
    Boolean active;
    static Boolean running;
    static CountDownTimer intervalTimer;
    String id;


    public void decreaseTimer(){

        if (bowlAmount > 0) {
            intervalTimeInSeconds -= 1;

            if (intervalTimeInSeconds == 0){
                intervalTimeInSeconds = timeSetting;
                bowlAmount -= 1;
            }

        }else if (bowlAmount == 0) {

            invalidateIntervalTimer();
            totalReset();

        }else{
            Log.i("WARNING","YOU SHOULDN'T BE HERE");
        }
    }



    public void startTimer(){
        intervalTimer();
        active = true;
        bowlAmount -= 1;

    }

    IntervalTimer(int timeSetting, int bowlSetting) {
        this.timeSetting = timeSetting;
        this.bowlSetting = bowlSetting;
        this.intervalTimeInSeconds = timeSetting;
        this.bowlAmount = bowlSetting;
        this.active = false;

        ;
        Log.i("IntervalTimeInSeconds :",String.valueOf(intervalTimeInSeconds));
    }

    public static void invalidateIntervalTimer(){
        intervalTimer.cancel();
        cancelIntervalTimer();
    }

    public void resetForNextBowl(){

        intervalTimeInSeconds = timeSetting;
        active = true;
    }

    public void totalReset(){
        cancelIntervalTimer();
        active = false;
    }



    public void intervalTimer(){

        intervalTimer = new CountDownTimer((timeSetting * 1000), 1000) {

            @Override
            public void onTick(long millisecondsUntilDone) {
                //Code executed at every Interval
                Log.i("Interval Timer =",String.valueOf(id)+"  - Time = " + String.valueOf(millisecondsUntilDone/1000)+ "-  Bowls left =" + bowlAmount) ;
                intervalTimeInSeconds = ((int)millisecondsUntilDone/1000);
                MainActivity.intervalProgress.setProgress(intervalTimeInSeconds);
            }

            @Override
            public void onFinish() {
                intervalTimeInSeconds = 0;
                //Code executed at finish
                if(bowlAmount > 0){
                    MainActivity.intervalProgress.setProgress(0);
                    Log.i("interval Timer","Finished "+String.valueOf(bowlAmount));
                    resetForNextBowl();
                    startTimer();
                }else{
                    MainActivity.intervalProgress.setProgress(0);
                    totalReset();
                    Log.i("interval Timer","Finished");
                }
            }
        }.start();
    }

    public static void  cancelIntervalTimer(){

        intervalTimer.cancel();
    }

    public void cancelIntervalTimerFromParent(){

        intervalTimer.cancel();

    }



    public Boolean queryActive(){

        return active;
    }

    public int getTimeInt() {
        return intervalTimeInSeconds;
    }

    public void setBowlAmount(int bowlSetAs){
        bowlAmount = bowlSetAs;
    }

    public int getSecondsFromMilliSeconds(int milliSeconds){

        int seconds = milliSeconds/1000;

        return seconds;
    }
    public void setId(String iD){

        this.id = iD;

    }
}
