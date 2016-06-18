package com.example.admin.testnetpage;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    //private WebView mainWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView mainWebView = new WebView(this);
        mainWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        if (Build.VERSION.SDK_INT >= 19) {
            mainWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        String html="<html><body>";
        mainWebView.setWebViewClient(new WebViewClient());
        mainWebView.setWebChromeClient(new WebChromeClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mainWebView.getSettings().setDomStorageEnabled(true);
       // mainWebView.loadDataWithBaseURL("http://www.baidu.com", html, "text/html", "utf-8", null);
        mainWebView.loadUrl("http://www.baidu.com");
        //mainWebView.loadUrl("http://www.baidu.com");
    }
}
//
//注意在manifest中申请上网的功能，申请后就可以了。
//   <uses-permission android:name="android.permission.INTERNET"/>
//  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
