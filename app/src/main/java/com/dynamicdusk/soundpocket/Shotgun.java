package com.dynamicdusk.soundpocket;

public class Shotgun implements AccelerometerListener {

    private int shots = 0;
    private boolean loaded;
    private boolean magazineCocked = false;
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

            if (soundPlayer.isSoundOn()) {
                if(magazineCocked &&shots>0) {
                soundPlayer.playSound(SoundPlayer.SOUND_SHOTGUN_SHOT);
                magazineCocked = false;
                shots--;
            } else{
                    soundPlayer.playSound(SoundPlayer.SOUND_DRY_FIRE);
                }
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeY(float force) {
        if (soundPlayer.isSoundOn()) {
            if (shots > 0) {
                soundPlayer.playSound(SoundPlayer.SOUND_SHOTGUN_RELOAD);
                magazineCocked = true;
            } else{
                soundPlayer.playSound(SoundPlayer.SOUND_EMPTY_PUMP);
            }
            //jsHandler.alert("Force: " + force);
        }
    }

    public void onShakeZ(float force) {
        if(soundPlayer.isSoundOn()) {
            soundPlayer.playSound(SoundPlayer.SOUND_AMMO_LOAD);
            if(shots<8) {
                shots++;
            }
        }
        //jsHandler.alert("Force: " + force);
    }
}
