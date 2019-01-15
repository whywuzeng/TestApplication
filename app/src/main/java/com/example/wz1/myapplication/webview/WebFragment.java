package com.example.wz1.myapplication.webview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.wz1.myapplication.fragmentTest.BaseFragment;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview
 */
public abstract class WebFragment  extends BaseFragment implements IWebViewInit{

    private ReferenceQueue<WebView> WEB_VIEW_QUEUE =new ReferenceQueue<>();
    public static final String URLKEY="urlkey";
    private String mUrl;
    private WebView mWebView =null;
    private boolean mIsWebViewAvailable;

    public Activity getTopParent() {
        return topParent;
    }

    public void setTopParent(Activity topParent) {
        this.topParent = topParent;
    }

    private Activity topParent;

    public WebFragment(){

    }

    protected abstract IWebViewInit setIWebInit();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mUrl=arguments.getString(URLKEY,null);
        initWebView();
    }

    private void initWebView() {
    //      在oncreate 方法里    第一次进入 判断webview 是否有值
        if (this.mWebView !=null)
        {
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
        }else {
            //weakrefrence 和 referencequeue 结合使用 生成webview
            IWebViewInit iWebViewInit = setIWebInit();
            if (iWebViewInit!=null) {
                WeakReference<WebView> weakReference =new WeakReference<>(new WebView(getContext()),WEB_VIEW_QUEUE);
                this.mWebView = weakReference.get();
                //webview 初始化 三大方法  初始化WebView
                this.mWebView=iWebViewInit.initWebView(this.mWebView);
                //初始化 webViewClient
                this.mWebView.setWebViewClient(iWebViewInit.initWebViewClient());
                //初始化 webChromeClient
                this.mWebView.setWebChromeClient(iWebViewInit.initWebChromeClient());
                //addJavascriptInterface 加方法JS方法
                this.mWebView.addJavascriptInterface(BhomeWebInterface.create(this),"bhome");

                mIsWebViewAvailable=true;
            }else {
                throw new NullPointerException(" iWebViewInit is NULL ,error init");
            }
        }
    }

    public String getmUrl() {
        if (mUrl ==null)
        {
            throw new NullPointerException(" mUrl is NULL Error");
        }
        return mUrl;
    }

    /**
     * 各个生命周期的处理
     */

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null)
        {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView!=null)
        {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView!=null)
        {
            mWebView.destroy();
        }
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * Gets the WebView.
     */
    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }
}
