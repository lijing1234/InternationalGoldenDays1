package com.app.goldendays_android.presenter.contract;

import com.app.goldendays_android.base.BasePresenter;
import com.app.goldendays_android.base.BaseView;

/**
 * Created by 王立强 on 2016/11/8.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void showError();
    }

    interface Presenter extends BasePresenter {

    }
}
