package com.app.goldendays_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseActivity;

public class TVActivity extends BaseActivity {
        WebView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        tv= (WebView) findViewById(R.id.tv);
        tv.loadUrl("http://pan.baidu.com/s/1qYjfkK0");
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if(tv!=null){
            tv.destroy();
        }
        super.onDestroy();
    }
}
