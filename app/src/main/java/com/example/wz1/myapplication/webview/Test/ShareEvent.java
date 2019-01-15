package com.example.wz1.myapplication.webview.Test;

import android.widget.Toast;

import com.example.wz1.myapplication.webview.event.Event;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview.Test
 */
public class ShareEvent extends Event{
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), "分享", Toast.LENGTH_SHORT).show();
        return null;
    }
}
