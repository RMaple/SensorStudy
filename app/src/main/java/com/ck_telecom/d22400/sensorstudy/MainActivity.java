package com.ck_telecom.d22400.sensorstudy;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCenterTextView;
    private Button mGSensorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s : sensorList) {
            mCenterTextView.append(s.getName() + "\n");
        }
    }

    private void initView() {
        mCenterTextView = (TextView) findViewById(R.id.tv_center);
        mGSensorButton = (Button) findViewById(R.id.btn_g_sensor);
        mGSensorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_g_sensor:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, 10);
                    }else {
                        startActivity(new Intent(this, GsensorActivity.class));
                        finish();
                    }
                }

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {

                }
            }
        }
    }
}
