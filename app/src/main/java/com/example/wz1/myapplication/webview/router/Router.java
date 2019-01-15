package com.example.wz1.myapplication.webview.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.example.wz1.myapplication.R;
import com.example.wz1.myapplication.webview.WebFragmentImpl;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.webview.router
 */
public class Router {
    private Router(){
    }

    private static final class RouterHolder {
        private static final Router holder =new Router();
    }

    public static Router getInstance(){
        return RouterHolder.holder;
    }

    public final  boolean HandleURL(WebFragmentImpl webFragment,String url){

        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(webFragment.getContext(), url);
            return true;
        }

        AppCompatActivity topParent = (AppCompatActivity)webFragment.getTopParent();
        WebFragmentImpl mWebFragment = WebFragmentImpl.newInstance(url);
        if (topParent != null)
        {
            FragmentManager fragmentManager = topParent.getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.container,mWebFragment,"rootFragment");
            ft.addToBackStack("rootFragmentStack");
            ft.commit();
        }else {
            try {
                FragmentManager childFragmentManager = webFragment.getChildFragmentManager();
                FragmentTransaction ft = childFragmentManager.beginTransaction();
                ft.add(R.id.container, mWebFragment, "childFragment");
                ft.addToBackStack("childFragmentStack");
                ft.commit();
            } catch (Exception e) {
                throw new NullPointerException("R.id.container is null");
            }
        }
        return true;
    }


    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebFragmentImpl fragment, String url) {
        loadPage(fragment.getWebView(), url);
    }

    private static void callPhone(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
