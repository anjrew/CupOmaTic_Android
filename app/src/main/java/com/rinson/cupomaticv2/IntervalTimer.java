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
    static int intervalTimeInSeconds;
    //  var second: Float
    Boolean active;
    int timeUnit = 10;
    static Boolean running;
    ParentTimer parentTimer;
    static CountDownTimer intervalTimer;


    public void setParentTimer (ParentTimer parentTimer){
        this.parentTimer = parentTimer;
    }

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

//        parentTimer?.viewController?.updateIntervalViews()
    }



    public void startTimer(){
        active = true;
        bowlAmount -= 1;

        timer.cancel();
    }

    IntervalTimer(int timeSetting, int bowlSetting) {
        this.timeSetting = timeSetting * timeUnit;
        this.bowlSetting = bowlSetting;
        this.intervalTimeInSeconds = this.timeSetting;
        this.bowlAmount = bowlSetting;
        this.active = true;
//        setParentTimer(ParentTimer);
    }

    public void invalidateIntervalTimer(){
        timer.cancel();
        active = false;

    }

    public void reset(){

        bowlAmount = bowlSetting;
        intervalTimeInSeconds = timeSetting;
        active = false;
        }



    public static void startIntervalTimer(){

        intervalTimer = new CountDownTimer(intervalTimeInSeconds*1000, 1000) {


            @Override
            public void onTick(long millisecondsUntilDone) {
                //Code executed at every Interval
                MainActivity.updateIntervalDisplayIntToString((int)millisecondsUntilDone/1000);
                Log.i("interval Timer",String.valueOf(millisecondsUntilDone/1000));
            }

            @Override
            public void onFinish() {
                //Code executed at finish
                Log.i("interval Timer","Finished");
                //reset();
            }

        }.start();
    }

    public Boolean queryActive(){

        return active;
    }

//    public String getTimeLabel(){
////        return String(Int(trunc(( Double(time) / Double(timeUnit) ) )) )
//    }

//
//    public float getIntervalPercentage(){
//
//        if(time == 0 ){
//            return 0;
//        }
//        return (float)(Double(timeSetting - time) / Double(timeSetting))
//    }

    public void setBowlAmount(int bowlSetAs){
        bowlAmount = bowlSetAs;
    }
}
