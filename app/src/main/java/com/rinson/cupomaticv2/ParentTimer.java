package com.rinson.cupomaticv2;


import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class ParentTimer {


    public static Handler handler = new Handler();
    int[] timersIntervals;
    public static TimerCell[] timers;
    static int mainTime;
    static int alarmWarning = 1;

    int bowlSetting;
    int intervalTotalTimeInSeconds;
    int breaktime;
    int sampleTimeSeconds;
    int roundoneTimeSeconds;
    int roundTwoTimeSeconds;
    int roundThreeTimeSeconds;
    static boolean advancedMode;
    boolean vibrate;
    private static int getReadyWarningTime = 10;
    static MainActivity mainActivity;


    static IntervalTimer intervalTimer;

    //Start Timer
    Boolean initiateMainTimer = true;
    int startTime;
    public static Boolean running = false;
    static int startDisplayTime = 1;
    static int getReadyDisplayTime = 5;
    static int countDownTimerSeconds = 9;
    static CountDownTimer startTimer;


    public static void startStartTimer() {

        startTimer = new CountDownTimer(countDownTimerSeconds*1000, 1000) {

            @Override
            public void onTick(long millisecondsUntilDone) {
                //Code executed at every Interval
                if ((millisecondsUntilDone / 1000 > countDownTimerSeconds - getReadyDisplayTime)){

                    mainActivity.updatdateMainTimerDisplay("Get Ready");

                }else if(((millisecondsUntilDone / 1000) > startDisplayTime)) {

                    playBeep();
                    MainActivity.updatdateMainTimerDisplay(String.valueOf((millisecondsUntilDone / 1000 - startDisplayTime)));

                } else {
                    mainActivity.playPour();
                    mainActivity.updatdateMainTimerDisplay("GO!");

                }
                Log.i("Countdown timer" , String.valueOf(millisecondsUntilDone / 1000));
            }

            @Override
            public void onFinish() {
                //Code executed at finish

                startMainTimer();
                MainActivity.updatdateMainTimerDisplay("Go!");
            }

        }.start();
    }


    //Timer
    public  static Runnable mainTimer = new Runnable() {
        @Override
        public void run() {
            //insert code to be run every second

            increaseTimer();

            if(running == true) {

                mainTime++;
                MainActivity.updatdateMainTimerDisplay(TimeConverters.convertIntSecStringsmmss(mainTime));

                //Time interval of timer
                handler.postDelayed(this, 1000);
            }else{
                handler.removeCallbacks(mainTimer);
            }
        }
    };

    public static void startMainTimer(){
        running = true;
        handler.removeCallbacks(mainTimer);
        handler.postDelayed(mainTimer, 0);

    }


    public static int getMainTimeInterger() {
        return mainTime;
    }

    public Boolean getRunningstaus() {
        return running;
    }



    public void cancelCountdownTimer(){
        startTimer.cancel();

    }

    ParentTimer(boolean advancedMode, int bowlSetting, int intervalTotalTimeInSeconds, int breaktime, int sampleTimeSeconds, int roundoneTimeSeconds,int roundTwoTimeSeconds,int roundThreeTimeSeconds, boolean vibrate, MainActivity mainActivity) {
        this.advancedMode = advancedMode;
        this.bowlSetting = bowlSetting;
        this.intervalTotalTimeInSeconds = intervalTotalTimeInSeconds;
        this.breaktime = breaktime;
        this.sampleTimeSeconds = sampleTimeSeconds;
        this.roundoneTimeSeconds = roundoneTimeSeconds;
        this.roundTwoTimeSeconds = roundTwoTimeSeconds;
        this.roundThreeTimeSeconds = roundThreeTimeSeconds;
        this.vibrate = vibrate;
        this.mainActivity = mainActivity;


        intervalTimer = new IntervalTimer(intervalTotalTimeInSeconds,bowlSetting, this);

        timersIntervals = new int[]{0, breaktime,sampleTimeSeconds, roundoneTimeSeconds, roundTwoTimeSeconds, roundThreeTimeSeconds};
        setupTimerCells();

    }

    public static void increaseTimer() {


        MainActivity.updateProgressViews();


        for (TimerCell x : timers) {
            startTimercellOnTime(x);
            playWarningOnCall(x);
            playBeepOnCall(x);
            getReadyVoiceOnCall(x);


        }
    }

    public static void playBeepOnCall(TimerCell x) {
        if(mainTime == x.getTimerSetting()){
            mainActivity.playBeep();
        }
    }

    public static void playBeep() {
        mainActivity.playBeep();
    }


    private static void playWarningOnCall(TimerCell x) {
        if (mainTime == x.getTimerSetting() -  alarmWarning){
            mainActivity.setupintervalsMediaPLayers(x.iD);

        }
    }


    public static void getReadyVoiceOnCall(TimerCell timer){

        if (timer.getTimerSetting() - getReadyWarningTime == getMainTimeInterger()) {
            mainActivity.playGetReady();

        }

    }


    public static void  startTimercellOnTime(TimerCell timerCell){

        if (timerCell.getTimerSetting() == mainTime) {
            timerCell.startTimerCell();

        }
    }

    public void cancelTimerCells(){

        for (TimerCell x : timers){
            if(x.getActiveStatus() == true){
                x.cancelTimer();
            }
        }
    }


    public void startIntervalTimer(String id){
        intervalTimer.setId(id);
        intervalTimer.startTimer();

    }

    public void parentTimerCancelsIntervalTimer(){

        if(intervalTimer.active)intervalTimer.cancelIntervalTimerFromParent();

    }

    public void stopParentTimer(){
        if(running == true){
            cancelCountdownTimer();
            running = false;
            handler.removeCallbacks(mainTimer);}
    }

    public void resetMainTimeToZero(){

        mainTime = 0;

    }

    public void setupTimerCells(){

        if(advancedMode) {
            timers = new TimerCell[]{
                    new TimerCell("Pour", intervalTotalTimeInSeconds, 0, bowlSetting, "pour", this),

                    new TimerCell("Break", intervalTotalTimeInSeconds, timersIntervals[1], bowlSetting, "brake", this),

                    new TimerCell("Sample", intervalTotalTimeInSeconds, timersIntervals[2], bowlSetting, "sample", this),

                    new TimerCell("Round 1", 1, timersIntervals[3], 1, "round_one", this),

                    new TimerCell("Round 2", 1, timersIntervals[4], 1, "round_two", this),

                    new TimerCell("Round 3", 1, timersIntervals[5], 1, "round_three", this),
            };
        }else{

            timers = new TimerCell[]{
                    new TimerCell("Pour", intervalTotalTimeInSeconds, 0, bowlSetting, "pour", this),

                    new TimerCell("Break", intervalTotalTimeInSeconds, timersIntervals[1], bowlSetting, "brake", this),

                    new TimerCell("Sample", 1, -1, 1, "sample", this),

                    new TimerCell("Round 1", 1, -1, 1, "round_one", this),

                    new TimerCell("Round 2", 1, -1, 1, "round_two", this),

                    new TimerCell("Round 3", 1, -1, 1, "round_three", this),
            };
        }
    }

}


