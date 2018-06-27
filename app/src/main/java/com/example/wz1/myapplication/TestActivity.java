package com.example.wz1.myapplication;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018-05-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    
    private static final String TAG = "TestActivity";

    private Button button1;

    private static final int TAG_BUTTON_MOVING=0x454545;

    private static final int DELAY_TIME=15;

    private int TIME_COUNT=15;

    private int COUNT=1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==TAG_BUTTON_MOVING)
            {
                COUNT++;
                if (button1!=null&&COUNT<TIME_COUNT)
                {  float fraction=  (float) COUNT/TIME_COUNT;
                    int ScrollX = (int) (fraction*100);

                    button1.scrollTo(ScrollX,0);
                    handler.sendEmptyMessageDelayed(TAG_BUTTON_MOVING,DELAY_TIME);
                }
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Log.e(TAG, "button1.left=" + button1.getLeft());
            Log.e(TAG, "button1.x=" + button1.getX());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        button1= (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1:
                handler.sendEmptyMessageDelayed(TAG_BUTTON_MOVING,DELAY_TIME);

                break;
        }
    }
}
