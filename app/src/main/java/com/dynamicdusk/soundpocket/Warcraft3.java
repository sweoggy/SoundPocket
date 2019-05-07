package com.dynamicdusk.soundpocket;

import android.content.Context;

public class Warcraft3 implements AccelerometerListener {

    SoundPlayer soundPlayer;


    public void set(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
    }

    public void setSoundPlayer(SoundPlayer soundPlayer){
        this.soundPlayer = soundPlayer;
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        if(soundPlayer.isSoundOn()) {
            soundPlayer.playSound(-1);
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeX(float force) {
        if(soundPlayer.isSoundOn()) {
            soundPlayer.playSound(SoundPlayer.SOUND_SHOTGUN_SHOT);
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeY(float force) {
        if(soundPlayer.isSoundOn()) {
            soundPlayer.playSound(SoundPlayer.SOUND_SHOTGUN_RELOAD);
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeZ(float force) {
        if(soundPlayer.isSoundOn()) {
            soundPlayer.playSound(SoundPlayer.SOUND_PUNCH);
        }
        //jsHandler.alert("Force: " + force);
    }
}
