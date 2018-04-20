package com.verenitymc.xutil.util;

/**
 * Created by Mat
 *
 * This class is just some base utils to convert units
 * of time to something more presentable.
 */
public class TimeFormatter {


    public static String convertMinutesToHourMinutesColonFormat(int minutes) //This works for seconds too.
    {
        int hour = minutes/60;
        minutes = minutes - hour * 60;
        String newTime = String.format("%02d:%02d", hour, minutes);
        return newTime;
    }


    public static String convertMinutesToHourMinutesWordFormat(int minutes)
    {
        int hour = minutes/60;
        minutes = minutes - hour * 60;
        String hourString = hour == 1 ? "Hour" : "Hours";
        String minutesString = minutes == 1 ? "Minute" : "Minutes";
        return hour + " " + hourString + " and " + minutes + " " + minutesString;
    }

    public static String convertsSecondsToHourMinutesColonFormat(int totalSeconds)
    {
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hour = totalSeconds / (60 * 60);
        String newTime = String.format("%02d:%02d:%02d", hour, minutes, seconds);
        return newTime;
    }



    public static String convertSecondsToHourMinutesWordFormnat(int totalSeconds)
    {
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hour = totalSeconds / (60 * 60);
        String hourString = hour == 1 ? "Hour" : "Hours";
        String minutesString = minutes == 1 ? "Minute" : "Minutes";
        String secondString = seconds == 1 ? "Second" : "Seconds";
        return hour + " " + hourString + ", " + minutes + " " + minutesString + " and " + seconds + " " + secondString;
    }


}
