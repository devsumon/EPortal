package com.example.e_portal;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView mywebview;
    SwipeRefreshLayout swip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow(). setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        swip=(SwipeRefreshLayout)findViewById(R.id.swip);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                load();

            }
        });

        load();





    }

    public void load(){


        mywebview = (WebView)findViewById(R.id.WebViewID);
        mywebview.loadUrl("http://usbair.com/occ/m/");


        WebSettings x=mywebview.getSettings();
        x.setJavaScriptEnabled(true);
        x.setSupportZoom(true);
        x.setBuiltInZoomControls(false);
        x.setCacheMode(WebSettings.LOAD_NO_CACHE);
        x.setDomStorageEnabled(true);
        mywebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mywebview.setScrollbarFadingEnabled(true);
        mywebview.setWebViewClient(new WebViewClient(){

                                       @Override
                                       public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){

                                           mywebview.loadUrl("file:///android_asset/hello.html");

                                       }

                                       @Override
                                       public void onPageFinished(WebView view, String url) {
                                           swip.setRefreshing(false);

                                       }
                                   }


        );

    }


    @Override
    public void onBackPressed() {
        if (mywebview.canGoBack()){
            mywebview.goBack();
        }
        else {
            finish();
        }
    }
}