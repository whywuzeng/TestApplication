package com.example.wz1.myapplication.fragmentTest;

import android.os.Handler;

/**
 * Created by Administrator on 2019-01-10.
 * <p>
 * by author wz
 * <p>
 * com.scoket.wz1.scokettest
 */
public class OneRunable implements Runnable {

    private android.os.Handler mHandler;

    public OneRunable(Handler handler) {
        this.mHandler=handler;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            mHandler.sendEmptyMessageAtTime(102,100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
