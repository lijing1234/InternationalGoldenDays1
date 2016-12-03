package com.app.goldendays_android.ui.activity;

import android.databinding.DataBindingUtil;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.goldendays_android.R;
import com.app.goldendays_android.databinding.ActivitySharedelementBinding;
import com.app.goldendays_android.utils.Sample;

import fm.jiecao.jcvideoplayer_lib.JCBuriedPoint;
import fm.jiecao.jcvideoplayer_lib.JCBuriedPointStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class SharedElementActivity extends BaseDetailActivity implements View.OnClickListener{

    JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;
    SensorManager mSensorManager;
    JCVideoPlayerStandard mJcVideoPlayerStandard;
    Button mTinyWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        bindData(sample);
        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar();

    }

    private void bindData(Sample sample) {
        ActivitySharedelementBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sharedelement);
        binding.setSharedSample(sample);
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(Sample sample) {

        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp(Environment.getExternalStorageDirectory()
                        .getPath()+"/你好.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子真嘚瑟");
//        Picasso.with(this)
//                .load("http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg")
//                .into(mJcVideoPlayerStandard.thumbImageView);
        mJcVideoPlayerStandard.looping = true;
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiny_window:
                mJcVideoPlayerStandard.startWindowTiny();
                break;

        }
    }


}
