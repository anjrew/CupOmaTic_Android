package com.rinson.cupomaticv2;

import android.util.Log;

import java.util.Timer;

public class IntervalTimer {





    int timeSetting;
    int bowlSetting;
    Timer timer;
    int bowlAmount;
    int time;
    //  var second: Float
    Boolean active;
    int timeUnit = 10;
    ParentTimer parentTimer;

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
