package com.rinson.cupomaticv2;


import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class ParentTimer {


    public static Handler handler = new Handler();
    int bowl;
    int timerAlarms;
    int[] timersIntervals;
    //    timer timer;
    static TimerCell[] timers;
    static int mainTime;
    int alarmSound;
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


    static IntervalTimer intervalTimer;

    //Start Timer
    Boolean initiateMainTimer = true;
    //    CountDownTimer startTimer;
    Boolean timerActive = false;
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

                    MainActivity.updatdateMainTimerDisplay("Get Ready");

                }else if(((millisecondsUntilDone / 1000) > startDisplayTime)) {

                    MainActivity.updatdateMainTimerDisplay(String.valueOf((millisecondsUntilDone / 1000 - startDisplayTime)));
                } else {
                    MainActivity.updatdateMainTimerDisplay("GO!");
                }

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
//                increaseTimeByOneSecond();
            if(running == true) {

                mainTime++;
                MainActivity.updatdateMainTimerDisplay(convertSecsmmss(mainTime));

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


    public static void increaseTimeByOneSecond() {
        for (int i = 0; i < timers.length; i++) {
//            timers[i].addSecond();
        }
        mainTime = +1;

    }

    public void comeBackFromBreak() {

    }


    public void activateRunTimer() {
        running = true;
    }

    public void deactivateRunTimer() {
        running = false;
    }

    public static int getMainTimeInterger() {
        return mainTime;
    }

    public Boolean getRunningstaus() {
        return running;
    }

    public void setRunningStatusToFalse(){
        running = false ;
    }

    public static void switchParentTimerActiviationState() {
        if (running == true) {

            running = false;

        } else {

            running = true;
        }

        Log.i("Main time run status", String.valueOf(running));
    }

    public static String convertSecsmmss(int timeInput){

        int timeSecs = timeInput;
        String timeString;

        int minutes = timeSecs / 60;
        int seconds = timeSecs % 60;

        if (minutes < 10 && seconds < 10){

            timeString = "0"+ String.valueOf(minutes)+":0"+String.valueOf(seconds);

        }else if (minutes < 10 ){

            timeString = "0"+ String.valueOf(minutes)+":"+ String.valueOf(seconds);

        }else if (seconds < 10) {

            timeString = String.valueOf(minutes)+":0" + String.valueOf(seconds);

        } else {

            timeString = String.valueOf(minutes)+":"+String.valueOf(seconds);
        }
        return timeString;
    }



    public void cancelCountdownTimer(){
        startTimer.cancel();
    }

    ParentTimer(boolean advancedMode, int bowlSetting, int intervalTotalTimeInSeconds, int breaktime, int sampleTimeSeconds, int roundoneTimeSeconds,int roundTwoTimeSeconds,int roundThreeTimeSeconds, boolean vibrate) {
        this.advancedMode = advancedMode;
        this.bowlSetting = bowlSetting;
        this.intervalTotalTimeInSeconds = intervalTotalTimeInSeconds;
        this.breaktime = breaktime;
        this.sampleTimeSeconds = sampleTimeSeconds;
        this.roundoneTimeSeconds = roundoneTimeSeconds;
        this.roundTwoTimeSeconds = roundTwoTimeSeconds;
        this.roundThreeTimeSeconds = roundThreeTimeSeconds;
        this.vibrate = vibrate;

        intervalTimer = new IntervalTimer(intervalTotalTimeInSeconds,bowlSetting,this);

        timersIntervals = new int[]{0, breaktime,sampleTimeSeconds, roundoneTimeSeconds, roundTwoTimeSeconds, roundThreeTimeSeconds};

        timers = new TimerCell[]{
                new TimerCell("Pour", intervalTotalTimeInSeconds, 0, bowlSetting, "pour"),

                new TimerCell("Break", intervalTotalTimeInSeconds, timersIntervals[1],bowlSetting, "break"),

                new TimerCell("Sample", intervalTotalTimeInSeconds, timersIntervals[2], bowlSetting,"sample"),

                new TimerCell("Round 1", intervalTotalTimeInSeconds, timersIntervals[3], bowlSetting,"Rd 1"),

                new TimerCell("Round 2", intervalTotalTimeInSeconds, timersIntervals[4], bowlSetting,"Rd 2"),

                new TimerCell("Round 3", intervalTotalTimeInSeconds, timersIntervals[5], bowlSetting,"Rd 3"),
        };

    }

    public static void increaseTimer(){

        mainTime += 1;

        MainActivity.updateProgressViews();

        int i = 0;

        for (TimerCell timer : timers){

            if (advancedMode == false){

                if (i < 2) {

                    MainActivity.updateProgressViews();
                }
            }else{

                if (i < 3) {

                    MainActivity.updateProgressViews();

                }else{

                    //in advanced mode controlling timers and progressbars for the rounds.
                    if (intervalTimer.active != true){
                        if (i < 5){
                            if (timers[i].getTimerSetting() < timers[i+1].getTimerSetting()){

                                if (timers[i].getTimerSetting() > mainTime) {

                                    if (timers[i].getTimerSetting() - mainTime == (alarmWarning + 1 )){
//                                        timers[i].playSound()
                                    }

                                    if (timers[i].getTimerSetting() - mainTime == (alarmWarning + 10 )){
//                                        audio.playGetReady()
                                    }

                                } else if (timers[i].getTimerSetting() == mainTime){
                                    MainActivity.intervalProgress.setBottomText(timers[i].Label());

//                                    AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                    vibrateProcess()

                                } else {

                                    MainActivity.intervalProgress.setProgress(timers[1].getTimePassed());
                                    MainActivity.intervalProgress.setBottomText(timers[1].getId());

                                    if(timers[i].getTimePassed() == alarmWarning){
//                                        AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                        vibrateProcess()
                                    }
                                }
                            }
                        }else{


                            // Final Round interval progress ui

                            if (timers[i].getTimerSetting() > mainTime) {

                                if (timers[i].getTimerSetting() - mainTime == (alarmWarning + 1 )){
//                                    timers[i].playSound()
                                }

                                if (timers[i].getTimerSetting() - mainTime == (alarmWarning + 10 )){
//                                    audio.playGetReady()
                                }

                            } else if (timers[i].getTimerSetting() == mainTime){

                                MainActivity.intervalProgress.setBottomText(timers[i].Label());
//                                AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                vibrateProcess()

                            } else {

                                MainActivity.intervalProgress.setProgress(timers[1].getTimePassed());
                                MainActivity.intervalProgress.setBottomText(timers[1].getId());

                                if(timers[i].getTimePassed() == alarmWarning){
//                                        AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                        vibrateProcess()
                                }

                            }
                        }
                    }
                }
            }

            timer.decreaseTimer();
            if(i < timers.length){
                i += 1;
            }
        }

    }

    public static String getRoundTime(int timerIndex) {
        int timerOne;
        String time;
        int timeInt;
        int timerTwo;

        if (timerIndex <= timers.length){
//            Log.i("Get RoundTime, Timer Number " + String.valueOf(timerIndex) + " of " + String.valueOf(timers.length));
            timerOne = timers[timerIndex].getTimerSetting();
            timerTwo = timers[timerIndex+1].getTimerSetting();
            timeInt = (timerTwo-timerOne) - (getMainTimeInterger() - timerOne);

            if(timeInt > 60){
                time = convertSecsmmss(timeInt);
            }else{
                time = String.valueOf(timeInt);
            }

        }else{

            time = "Final Taste";
        }

        return time;
    }
}


