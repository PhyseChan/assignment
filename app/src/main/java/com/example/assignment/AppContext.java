package com.example.assignment;

import android.app.Application;

public class AppContext extends Application {
    /**
     * Singleton Pattern(单例模式)
     */
    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
