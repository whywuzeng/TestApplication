package com.example.wz1.myapplication.webview;

import android.graphics.Bitmap;
import android.util.Log;
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
public class WebViewClientImpl extends WebViewClient{

    private static final String TAG = "WebViewClientImpl";
    
    private IPageLoadListener mIPageLoadListener;
    private final WebFragment mWebFragment;

    public WebViewClientImpl(WebFragment mWebFragment) {
        this.mWebFragment = mWebFragment;
    }

    public void setIPageLoadListener(IPageLoadListener mIPageLoadListener) {
        this.mIPageLoadListener = mIPageLoadListener;
    }



    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e(TAG, "shouldOverrideUrlLoading: "+url );
        return Router.getInstance().HandleURL((WebFragmentImpl)mWebFragment,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener!=null)
        {
            mIPageLoadListener.onPageLoadStart();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener!=null)
        {
            mIPageLoadListener.onPageLoadEnd();
        }
    }
}
