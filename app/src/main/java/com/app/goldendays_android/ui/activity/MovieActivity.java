package com.app.goldendays_android.ui.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseActivity;
import com.squareup.picasso.Picasso;


import fm.jiecao.jcvideoplayer_lib.JCBuriedPoint;
import fm.jiecao.jcvideoplayer_lib.JCBuriedPointStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

//http://video.jiecao.fm/8/17/%E6%8A%AB%E8%90%A8.mp4
public class MovieActivity extends BaseActivity implements View.OnClickListener{
    JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;
    SensorManager mSensorManager;
    JCVideoPlayerStandard mJcVideoPlayerStandard;
    Button mTinyWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mTinyWindow = (Button) findViewById(R.id.tiny_window);

        mTinyWindow.setOnClickListener(this);

        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp(Environment.getExternalStorageDirectory()
                        .getPath()+"/你好.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子真嘚瑟");
//        Picasso.with(this)
//                .load("http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg")
//                .into(mJcVideoPlayerStandard.thumbImageView);
        mJcVideoPlayerStandard.looping = true;
        JCVideoPlayer.setJcBuriedPoint(new MyJCBuriedPointStandard());
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

    class MyJCBuriedPointStandard implements JCBuriedPointStandard {

        @Override
        public void onEvent(int type, String url, int screen, Object... objects) {
            switch (type) {
                case JCBuriedPoint.ON_CLICK_START_ICON:
                    Log.i("Buried_Point", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_CLICK_START_ERROR:
                    Log.i("Buried_Point", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_CLICK_START_AUTO_COMPLETE:
                    Log.i("Buried_Point", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_CLICK_PAUSE:
                    Log.i("Buried_Point", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_CLICK_RESUME:
                    Log.i("Buried_Point", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_SEEK_POSITION:
                    Log.i("Buried_Point", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_AUTO_COMPLETE:
                    Log.i("Buried_Point", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_ENTER_FULLSCREEN:
                    Log.i("Buried_Point", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_QUIT_FULLSCREEN:
                    Log.i("Buried_Point", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_ENTER_TINYSCREEN:
                    Log.i("Buried_Point", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_QUIT_TINYSCREEN:
                    Log.i("Buried_Point", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_TOUCH_SCREEN_SEEK_VOLUME:
                    Log.i("Buried_Point", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPoint.ON_TOUCH_SCREEN_SEEK_POSITION:
                    Log.i("Buried_Point", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;

                case JCBuriedPointStandard.ON_CLICK_START_THUMB:
                    Log.i("Buried_Point", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCBuriedPointStandard.ON_CLICK_BLANK:
                    Log.i("Buried_Point", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                default:
                    Log.i("Buried_Point", "unknow");
                    break;
            }
        }
    }

}
