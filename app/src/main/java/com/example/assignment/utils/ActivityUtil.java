package com.example.assignment.utils;

import android.app.Activity;

import java.util.Stack;

/**

 *   TODO Manage instances of all activities 管理所有Activity的实例
 */

public class ActivityUtil {

    private static Stack<Activity> stack;
    private static ActivityUtil manager;

    /**
     * Get instance 获取实例
     */
    public static synchronized ActivityUtil getInstance() {
        if (manager == null) {
            manager = new ActivityUtil();
            stack = new Stack<>();
        }
        return manager;
    }

    /**
     * add Activity 添加Activity
     */
    public synchronized void addActivity(Activity activity) {
        stack.add(activity);
    }

    /**
     * remove Activity  移除Activity
     */
    public synchronized void removeActivity(Activity activity) {
        stack.remove(activity);
    }

    /**
     * Ends the activity of the specified class name 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : stack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * Ends the specified activity 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            stack.remove(activity);
        }
    }

    /**
     * check if there is an activity是否存在Activity
     */
    public boolean containsActivity(Class<?> cls) {
        for (Activity activity : stack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * end all activity 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : stack) {
            if (activity != null) {
                activity.finish();
            }
        }
        stack.clear();
    }
}
