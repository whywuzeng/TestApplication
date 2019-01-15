package com.example.wz1.myapplication.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.wz1.myapplication.R;
import com.example.wz1.myapplication.systembar.BaseActivity;
import com.example.wz1.myapplication.webview.Test.ShareEvent;
import com.example.wz1.myapplication.webview.Test.TestEvent;
import com.example.wz1.myapplication.webview.event.EventManager;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview
 */
public class WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.getInstance().addEvent("test",new TestEvent());
        EventManager.getInstance().addEvent("share",new ShareEvent());
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag("webfragment1")==null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            WebFragmentImpl webFragment = WebFragmentImpl.newInstance("index2.html");
            webFragment.setTopParent(this);
            ft.add(R.id.container, webFragment, "webfragment1");
            ft.addToBackStack("webfragment1stack");
            ft.commit();
        }
    }

    @Override
    protected int parentContentView() {
        return R.layout.activity_webview;
    }
}
