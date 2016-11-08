package com.app.goldendays_android.presenter;

import com.app.goldendays_android.base.RxPresenter;
import com.app.goldendays_android.presenter.contract.LoginContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 王立强 on 2016/11/8.
 */

public class LoginPresenter extends RxPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view){
        mView = checkNotNull(view,"view is not null");
        mView.setPresenter(this);
    }



}
