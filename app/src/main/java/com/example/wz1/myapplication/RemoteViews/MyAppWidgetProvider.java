package com.example.wz1.myapplication.RemoteViews;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.wz1.myapplication.R;

/**
 * Created by Administrator on 2018-05-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.RemoteViews
 */

public class MyAppWidgetProvider extends AppWidgetProvider {

    private static final String TAG = "MyAppWidgetProvider";
    private static final String TAG_CLICK = "com.example.wz1.myapplication.RemoteViews.MyAppWidgetProvider_clickAction";

    public MyAppWidgetProvider(){
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e(TAG, "onReceive: action=="+intent.getAction() );
        if (intent.getAction().equals(TAG_CLICK))
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_avatar);

                }
            }).start();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.e(TAG,"OnUpdate");
        int length = appWidgetIds.length;
        Log.e(TAG, "onUpdate: "+length);
        for (int i=0;i<length;i++)
        {
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdata(context,appWidgetManager,appWidgetId);
        }
    }

    private void onWidgetUpdata(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget);
        Intent intent = new Intent();
        intent.setAction(TAG_CLICK);
        PendingIntent activity = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_centeriv,activity);
        appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
    }


    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }
}
