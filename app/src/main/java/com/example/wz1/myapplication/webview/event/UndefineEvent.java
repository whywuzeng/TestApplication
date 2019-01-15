package com.example.wz1.myapplication.webview.event;

import android.util.Log;

/**
 * @author: wuchao
 * @date: 2017/11/29 22:44
 * @desciption:
 */

public class UndefineEvent extends Event {
    private static final String TAG = "UndefineEvent";
    @Override
    public String execute(String params) {
        Log.e(TAG, "execute: " );
        return null;
    }
}
