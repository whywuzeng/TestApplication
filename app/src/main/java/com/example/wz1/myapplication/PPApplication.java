package com.example.wz1.myapplication;

import android.app.Application;

import com.example.wz1.myapplication.BlockDetectByPrinter.BlockDetectByPrinter;

/**
 * Created by Administrator on 2019-01-11.
 * <p>
 * by author wz
 * <p>
 * com.scoket.wz1.scokettest
 */
public class PPApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BlockDetectByPrinter.start();
    }
}
