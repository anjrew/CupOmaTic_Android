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
            increaseTimer();
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
        intervalTimer.intervalTimer();
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

        intervalTimer = new IntervalTimer(intervalTotalTimeInSeconds,bowlSetting);

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


        MainActivity.updateProgressViews();

        for (int x = 0; x < timers.length; x++){



                    //in advanced mode controlling timers and progressbars for the rounds.
                    if (intervalTimer.active != true) {
                        if (x < 5) {
                            if (timers[x].getTimerSetting() < timers[x + 1].getTimerSetting()) {

                                if (timers[x].getTimerSetting() > mainTime) {

                                    if (timers[x].getTimerSetting() - mainTime == (alarmWarning + 1)) {
//                                        timers[x].playSound()
                                    }

                                    if (timers[x].getTimerSetting() - mainTime == (alarmWarning + 10)) {
//                                        audio.playGetReady()
                                    }

                                } else if (timers[x].getTimerSetting() == mainTime) {
                                    MainActivity.intervalProgress.setBottomText(timers[x].Label());
                                    intervalTimer.startTimer();

//                                    AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                    vibrateProcess()

                                } else {

                                    MainActivity.intervalProgress.setProgress(timers[1].getTimePassed());
                                    MainActivity.intervalProgress.setBottomText(timers[1].getId());

                                    if (timers[x].getTimePassed() == alarmWarning) {
//                                        AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                        vibrateProcess()
                                    }
                                }
                            }
                        } else {


                            // Final Round interval progress ui

                            if (timers[x].getTimerSetting() > mainTime) {

                                if (timers[x].getTimerSetting() - mainTime == (alarmWarning + 1)) {
//                                    timers[i].playSound()
                                }

                                if (timers[x].getTimerSetting() - mainTime == (alarmWarning + 10)) {
//                                    audio.playGetReady()
                                }

                            } else if (timers[x].getTimerSetting() == mainTime) {

                                MainActivity.intervalProgress.setBottomText(timers[x].Label());
//                                AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                vibrateProcess()

                            } else {

                                MainActivity.intervalProgress.setProgress(timers[1].getTimePassed());
                                MainActivity.intervalProgress.setBottomText(timers[1].getId());

                                if (timers[x].getTimePassed() == alarmWarning) {
//                                        AudioServicesPlaySystemSound(SystemSoundID(1256))
//                                        vibrateProcess()
                                }

                            }
                        }
                    }
            timers[x].decreaseTimer();
            if(x < timers.length){
                x += 1;
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


