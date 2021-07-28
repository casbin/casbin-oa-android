package com.casbin.casdoor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CasdoorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casdoor_login);

        WebView webView = findViewById(R.id.casdoor_login_webview);
        webView.setWebViewClient(new CasdoorWebView(this));

        CasdoorWebView.LoadLoginPage(webView);
    }
}