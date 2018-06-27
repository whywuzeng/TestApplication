package com.example.wz1.myapplication.windowManager;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;

import com.example.wz1.myapplication.R;

/**
 * Created by Administrator on 2018-06-11.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.windowManager
 */

public class WindowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_activity);

        WindowManager systemService = (WindowManager)getSystemService(WINDOW_SERVICE);

        Button button = new Button(this);
        button.setText("button");

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0, PixelFormat.TRANSPARENT);
        layoutParams.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        layoutParams.gravity= Gravity.LEFT|Gravity.TOP;
        layoutParams.x=100;
        layoutParams.y=300;

        systemService.addView(button,layoutParams);
    }
}
