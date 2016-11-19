package com.app.goldendays_android.base;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.app.goldendays_android.R;
import com.app.goldendays_android.ui.view.StatusBarCompat;
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

    /***
     *
     * @param toolbar toolbar
     * @param title 标题为""，已重新设置标题
     * @param statusColor 设置状态栏颜色
     * @param homeAs 是否显示返回图标
     * @param homeAsColor 返回图标颜色
     */
    public void initToolbar(Toolbar toolbar,String title,int statusColor,boolean homeAs,int homeAsColor){
        StatusBarCompat.compat(this,getResources().getColor(statusColor));
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if(homeAs){
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAs);
            Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
            upArrow.setColorFilter(getResources().getColor(homeAsColor), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        OkGo.getInstance().cancelTag(this);
        presenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
//        OkGo.getInstance().cancelTag(this);
        presenter = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        OkGo.getInstance().cancelTag(this);
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