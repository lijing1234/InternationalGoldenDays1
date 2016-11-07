package com.app.goldendays_android.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 王立强 on 2016/11/7.
 */

public class RxPresenter <T> implements BasePresenter<T>{

    protected T mView;

    protected CompositeSubscription mCompositeSubscription;

    protected void subscribe(Subscription subscription){
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unsubscribe(){
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(T view) {
        this.mView  = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unsubscribe();
    }
}