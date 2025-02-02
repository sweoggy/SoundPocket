package com.dynamicdusk.soundpocket;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;
import java.util.List;
public class AccelerometerManager {

    private static Context context = null;

    private static float xThreshold = 12.0f;
    private static float yThreshold = 4;
    private static float negyThreshold = -4;
    private static float zThreshold = 12.0f;
    private static int interval = 200;

    private static AccelerometerListener listener;
    private static Sensor sensor;
    private static SensorManager acceleratorManager;
    private static SensorManager orientationManager;
    private static Boolean supported;
    private static boolean running = false;


    public static void stopListening() {
        running = false;
        try {
            if (acceleratorManager != null && sensorEventListener != null) {
                acceleratorManager.unregisterListener(sensorEventListener);
            }
        } catch (Exception e) {
        }
    }

    public static boolean isSupported(Context cntxt) {
        context = cntxt;
        if (supported == null) {
            if (context != null) {

                acceleratorManager = (SensorManager) context.
                        getSystemService(Context.SENSOR_SERVICE);


// Get all sensors in device

                List<Sensor> sensors = acceleratorManager.getSensorList(
                        Sensor.TYPE_ACCELEROMETER);

                supported = new Boolean(sensors.size() > 0);
            } else {
                supported = Boolean.FALSE;
            }
        }
        return supported;
    }

    public static void configure(int threshold, int interval) {
        AccelerometerManager.xThreshold = threshold;
        AccelerometerManager.interval = interval;
    }

    /**
     * Registers a listener and start listening
     *
     * @param accelerometerListener callback for accelerometer events
     */


    public static void startListening(AccelerometerListener accelerometerListener) {

        acceleratorManager = (SensorManager) context.
                getSystemService(Context.SENSOR_SERVICE);

// Take all sensors in device
        List<Sensor> sensors = acceleratorManager.getSensorList(
                Sensor.TYPE_ACCELEROMETER);

        if (sensors.size() > 0) {

            sensor = sensors.get(0);

// Register Accelerometer Listener
            running = acceleratorManager.registerListener(
                    sensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_GAME);

            listener = accelerometerListener;
        }
    }

    /**
     * Configures threshold and interval
     * And registers a listener and start listening
     *
     * @param accelerometerListener callback for accelerometer events
     * @param threshold minimum acceleration variation for considering shaking
     * @param interval minimum interval between to shake events
     */
    public static void startListening(AccelerometerListener accelerometerListener, int threshold, int interval) {
        configure(threshold, interval);
        startListening(accelerometerListener);
    }

    private static SensorEventListener sensorEventListener = new SensorEventListener() {

        private long now = 0;
        private long timeDiff = 0;
        private long lastUpdate = 0;
        private long lastShake = 0;

        private float x = 0;
        private float y = 0;
        private float z = 0;
        private float lastX = 0;
        private float lastY = 0;
        private float lastZ = 0;
        private float force = 0;
        private float forceX = 0;
        private float forceY = 0;
        private float forceZ = 0;

        private float roll = 0;

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
// use the event timestamp as reference
// so the manager precision won't depends
// on the AccelerometerListener implementation
// processing time
            now = event.timestamp;

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

// if not interesting in shake events
// just remove the whole if then else block
            if (lastUpdate == 0) {
                lastUpdate = now;
                lastShake = now;
                lastX = x;
                lastY = y;
                lastZ = z;


            } else {
                timeDiff = now - lastUpdate;

                if (timeDiff > 0) {
                    forceX = Math.abs(x - lastX );
                    forceY = (y - lastY );
                    forceZ = Math.abs(z - lastZ );

                    if (Float.compare(forceX, xThreshold) > 0) {

                        if (now - lastShake >= interval) {
                            listener.onShakeX(force);
                        }
                        lastShake = now;
                    }
                    if (Float.compare(forceY, yThreshold) > 0) {

                        if (now - lastShake >= interval) {
                            listener.onShakeY(force);
                        }
                        lastShake = now;
                    }
                   /* else if (Float.compare(forceY,negyThreshold) <0) {
                        if (now - lastShake >= interval) {
                            listener.onShake(force);
                        }
                        lastShake = now;
                    }
*/

                    if (Float.compare(forceZ, zThreshold) > 0) {

                        if (now - lastShake >= interval) {
                            listener.onShakeZ(force);
                        }
                        lastShake = now;
                    }

                    //force = Math.abs(x + y + z - lastX - lastY - lastZ);

                    //if (Float.compare(force, threshold) > 0) {

                      //  if (now - lastShake >= interval) {
// trigger shake event
                          //  listener.onShake(force);
                        //} else {
                            //Toast.makeText(context, "No Motion detected",
                              //      Toast.LENGTH_SHORT).show();

                        //}
                        //lastShake = now;
                   // }
                    lastX = x;
                    lastY = y;
                    lastZ = z;
                    lastUpdate = now;
                }
            }
// trigger change event
            listener.onAccelerationChanged(x, y, z);
        }
    };
}


