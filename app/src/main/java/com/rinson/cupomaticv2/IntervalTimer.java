package com.rinson.cupomaticv2;

import android.os.CountDownTimer;
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

    public void startTimer(){
        reset();
        if(!active){intervalTimer();}
        active = true;
    }

    IntervalTimer(int timeSetting, int bowlSetting) {
        this.timeSetting = timeSetting ;
        this.bowlSetting = bowlSetting;
        this.intervalTimeInSeconds = this.timeSetting * bowlSetting - 1;
        this.bowlAmount = bowlSetting;
        this.active = false;

        ;
        Log.i("IntervalTimeInSeconds :",String.valueOf(intervalTimeInSeconds));
    }

    public void invalidateIntervalTimer(){
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

    public void reset(){

        this.intervalTimeInSeconds = this.timeSetting * bowlSetting;
    }





    public void intervalTimer(){

        intervalTimer = new CountDownTimer((intervalTimeInSeconds * 1000), 1000) {

            @Override
            public void onTick(long millisecondsUntilDone) {

                intervalTimeInSeconds --;

                //Code executed at every Interval
//                Log.i("Interval Timer =",String.valueOf(id)+"  - Time = " + String.valueOf(millisecondsUntilDone/1000)+ "-  Bowls left =" + bowlAmount) ;
                MainActivity.intervalProgress.setProgress(intervalTimeInSeconds % timeSetting);
//                Log.i("Interval Timer", String.valueOf(intervalTimeInSeconds % timeSetting));
//                Log.i("Interval Timer setting", String.valueOf(timeSetting) + " Time " + String.valueOf(intervalTimeInSeconds));

            }

            @Override
            public void onFinish() {
                //Code executed at finish

                intervalTimeInSeconds = 0;
                active = false;

                MainActivity.intervalProgress.setProgress(0);
                Log.i("interval Timer","Finished "+String.valueOf(bowlAmount));
            }
        }.start();
    }

    public  void  cancelIntervalTimer(){

        intervalTimer.cancel();
        active = false;
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
