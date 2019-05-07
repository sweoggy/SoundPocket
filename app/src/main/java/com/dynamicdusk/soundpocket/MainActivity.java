package com.dynamicdusk.soundpocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity{

    SoundPlayer soundPlayer;
    MyJavaScriptInterface jsHandler;
    Shotgun shotgun;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        setContentView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        settings.setBuiltInZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient()); //making js alerts work

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webView.setLongClickable(false);
        webView.setHapticFeedbackEnabled(false);



        this.soundPlayer = new SoundPlayer(this);
        this.jsHandler = new MyJavaScriptInterface(webView, this, soundPlayer);
        webView.addJavascriptInterface(jsHandler, "Android");
        webView.loadUrl("file:///android_asset/www/splash.html");
        final WebView webViewCallbackAccess = webView;
        shotgun = new Shotgun();
        shotgun.setSoundPlayer(soundPlayer);
        if (AccelerometerManager.isSupported(this)) {
            AccelerometerManager.startListening(shotgun);
        }
       shotgun.setSoundPlayer(soundPlayer);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        webViewCallbackAccess.loadUrl("file:///android_asset/www/index.html");
                    }
                });
            }
        }, 3000);

    }
}


