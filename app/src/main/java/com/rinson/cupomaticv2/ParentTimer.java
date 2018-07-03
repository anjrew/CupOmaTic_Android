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

    boolean advancedMode = false;
    boolean vibrate = false;
    int interval;
    int bowl;
    int timerAlarms ;
    int [] timersIntervals;
    //    timer timer;
    TimerCell[] timers;
    int mainTime;
    MainActivity mainActivity;
    int alarmSound;
    int alarmWarning = 1;
//    Audio audio;
//    IntervalTimer intervalTimer;

    //Start Timer
    Boolean initiateMainTimer = true;
//    CountDownTimer startTimer;

    int startTime;
    int bowlSetting;
    Boolean running = false;


    public void startStartTimer() {

        CountDownTimer startTimer = new CountDownTimer(4000, 1000) {


            @Override
            public void onTick(long millisecondsUntilDone) {
                //Code executed at every Interval
                Log.i("Seconds Left", String.valueOf(millisecondsUntilDone / 1000));

            }

            @Override
            public void onFinish() {
                //Code executed at finish
                Log.i("Done!", "Countdown Timer finished");
                mainTimer();
            }
        }.start();
    }


    public void mainTimer() {
        //Timer
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                //insert code to be run every second
                mainTime = mainTime + 1;
                Log.i("Main time:", String.valueOf(mainTime));


                //Time interval of timer
                handler.postDelayed(this, 1000);
            }

        };
        handler.post(run);
    }

    public void increaseTimeByOneSecond(){
        for (int i = 0; i < timers.length; i++) {
//            timers[i].addSecond();
        }
        mainTime =+ 1;

    }

    public void comeBackFromBreak(){

    }

    //possibly not used
    public void updatdateMainTimerTime(){
        TextView mainTimerTextview = mainActivity.findViewById(R.id.mainTimerDisplay);
        mainTimerTextview.setText(mainTime);
    }
    public void updatdateMainTimerWithStart(){
        TextView mainTimerTextview = mainActivity.findViewById(R.id.mainTimerDisplay);
        mainTimerTextview.setText(R.string.start);
    }

    public void activateRunTimer(){
        running = true;
    }

    public void deactivateRunTimer(){
        running = false;
    }

    public Boolean getRunningstaus(){
        return running;
    }

    public void switchParentTimerActiviationState(){
        if (running == true){

            running = false;

        }else{

            running = true;
        }

        Log.i("Main time status", String.valueOf(running));
    }

    public void stopMainTimer(){


    }

}



//public class ParentTimer {
//
//    boolean advancedMode = false;
//    boolean vibrate = false;
//    int interval;
//    int bowl;
//    int timerAlarms;
//    int[] timersIntervals;
//    TimerCell timer;
//    TimerCell[] timers;
//    int mainTimer;
//    MainActivity mainActivity;
//    Bowls bowls;
//    //    Var alarmSound = [String: Int]()
//    int alarmWarning = 1;
////    Audio audio;
////    IntervalTimer intervalTimer;
//
//    //Start Timer
//    Boolean initiateMainTimer = true;
//    int startTimerSetting = 4;
//    CountDownTimer startTimer;
//
//    int startTime;
//    int bowlSetting;
//    Boolean running = false;
//
//    ParentTimer(MainActivity mainactivity, Bowls bowls){
//        this.mainActivity = mainactivity;
//        this.bowls = bowls;
//
//
//    }
//
////    init(viewController : ViewController){
////        self.advancedMode = UserDefaults.standard.object(forKey: "advancedMode") as! Bool
////        self.vibrate = UserDefaults.standard.object(forKey: "vibrate") as! Bool
////        self.viewController = viewController
////        isKeyPresentInUserDefaults()
////        self.interval = UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int
////        self.bowl = UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int
////        self.startTimerSetting = 4
////        self.intervalTimer = IntervalTimer(timeSetting: UserDefaults.standard.object(forKey: "intervalSettingSave") as! Int, bowlSetting: (UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int))
////        reset()
////
////        intervalTimer?.setParentTimer(parentTimer: self)
////
////
////        timersIntervals = [
////        0,
////                UserDefaults.standard.object(forKey: "breakSettingSave")  as! Int,
////                UserDefaults.standard.object(forKey: "sampleSettingSave") as! Int,
////                UserDefaults.standard.object(forKey: "roundOneSettingSave") as! Int,
////                UserDefaults.standard.object(forKey: "roundTwoSettingSave") as! Int,
////                UserDefaults.standard.object(forKey: "roundThreeSettingSave") as! Int
////        ]
////
////
////        timers = [
////        TimerCell(label: "Pour", interval: interval, timerSetting: 0, bowlCount: UserDefaults.standard.object(forKey: "numberOfBowlsSave" ) as! Int, iD: "pour"),
////        TimerCell(label: "Break", interval: interval, timerSetting: timersIntervals[1], bowlCount: UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int, iD: "break"),
////        TimerCell(label: "Sample", interval: interval, timerSetting: timersIntervals[2], bowlCount: UserDefaults.standard.object(forKey: "numberOfBowlsSave") as! Int, iD: "sample"),
////        TimerCell(label: "Round 1", interval: interval, timerSetting: timersIntervals[3], bowlCount: 0, iD: "Rd 1"),
////        TimerCell(label: "Round 2", interval: interval, timerSetting: timersIntervals[4], bowlCount: 0, iD: "Rd 2"),
////        TimerCell(label: "Round 3", interval: interval, timerSetting: timersIntervals[5], bowlCount: 0, iD: "Rd 3")
////        ]
////
////
////
////        alarmSound = UserDefaults.standard.object(forKey: "alarmSoundSave") as! [String : Int]
//
//
//    public void startStartTimer() {
//
//
//        new CountDownTimer(3000, 1000) {
//
//            @Override
//            public void onTick(long millisecondsUntilDone) {
//                //Code executed at every Interval
//                Log.i("Seconds Left", String.valueOf(millisecondsUntilDone / 1000));
//
//            }
//
//            @Override
//            public void onFinish() {
//                //Code executed at finish
//                Log.i("Done!", "Countdown Timer finished");
//                startMainTimer();
//            }
//        }.start();
//    }
//
//
//    //Countdown Timer
//    public void startMainTimer(){
//
//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//
//                // Insert code to be run every second
//
//                Log.i("Runnable has run!", "a second must have passed...");
//
//                handler.postDelayed(this, 1000);
//
//            }
//        };
//
//        handler.post(run);
//}
//}
