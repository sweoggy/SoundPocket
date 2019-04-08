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


    MediaPlayer mPlayer;
    private boolean soundOn = false;
    private int chosenSound = SOUND_PEW_PEW;
    Context context;

    public SensorHandler(Context context) {
        this.context = context;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn() {
        this.soundOn = true;
    }

    public void setSoundOff() {
        this.soundOn = false;
    }

    public void setSound(int sound) {
        this.chosenSound = sound;
    }

    public void playSound(int sound) {
        if (mPlayer != null) {
            stopPlayer();
        }

        if (sound == -1) {
            mPlayer = MediaPlayer.create(context, chosenSound);
        } else {
            mPlayer = MediaPlayer.create(context, sound);
        }

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();
            }
        });
        mPlayer.start();
    }

    public void stopPlayer(){
        if(mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }
}
