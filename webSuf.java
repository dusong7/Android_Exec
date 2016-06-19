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
         getSupportActionBar().hide(); //全屏浏览
        setContentView(R.layout.activity_main);
        WebView mainWebView = new WebView(this);
        mainWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //显示同手机分辨率网页
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

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
        //访问assets文件夹
        //mainWebView.loadUrl("file:///android_asset/image.html");
    }
}
//
//注意在manifest中申请上网的功能，申请后就可以了。
//   <uses-permission android:name="android.permission.INTERNET"/>
//  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
//在Android Studio中添加assets目录，目录的位置在
//[java] view plain copy 在CODE上查看代码片派生到我的代码片
//XXX\src\main\assets  
//XXX代表你的项目的路径，assets放在src\main目录下。

//这个位置可以通过XXX.iml  XXX代表自己的项目名，其中有设置assets的目录，设置如下
//[java] view plain copy 在CODE上查看代码片派生到我的代码片
//<option name="ASSETS_FOLDER_RELATIVE_PATH" value="/src/main/assets" />  
