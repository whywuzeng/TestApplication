//
// Created by Administrator on 2019-01-16.
//
#include <jni.h>
#include "com_example_wz1_myapplication_tinker_JniUtil.h"
JNIEXPORT jstring JNICALL Java_com_example_wz1_myapplication_tinker_JniUtil_hello
        (JNIEnv * env, jclass object){
    return  (*env)->NewStringUTF(env,"hello 就是干 bugly");
}
