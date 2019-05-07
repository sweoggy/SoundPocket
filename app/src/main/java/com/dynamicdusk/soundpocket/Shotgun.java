package com.dynamicdusk.soundpocket;

public class Shotgun implements AccelerometerListener {

    private int shots;
    private boolean loaded;
    private boolean magaziePulledBack;
    private boolean magazinePulledForward;
    private SoundPlayer soundPlayer;
    private int intervall;
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
