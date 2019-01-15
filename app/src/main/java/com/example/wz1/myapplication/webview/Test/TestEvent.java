package com.example.wz1.myapplication.webview.Test;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.wz1.myapplication.webview.event.Event;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview.Test
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall()", null);
                }
            });
        }
        return null;
    }
}
