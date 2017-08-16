package com.ck_telecom.d22400.sensorstudy.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class GsensorService extends Service {

    public static final String TAG = "G-Srv";
    private SensorManager mSensorManager;
    private Sensor mGSensor;
    private long lastUpdateTime = 0;//上次检测时间

    public GsensorService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mGSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mGSensor != null) {
            mSensorManager.registerListener(mSensorEventListener, mGSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(mSensorEventListener);
    }

    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            long currentUpdateTime = System.currentTimeMillis();
            // 两次检测的时间间隔
            long timeInterval = currentUpdateTime - lastUpdateTime;
            if (timeInterval == 0) {
                Log.i(TAG + "0", "X: " + event.values[0]
                        + " Y: " + event.values[1]
                        + " Z: " + event.values[2]
                        + "  检测耗时: " + timeInterval);
            } else {
                Log.i(TAG, "X: " + event.values[0]
                        + " Y: " + event.values[1]
                        + " Z: " + event.values[2]
                        + "  检测耗时: " + timeInterval);
            }
            lastUpdateTime = currentUpdateTime;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
