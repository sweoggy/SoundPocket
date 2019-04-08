package com.dynamicdusk.soundpocket;

/**
 * Created by fredricbillow on 2019-04-08.
 */

public class SensorHandler {
    private boolean soundOn = false;

    public SensorHandler() {

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

}
