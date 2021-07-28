package com.casbin.casdoor;

import android.annotation.SuppressLint;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.FragmentActivity;

import java.util.Map;

class CasdoorWebView extends WebViewClient {
    FragmentActivity activity;

    CasdoorWebView(FragmentActivity activity) {
        this.activity = activity;
    }

    @SuppressLint("SetJavaScriptEnabled")
    static void LoadLoginPage(WebView webView) {
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.supportMultipleWindows();
        webSettings.setAppCacheMaxSize(1024 * 1024 * 250);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setSavePassword(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSaveFormData(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);

        webView.loadUrl("https://door.casbin.com/login/oauth/authorize?client_id=0ba528121ea87b3eb54d&response_type=code&redirect_uri=https://oa.casbin.com/callback&scope=read&state=casdoor");
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (url.contains("code=")) {
            String paras = url.split("\\?")[1];
            String code = paras.split("&")[0].split("=")[1];
            CasdoorUserToken.SetUserToken(activity, code);
            activity.finish();
        }
    }
}
