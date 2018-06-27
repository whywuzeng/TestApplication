package com.example.wz1.myapplication.RemoteViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wz1.myapplication.R;

/**
 * Created by Administrator on 2018-05-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.RemoteViews
 */

public class AppWidgetActivity extends AppCompatActivity implements View.OnClickListener{
    private Button tv_click;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        tv_click=(Button)findViewById(R.id.tv_click);
        tv_click.setOnClickListener(this);
        tv_click.setText("点击会有AppWidget");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_click:
                break;
        }
    }
}
