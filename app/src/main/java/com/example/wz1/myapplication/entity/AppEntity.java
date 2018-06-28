package com.example.wz1.myapplication.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2018-06-28.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.entity
 */

public class AppEntity {
        private Drawable appIcon;
        private String appName;
        private String packageName;
        private double memorySize;

        public Drawable getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(Drawable appIcon) {
            this.appIcon = appIcon;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public double getMemorySize() {
            return memorySize;
        }

        public void setMemorySize(double memorySize) {
            this.memorySize = memorySize;
        }
}
