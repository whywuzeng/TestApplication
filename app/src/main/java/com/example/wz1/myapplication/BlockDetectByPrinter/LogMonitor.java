package com.example.wz1.myapplication.BlockDetectByPrinter;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Administrator on 2019-01-11.
 * <p>
 * by author wz
 * 监控延时超过1000ms 的任务。部门主线程的任务
 * <p>
 * com.scoket.wz1.scokettest.BlockDetectByPrinter
 */
public class LogMonitor {

    private static final String TAG = "LogMonitor";

    //单利
    private static LogMonitor mIntance=new LogMonitor();
    //handleThread 开线程处理
    private HandlerThread handlerThread= new HandlerThread("LogMonitor");
    //handle
    private Handler ioHandler;

    public static final int CLOCK_TIME = 1000;

    private  LogMonitor(){
        //启动 thread
        handlerThread.start();
        //绑定handlethread 到handle
        ioHandler=new Handler(handlerThread.getLooper());
    }

    //一个runable方法，延时任务 最好是静态的
    //打印stackTrace 信息
    private static Runnable runnable=new Runnable() {
        @Override
        public void run() {
            StringBuilder stringBuilder = new StringBuilder();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                 stringBuilder.append(stackTraceElement.toString()+"\n");
            }
            Log.e(TAG, "run: "+stringBuilder.toString());
        }
    };

    //保证单一对象 全局停止，开始

    public static LogMonitor getInstance(){
        return mIntance;
    }

    public void startMonitor(){
        ioHandler.postDelayed(runnable,CLOCK_TIME);
    }

    public void removeMonitor(){
        ioHandler.removeCallbacks(runnable);
    }

    public void isMonitor(){
    }
}
