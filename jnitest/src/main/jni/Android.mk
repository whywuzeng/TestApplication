LOCAL_PATH := $(call my-dir)  
include $(CLEAR_VARS)  
LOCAL_MODULE    := LQRJni
LOCAL_SRC_FILES := Toast.c
include $(BUILD_SHARED_LIBRARY)  