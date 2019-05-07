package com.dynamicdusk.soundpocket;

import android.content.Context;

public class Warcraft3 extends SoundPlayer {
    public static final int SOUND_OFF_I_GO_THEN = R.raw.offIgothen;
    public static final int SOUND_RIGHT_O = R.raw.righto;
    public static final int SOUND_YES_MILORD = R.raw.yesmilord;
    public Warcraft3 (Context context){
        super(context);
    }

    protected void playSpecificSound (int sound){
        if (soundOn){
            switch (sound) {
                case SOUND_YES_MILORD:
                    initPlayer(SOUND_YES_MILORD);
                    mPlayer.start();
                    break;
                case SOUND_RIGHT_O:
                    initPlayer(SOUND_RIGHT_O);
                    mPlayer.start();
                    break;
                case SOUND_OFF_I_GO_THEN:
                    initPlayer(SOUND_OFF_I_GO_THEN);
                    mPlayer.start();
                    break;
                case -1:
            }

        }
    }
}
