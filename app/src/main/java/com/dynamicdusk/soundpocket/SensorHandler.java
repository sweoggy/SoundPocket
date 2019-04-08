package com.dynamicdusk.soundpocket;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by fredricbillow on 2019-04-08.
 */

public class SensorHandler {
    public static final int SOUND_SPRAY_PAINT_SHAKE = R.raw.spraypaintshake;
    public static final int SOUND_PUNCH = R.raw.punch;


    MediaPlayer mPlayer;
    private boolean soundOn = false;
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

    public void playSound(int sound) {
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(context, sound);
        }

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("sdfgsfdg.fdsg-dsfgdf-g-dfsg-dfg");
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
