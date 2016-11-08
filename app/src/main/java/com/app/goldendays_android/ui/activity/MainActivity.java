package com.app.goldendays_android.ui.activity;

import android.os.Bundle;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseActivity;
import com.app.goldendays_android.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }

    @Override
    public void onBackPressedSupport() {
        // 对于4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }
}
