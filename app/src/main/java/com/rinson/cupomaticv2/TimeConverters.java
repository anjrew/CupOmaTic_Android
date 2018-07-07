package com.rinson.cupomaticv2;

public class TimeConverters {

    public static String convertSecsmmss(int timeInput) {

        int timeSecs = timeInput;
        String timeString;

        int minutes = timeSecs / 60;
        int seconds = timeSecs % 60;

        if (minutes < 10 && seconds < 10) {

            timeString = "0" + String.valueOf(minutes) + ":0" + String.valueOf(seconds);

        } else if (minutes < 10) {

            timeString = "0" + String.valueOf(minutes) + ":" + String.valueOf(seconds);

        } else if (seconds < 10) {

            timeString = String.valueOf(minutes) + ":0" + String.valueOf(seconds);

        } else {

            timeString = String.valueOf(minutes) + ":" + String.valueOf(seconds);
        }
        return timeString;
    }
}