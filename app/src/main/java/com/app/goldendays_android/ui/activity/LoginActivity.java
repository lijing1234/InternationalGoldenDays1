package com.app.goldendays_android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseActivity;
import com.app.goldendays_android.presenter.LoginPresenter;
import com.app.goldendays_android.presenter.contract.LoginContract;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_tv)
    TextView toolbarTv;

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        new LoginPresenter(this);
        initToolbar(toolbar,"");
        toolbarTv.setText("金天账号登录");
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
