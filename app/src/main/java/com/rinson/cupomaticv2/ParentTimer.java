package com.rinson.cupomaticv2;



import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.TextView;

public class ParentTimer {



    public static Handler handler = new Handler();
    boolean advancedMode = false;
    boolean vibrate = false;
    int interval;
    int bowl;
    int timerAlarms;
    int[] timersIntervals;
    //    timer timer;
    public static TimerCell[] timers;
    public static int mainTime;
    int alarmSound;
    int alarmWarning = 1;


    IntervalTimer intervalTimer;

    //Start Timer
    Boolean initiateMainTimer = true;
    //    CountDownTimer startTimer;
    Boolean timerActive = false;
    int startTime;
    int bowlSetting;
    public static Boolean running = false;

    public static int startDisplayTime = 1;
    public static int getReadyDisplayTime = 5;
    public static int countDownTimerSeconds = 9;
    public static CountDownTimer startTimer;

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

    ParentTimer() {
//        this.advancedMode = UserDefaults.standard.object(forKey: "advancedMode") as! Bool
//        this.vibrate = UserDefaults.standard.object(forKey: "vibrate") as! Bool
//        this.viewController = viewController
//        this.interval = UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int
//        this.bowl = UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int
//        this.startTimerSetting = 4
//        this.intervalTimer = IntervalTimer(timeSetting: UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int, bowlSetting: (UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int))
//        reset()
//
//        intervalTimer.setParentTimer(parentTimer self)
//
//
//        timersIntervals = [
//        0,
//                UserDefaults.standard.object(forKey: "breakSettingSave")  as! Int,
//                UserDefaults.standard.object(forKey: "sampleSettingSave") as! Int,
//                UserDefaults.standard.object(forKey: "roundOneSettingSave") as! Int,
//                UserDefaults.standard.object(forKey: "roundTwoSettingSave") as! Int,
//                UserDefaults.standard.object(forKey: "roundThreeSettingSave") as! Int
//        ]
//
//
//        timers = [
//        TimerCell(label: "Pour", interval: interval, timerSetting: 0, bowlCount: UserDefaults.standard.object(forKey: "numberOfBowlsSave" ) as! Int, iD: "pour"),
//        TimerCell(label: "Break", interval: interval, timerSetting: timersIntervals[1], bowlCount: UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int, iD: "break"),
//        TimerCell(label: "Sample", interval: interval, timerSetting: timersIntervals[2], bowlCount: UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int, iD: "sample"),
//        TimerCell(label: "Round 1", interval: interval, timerSetting: timersIntervals[3], bowlCount: 0, iD: "Rd 1"),
//        TimerCell(label: "Round 2", interval: interval, timerSetting: timersIntervals[4], bowlCount: 0, iD: "Rd 2"),
//        TimerCell(label: "Round 3", interval: interval, timerSetting: timersIntervals[5], bowlCount: 0, iD: "Rd 3")
//        ]
//
//
//
//        alarmSound = UserDefaults.standard.object(forKey: "alarmSoundSave") as! [String : Int]

    }
}


