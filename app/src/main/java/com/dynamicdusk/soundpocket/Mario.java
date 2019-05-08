package com.dynamicdusk.soundpocket;

import java.util.Calendar;

public class Mario implements AccelerometerListener {
    private int points =0;
    private int hits = 0;
    private long timeStamp = 0;
    private boolean levelUp = false;
    private SoundPlayer soundPlayer;
    public Mario(){
        timeStamp = Calendar.getInstance().getTimeInMillis();
    }

    public void setSoundPlayer(SoundPlayer soundPlayer){
        this.soundPlayer = soundPlayer;
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        if(soundPlayer.isSoundOn()&& (Calendar.getInstance().getTimeInMillis() - timeStamp) > 500) {
            soundPlayer.playSound(SoundPlayer.SOUND_PIPE);
            timeStamp = Calendar.getInstance().getTimeInMillis();
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeX(float force) {
        if(soundPlayer.isSoundOn()&& (Calendar.getInstance().getTimeInMillis() - timeStamp) > 500) {
            soundPlayer.playSound(SoundPlayer.SOUND_FIREBALL);
            timeStamp = Calendar.getInstance().getTimeInMillis();
            hits++;
            if(levelUp && hits >10){
                soundPlayer.playSound(SoundPlayer.SOUND_STAGE_WON);
                levelUp = false;
            }
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeY(float force) {
        if(soundPlayer.isSoundOn()&& (Calendar.getInstance().getTimeInMillis() - timeStamp) > 500) {
            soundPlayer.playSound(SoundPlayer.SOUND_COIN);
            timeStamp = Calendar.getInstance().getTimeInMillis();
            points++;
            if(points>10){
                soundPlayer.playSound(SoundPlayer.SOUND_POWER_UP);
                levelUp =true;
                points = 5;

            }
        }
        //jsHandler.alert("Force: " + force);
    }

    public void onShakeZ(float force) {
        if(soundPlayer.isSoundOn()&& (Calendar.getInstance().getTimeInMillis() - timeStamp) > 500) {
            soundPlayer.playSound(SoundPlayer.SOUND_PIPE);
            timeStamp = Calendar.getInstance().getTimeInMillis();
        }
        //jsHandler.alert("Force: " + force);
    }
}
