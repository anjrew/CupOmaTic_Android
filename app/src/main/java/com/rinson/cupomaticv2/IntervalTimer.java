package com.rinson.cupomaticv2;

import android.os.Handler;
import android.util.Log;

import java.util.Timer;

public class IntervalTimer {

    int timeSetting;
    int bowlSetting;
    Timer timer;
    int bowlAmount;
    static int time;
    //  var second: Float
    Boolean active;
    int timeUnit = 10;
    static Boolean running;
    ParentTimer parentTimer;
    public static Handler handler = new Handler();


    public void setParentTimer (ParentTimer parentTimer){
        this.parentTimer = parentTimer;
    }

    public void decreaseTimer(){

        if (bowlAmount > 0) {
            time -= 1;

            if (time == 0){
                time = timeSetting;
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
        this.time = this.timeSetting;
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
        time = timeSetting;
        active = false;
        }

    public  static Runnable mainTimer = new Runnable() {
        @Override
        public void run() {
            //insert code to be run every second
//                increaseTimeByOneSecond();
            if(running == true) {

                time ++;
                TimeConverters.convertIntSecStringsmmss(time);

                //Time interval of timer
                handler.postDelayed(this, 1000);
            }else{
                handler.removeCallbacks(mainTimer);
            }
        }
    };

    public static void startMainTimer(){
//        showStopButton();
        handler.removeCallbacks(mainTimer);
        handler.postDelayed(mainTimer, 0);
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
