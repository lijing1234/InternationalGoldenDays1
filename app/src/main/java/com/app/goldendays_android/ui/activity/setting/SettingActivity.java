package com.app.goldendays_android.ui.activity.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.app.goldendays_android.R;

public class SettingActivity extends AppCompatActivity {
    WebView wb;
    String url = "http://172.17.20.49:8080/script/HelloWorld1.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        wb = (WebView) findViewById(R.id.wb);
        wb.loadUrl(url);
    }
}
