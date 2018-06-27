package com.example.wz1.myapplication.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2018-05-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.button
 */

@SuppressLint("AppCompatCustomView")
public class MovingTextview extends TextView {
    
    private static final String TAG = "MovingTextview";

    private int lastX;
    private int lastY;
    private int scaledTouchSlop;

    public MovingTextview(Context context) {
        this(context, null);
    }

    public MovingTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.e(TAG,"scaledTouchSlop :="+scaledTouchSlop);
    }

    public MovingTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float rawX = event.getRawX();
        float rawY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                break;

            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:
                float v3 = rawX - lastX;
                float v2 = rawY - lastY;

                float v = ViewHelper.getTranslationX(this) + v3;
                float v1 = ViewHelper.getTranslationY(this) + v2;
                ViewHelper.setTranslationX(this, v);
                ViewHelper.setTranslationY(this, v1);
                break;
        }

        lastX = (int) rawX;
        lastY = (int) rawY;
        return true;
    }
}
