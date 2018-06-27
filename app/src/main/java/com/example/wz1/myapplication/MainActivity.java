package com.example.wz1.myapplication;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends Activity {

    private LottieAnimationView lt_pulldown_arrow;
    private LottieAnimationView lt_pulldown_refresh;
    private TextView tv_refresh_success;
    private ImageView iv_refresh_success;
    private Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lt_pulldown_arrow=(LottieAnimationView)findViewById(R.id.lt_pulldown_arrow);
        lt_pulldown_refresh=(LottieAnimationView)findViewById(R.id.lt_pulldown_refresh);
        tv_refresh_success=(TextView)findViewById(R.id.tv_refresh_success);
        iv_refresh_success=(ImageView)findViewById(R.id.iv_refresh_success);
        btn_click=(Button)findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lt_pulldown_arrow.playAnimation();
            }
        });

        lt_pulldown_arrow.playAnimation();

//        lt_pulldown_refresh.setVisibility(View.VISIBLE);
//        lt_pulldown_refresh.playAnimation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            lt_pulldown_arrow.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (!valueAnimator.isRunning())
                    {
    //                    tv_refresh_success.setVisibility(View.VISIBLE);
    //                    iv_refresh_success.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
