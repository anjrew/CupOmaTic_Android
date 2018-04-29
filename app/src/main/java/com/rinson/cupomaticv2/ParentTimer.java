//package com.rinson.cupomaticv2;
//
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.util.Log;
//import android.os.CountDownTimer;
//import android.os.Handler;
//
//import com.rinson.cupomaticv2.MainActivity;
//import com.rinson.cupomaticv2.R;
//
//import java.util.Timer;
//
//public class ParentTimer {
//
//    boolean advancedMode = false;
//    boolean vibrate = false;
//    int interval;
//    int bowl;
//    int timerAlarms ;
//    int [] timersIntervals;
//    timer timer;
//    TimerCell[] timers;
//    int mainTimer;
//    MainActivity mainActivity;
//    Var alarmSound = [String: Int]()
//    int alarmWarning = 1;
//    Audio audio;
//    IntervalTimer intervalTimer;
//
//    //Start Timer
//    Boolean initiateMainTimer = true;
//    int startTimerSetting = 4;
//    CountDownTimer startTimer;
//
//    int startTime;
//    int bowlSetting;
//    Boolean running = false
//
//    init(viewController : ViewController){
//        self.advancedMode = UserDefaults.standard.object(forKey: "advancedMode") as! Bool
//        self.vibrate = UserDefaults.standard.object(forKey: "vibrate") as! Bool
//        self.viewController = viewController
//        isKeyPresentInUserDefaults()
//        self.interval = UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int
//        self.bowl = UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int
//        self.startTimerSetting = 4
//        self.intervalTimer = IntervalTimer(timeSetting: UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int, bowlSetting: (UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int))
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
//
//    }
//
//
//
//    startTimer(3000,1000){
//
//        @Override
//        public void onTick(long millisecondsUntilDone) {
//            //Code executed at every Interval
//            Log.i("Seconds Left", String.valueOf(millisecondsUntilDone/1000));
//
//        }
//
//        @Override
//        public void onFinish() {
//            //Code executed at finish
//            Log.i("Done!","Countdown Timer finished");
//
//        }
//    }.start();
//
//
//
//    //Timer
//    final Handler handler = new Handler();
//    Runnable run = new Runnable() {
//        @Override
//        public void run() {
//            //insert code to be run every second
//
//            Log.i("The runnable", "has been run");
//
//            handler.postDelayed(this,1000);
//        }
//
//    };
//        handler.post(run);
//                }
//                }
