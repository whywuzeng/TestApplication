package com.example.wz1.myapplication.webview;

import android.webkit.JavascriptInterface;

import com.example.wz1.myapplication.webview.event.Event;
import com.example.wz1.myapplication.webview.event.EventManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview
 */
public class BhomeWebInterface {
    private final WebFragment DELEGATE;

    private BhomeWebInterface(WebFragment delegate) {
        this.DELEGATE = delegate;
    }

    static BhomeWebInterface create(WebFragment delegate) {
        return new BhomeWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        try {
            JSONObject jsonObject = new JSONObject(params);
            final String action =  jsonObject.getString("action");
            final Event event = EventManager.getInstance().createEvent(action);
            if (event != null) {
                event.setAction(action);
                event.setContext(DELEGATE.getContext());
                event.setDelegate(DELEGATE);
                event.setUrl(DELEGATE.getmUrl());
                return event.execute(params);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
