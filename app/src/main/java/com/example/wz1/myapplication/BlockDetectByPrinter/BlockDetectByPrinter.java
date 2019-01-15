package com.example.wz1.myapplication.BlockDetectByPrinter;

import android.os.Looper;
import android.util.Printer;

/**
 * Created by Administrator on 2019-01-11.
 * <p>
 * by author wz
 * 监控延时超过1000ms 的任务。部门主线程的任务
 * <p>
 * com.scoket.wz1.scokettest.BlockDetectByPrinter
 */
public class BlockDetectByPrinter {
    public static void start() {

        Looper.getMainLooper().setMessageLogging(new Printer() {

            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";

            @Override
            public void println(String x) {
                if (x.startsWith(START)) {
                    //开始执行handle任务
                    LogMonitor.getInstance().startMonitor();
                }
                //handle任务结束打印
                if (x.startsWith(END)) {
                    LogMonitor.getInstance().removeMonitor();
                }
            }
        });

    }
}
