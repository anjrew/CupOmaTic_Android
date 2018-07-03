package com.rinson.cupomaticv2;



import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class ParentTimer {

    ParentTimer(){
//        this.advancedMode = UserDefaults.standard.object(forKey: "advancedMode") as! Bool
//        this.vibrate = UserDefaults.standard.object(forKey: "vibrate") as! Bool
//        this.viewController = viewController
//        this.interval = UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int
//        this.bowl = UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int
//        this.startTimerSetting = 4
//        this.intervalTimer = IntervalTimer(timeSetting: UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int, bowlSetting: (UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int))
//        reset()
//
//        intervalTimer?.setParentTimer(parentTimer: self)
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
    public static Handler handler = new Handler();
    boolean advancedMode = false;
    boolean vibrate = false;
    int interval;
    int bowl;
    int timerAlarms ;
    int [] timersIntervals;
    //    timer timer;
    public static TimerCell[] timers;
    public static int mainTime;
    MainActivity mainActivity;
    int alarmSound;
    int alarmWarning = 1;
    public static int startDisplayTime = 2;
//    Audio audio;
//    IntervalTimer intervalTimer;

    //Start Timer
    Boolean initiateMainTimer = true;
//    CountDownTimer startTimer;
    Boolean timerActive = false;
    int startTime;
    int bowlSetting;
    public static Boolean running = false;


    public static void startStartTimer() {

        CountDownTimer startTimer = new CountDownTimer(7000, 1000) {


            @Override
            public void onTick(long millisecondsUntilDone) {

                if( ((millisecondsUntilDone/1000) > startDisplayTime)) {
                    //Code executed at every Interval
                    Log.i("Seconds Left", String.valueOf((millisecondsUntilDone / 1000 - startDisplayTime)));
                    MainActivity.updatdateMainTimerDisplay(String.valueOf((millisecondsUntilDone / 1000 - startDisplayTime)));
                }else{
                    MainActivity.updatdateMainTimerDisplay("Start");
                }

            }

            @Override
            public void onFinish() {
                //Code executed at finish
                Log.i("Done!", "Countdown Timer finished");
                mainTimer();
                MainActivity.updatdateMainTimerDisplay("Start");
            }
        }.start();
    }


    public static void mainTimer() {
        //Timer
        Runnable run = new Runnable() {
            @Override
            public void run() {

                //insert code to be run every second
//                increaseTimeByOneSecond();
                mainTime ++ ;
                MainActivity.updatdateMainTimerDisplay(String.valueOf(mainTime));
                Log.i("Main time:", String.valueOf(mainTime));


                //Time interval of timer
                handler.postDelayed(this, 1000);
            }

        };
        handler.post(run);
    }

    public static void increaseTimeByOneSecond(){
        for (int i = 0; i < timers.length; i++) {
//            timers[i].addSecond();
        }
        mainTime =+ 1;

    }

    public void comeBackFromBreak(){

    }


    public void activateRunTimer(){
        running = true;
    }

    public void deactivateRunTimer(){
        running = false;
    }

    public static int getMainTimeInterger (){
        return mainTime;
    }

    public Boolean getRunningstaus(){
        return running;
    }

    public static void switchParentTimerActiviationState(){
        if (running == true){

            running = false;

        }else{

            running = true;
        }

        Log.i("Main time status", String.valueOf(running));
    }

    
}



