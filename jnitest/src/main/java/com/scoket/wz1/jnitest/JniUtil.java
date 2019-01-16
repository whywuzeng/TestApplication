package com.scoket.wz1.jnitest;

public class JniUtil {

    public static String LIB_NAME = "LQRJni";

    public JniUtil() {

    }

    static {
        System.loadLibrary(LIB_NAME);
    }

    public static native String hello();
}
