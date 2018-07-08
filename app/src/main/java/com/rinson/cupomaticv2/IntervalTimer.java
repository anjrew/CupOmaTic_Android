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


    public void decreaseTimer(){

        if (bowlAmount > 0) {
            intervalTimeInSeconds -= 1;

            if (intervalTimeInSeconds == 0){
                intervalTimeInSeconds = timeSetting;
                bowlAmount -= 1;
            }

        }else if (bowlAmount == 0) {

            invalidateIntervalTimer();
            reset();

        }else{
            Log.i("WARNING","YOU SHOULDN'T BE HERE");
        }
    }



    public void startTimer(){
        active = true;
        bowlAmount -= 1;
        intervalTimer();
    }

    IntervalTimer(int timeSetting, int bowlSetting) {
        this.timeSetting = timeSetting;
        this.bowlSetting = bowlSetting;
        this.intervalTimeInSeconds = timeSetting;
        this.bowlAmount = bowlSetting;
        this.active = true;

;
        Log.i("IntervalTimeInSeconds :",String.valueOf(intervalTimeInSeconds));
    }

    public void invalidateIntervalTimer(){
        intervalTimer.cancel();
        active = false;
    }

    public void reset(){

        intervalTimeInSeconds = timeSetting;
        active = false;
    }

    public void totalReset(){

        bowlAmount = bowlSetting;
        reset();
    }



    public void intervalTimer(){

        intervalTimer = new CountDownTimer((timeSetting * 1000), 1000) {

            @Override
            public void onTick(long millisecondsUntilDone) {
                //Code executed at every Interval
                intervalTimeInSeconds = ((int)millisecondsUntilDone/1000);
                MainActivity.intervalProgress.setProgress(intervalTimeInSeconds);
            }

            @Override
            public void onFinish() {
                intervalTimeInSeconds = 0;
                //Code executed at finish
                MainActivity.intervalProgress.setProgress(intervalTimeInSeconds);

                reset();
                if(bowlAmount > 0){
                    Log.i("interval Timer","Finished "+String.valueOf(bowlAmount));
//                    bowlAmount = bowlAmount - 1;
                    MainActivity.intervalProgress.setProgress(0);

                    startTimer();
                }else{
                    MainActivity.intervalProgress.setProgress(0);

                    Log.i("interval Timer","Finished");
                }
            }
        }.start();
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
}
