package com.ck_telecom.d22400.sensorstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ck_telecom.d22400.sensorstudy.service.GsensorService;

import static android.view.WindowManager.*;

public class GsensorActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mStartSrvButton;
    private Button mStopSrvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsensor);
        setTitle("G-Sensor");
        initView();

//        mSm = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mG_sensor = mSm.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        if (mG_sensor != null) {
//            mSm.registerListener(this, mG_sensor, SensorManager.SENSOR_DELAY_UI);
//        }
    }

    private void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.activity_gsensor, null);
        mStartSrvButton = (Button) findViewById(R.id.btn_start_srv);
        mStartSrvButton.setOnClickListener(this);
        mStopSrvButton = (Button) findViewById(R.id.btn_stop_srv);
        mStopSrvButton.setOnClickListener(this);
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        LayoutParams params = new LayoutParams();
//      params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;   //如果设置为
        params.type = LayoutParams.TYPE_PHONE; //
        params.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        wm.addView(layout, params);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_srv:
                startService(new Intent(this, GsensorService.class));
                break;
            case R.id.btn_stop_srv:
                stopService(new Intent(this, GsensorService.class));
                break;
            default:
                break;
        }
    }
}
