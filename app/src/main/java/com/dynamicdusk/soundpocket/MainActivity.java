package com.dynamicdusk.soundpocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements AccelerometerListener {

    SensorHandler sensorHandler;
    MyJavaScriptInterface jsHandler;
    public static final int SOUND_PEW_PEW = R.raw.pewpew;
    public static final int SOUND_PUNCH = R.raw.punch;
    public static final int SOUND_GUN_SHOT = R.raw.gunshot;

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

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webView.setLongClickable(false);
        webView.setHapticFeedbackEnabled(false);



        this.sensorHandler = new SensorHandler(this);
        this.jsHandler = new MyJavaScriptInterface(webView, this, sensorHandler);
        webView.addJavascriptInterface(jsHandler, "Android");
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
        if(sensorHandler.isSoundOn()) {
            sensorHandler.playSound(-1);
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeX(float force) {
        if(sensorHandler.isSoundOn()) {
            sensorHandler.playSound(SOUND_PEW_PEW);
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeY(float force) {
        if(sensorHandler.isSoundOn()) {
            sensorHandler.playSound(SOUND_GUN_SHOT);
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeZ(float force) {
        if(sensorHandler.isSoundOn()) {
            sensorHandler.playSound(SOUND_PUNCH);
        }
        //jsHandler.alert("Force: " + force);
    }
}


