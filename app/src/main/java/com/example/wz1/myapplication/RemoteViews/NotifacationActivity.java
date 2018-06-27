package com.example.wz1.myapplication.RemoteViews;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.wz1.myapplication.R;
import com.example.wz1.myapplication.Test3Activity;

/**
 * Created by Administrator on 2018-05-27.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.RemoteViews
 */

public class NotifacationActivity extends AppCompatActivity implements View.OnClickListener{
    private Button tv_click;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        tv_click=(Button)findViewById(R.id.tv_click);
        tv_click.setOnClickListener(this);
    }

    int Id;

    @Override
    public void onClick(View v) {
        Id++;
        switch (v.getId())
        {
            case R.id.tv_click:
                Notification notification = new Notification();
                notification.icon=R.drawable.ic_avatar;
                notification.tickerText="Hello world tickerText";
                notification.when=System.currentTimeMillis();
                notification.flags=Notification.FLAG_AUTO_CANCEL;
                Intent intent = new Intent(this, Test3Activity.class);
                PendingIntent activity = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                //Flags为PendingIntent.FLAG_CANCEL_CURRENT，则只有最后一次PendingIntent有效，之前的都无效了。
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_remoteview);
                remoteViews.setTextViewText(R.id.id_count,"id_count"+ Id);
                remoteViews.setOnClickPendingIntent(R.id.id_count,activity);
                notification.contentView=remoteViews;
                notification.contentIntent=activity;
                NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                systemService.notify(Id,notification);
                break;
        }
    }
}
