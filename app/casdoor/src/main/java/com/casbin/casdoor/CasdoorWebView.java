package com.casbin.casdoor;

import android.annotation.SuppressLint;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.FragmentActivity;

import java.net.MalformedURLException;

class CasdoorWebView extends WebViewClient {
    FragmentActivity activity;

    CasdoorWebView(FragmentActivity activity) {
        this.activity = activity;
    }

    @SuppressLint("SetJavaScriptEnabled")
    static void LoadLoginPage(FragmentActivity activity, WebView webView) throws MalformedURLException {
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

        CasdoorConfig casdoorConfig = CasdoorConfig.GetConfig(activity);
        String url = casdoorConfig.Endpoint + "/login/oauth/authorize?" +
                "client_id=" + casdoorConfig.ClientID +
                "&response_type=code" +
                "&redirect_uri=" + CasdoorConfig.REDIRECTURI +
                "&scope=read&state=casdoor";

        webView.loadUrl(url);
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (url.contains("code=")) {
            CookieManager cookieManager = CookieManager.getInstance();
            String cookieStr = cookieManager.getCookie(url);
            CasdoorBackend.SetSession(activity, cookieStr);
            CasdoorBackend.SetCookie(activity, cookieStr);

            String paras = url.split("\\?")[1];
            // get the code from url
            String code = paras.split("&")[0].split("=")[1];

            // exchange code for token
            try {
                CasdoorBackend.SetAccessToken(activity, code);
                CasdoorBackend.SetUsers(activity);
                CasdoorBackend.SetUser(activity, "test");

                CasdoorAuth.setLogin(activity, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            activity.finish();
        }
    }
}
