package com.example.wz1.myapplication.webview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wz1.myapplication.webview.router.Router;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview
 */
public class WebFragmentImpl extends WebFragment {

    public void setIPageLoadListener(IPageLoadListener mIPageLoadListener) {
        this.mIPageLoadListener = mIPageLoadListener;
    }

    private IPageLoadListener mIPageLoadListener =null;

    public static WebFragmentImpl newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(URLKEY,url);
        WebFragmentImpl fragment = new WebFragmentImpl();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IWebViewInit setIWebInit() {
        return this;
    }

    /**
     * webview的属性设定类
     * @param mWebView
     * @return
     */
    @Override
    public WebView initWebView(WebView mWebView) {
      return new WebViewInit().createWebView(mWebView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webViewClient.setIPageLoadListener(mIPageLoadListener);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {

        return new WebChromeClientImpl();
    }

    @Override
    protected Object setLayout() {

        return getWebView();
    }

    @Override
    protected void onBindView(View rootView, Bundle savedInstanceState) {
        if (getmUrl()!=null)
        {
            //用原生方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this,getmUrl());
        }
    }
}
