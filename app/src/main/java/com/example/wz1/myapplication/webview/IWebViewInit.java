package com.example.wz1.myapplication.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview
 */
public interface IWebViewInit {
    WebView initWebView(WebView mWebView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();
}
