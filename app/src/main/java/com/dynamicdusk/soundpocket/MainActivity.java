package com.dynamicdusk.soundpocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.*;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements AccelerometerListener {

    SensorHandler sensorHandler;

    protected void onCreate(Bundle savedInstanceState) {

        if (AccelerometerManager.isSupported(this)) {
            AccelerometerManager.startListening(this);
        }
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        setContentView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        settings.setBuiltInZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient()); //making js alerts work
        this.sensorHandler = new SensorHandler(this);
        webView.addJavascriptInterface(new MyJavaScriptInterface(webView, this, sensorHandler), "Android");
        webView.loadUrl("file:///android_asset/www/splash.html");
        final WebView webViewCallbackAccess = webView;
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

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        sensorHandler.playSound(SensorHandler.SOUND_PUNCH);
    }
}


