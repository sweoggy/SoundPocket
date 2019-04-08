package com.dynamicdusk.soundpocket;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by fredricbillow on 2019-04-08.
 */

public class SensorHandler {
    //public static final int SOUND_SPRAY_PAINT_SHAKE = R.raw.spraypaintshake;
    public static final int SOUND_PEW_PEW = R.raw.pewpew;
    public static final int SOUND_PUNCH = R.raw.punch;
    public static final int SOUND_GUN_SHOT = R.raw.gunshot;


    MediaPlayer mPlayerPewPew;
    MediaPlayer mPlayerPunch;
    MediaPlayer mPlayerGunShot;
    private boolean soundOn = false;
    private int chosenSound = SOUND_PEW_PEW;
    Context context;

    public SensorHandler(Context context) {
        this.context = context;
        initPlayers();
    }

    private void initPlayers() {
        mPlayerPewPew = MediaPlayer.create(context, SOUND_PEW_PEW);
        mPlayerPunch = MediaPlayer.create(context, SOUND_PUNCH);
        mPlayerGunShot = MediaPlayer.create(context, SOUND_GUN_SHOT);


        mPlayerPewPew.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //stopPlayer();
            }
        });

        mPlayerPunch.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //stopPlayer();
            }
        });

        mPlayerGunShot.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //stopPlayer();
            }
        });
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn() {
        this.soundOn = true;
        initPlayers();
    }

    public void setSoundOff() {
        this.soundOn = false;
        stopPlayers();
    }

    public void setSound(int sound) {
        this.chosenSound = sound;
    }

    public void playSound(int sound) {
        switch (sound) {
            case SOUND_PEW_PEW:
                mPlayerPewPew.start();
                break;
            case SOUND_PUNCH:
                mPlayerPunch.start();
                break;
            case SOUND_GUN_SHOT:
                mPlayerGunShot.start();
                break;
            case -1:
                switch (chosenSound) {
                    case SOUND_PEW_PEW:
                        mPlayerPewPew.start();
                        break;
                    case SOUND_PUNCH:
                        mPlayerPunch.start();
                        break;
                    case SOUND_GUN_SHOT:
                        mPlayerGunShot.start();
                        break;
                }
                break;
        }
    }

    private void stopPlayers(){
        if(mPlayerPewPew != null){
            mPlayerPewPew.release();
            mPlayerPewPew = null;
        }

        if(mPlayerPunch != null){
            mPlayerPunch.release();
            mPlayerPunch = null;
        }

        if(mPlayerGunShot != null){
            mPlayerGunShot.release();
            mPlayerGunShot = null;
        }
    }
}
