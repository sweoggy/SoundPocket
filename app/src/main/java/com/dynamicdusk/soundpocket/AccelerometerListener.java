package com.dynamicdusk.soundpocket;

public interface AccelerometerListener {

        public void onAccelerationChanged(float x, float y, float z);

        public void onShake(float force);

    public void onShakeX(float force);

    public void onShakeY(float force);

    public void onShakeZ(float force);
    }

