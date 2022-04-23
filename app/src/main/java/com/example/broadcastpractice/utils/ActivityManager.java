package com.example.broadcastpractice.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {

    public static List<Activity> mActivityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        mActivityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        mActivityList.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : mActivityList) {
            if (!activity.isFinishing())
                activity.finish();
        }
    }

}
