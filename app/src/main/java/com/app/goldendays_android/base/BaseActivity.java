package com.app.goldendays_android.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.lzy.okgo.OkGo;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 王立强 on 2016/11/7.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity {

    private T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initToolbar(Toolbar toolbar,String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onPause() {
        super.onPause();
        OkGo.getInstance().cancelTag(this);
        presenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        OkGo.getInstance().cancelTag(this);
        presenter = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OkGo.getInstance().cancelTag(this);
        presenter = null;
    }

    public void showError() {
        SnackbarManager.show(Snackbar.with(this).text("暂无数据").duration(Snackbar.SnackbarDuration.LENGTH_SHORT));
    }

    private ProgressDialog dialog;

    public void showLoading() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求数据中...");
        dialog.show();
    }

    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}