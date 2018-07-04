package com.rinson.cupomaticv2;

import android.os.Bundle;

public class TimerCell {

    String label;
    int timerSetting;
    int bowlCount;
    Boolean active;
    int interval;
    int timePassed;
    int bowlsPassed;
    String iD;

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

    public void activate(){

        bowlsPassed ++ ;
        this.active = true;

    }

    public void decreaseTimer(){

        if (active){

            timePassed -= 1;

            if (bowlsPassed == bowlCount){

                timePassed = 0;
                this.active = false;


            }else if (timePassed == 0){
                bowlsPassed += 1;
                timePassed = interval;
            }
        }
    }

    public void resetTimer(){
        timePassed = interval;
        bowlsPassed = 0;
        this.active = false;
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

    public double getPercentage(){

        return (double)bowlsPassed / (bowlCount);
    }

    public String getBowlsPassedString(int numberOfBowls){

        return String.valueOf(getBowlsPassed());

    }


    public int getDisplayTime(int mainTimer){
        if (mainTimer <= timerSetting) {

            return timerSetting - mainTimer;

        }else if(timerSetting == mainTimer){

            activate();
            return 0;

        }else{

            return getTimePassed();

        }

    }

}
