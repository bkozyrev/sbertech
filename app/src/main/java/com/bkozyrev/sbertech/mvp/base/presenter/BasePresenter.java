package com.bkozyrev.sbertech.mvp.base.presenter;

import com.bkozyrev.sbertech.mvp.base.view.MvpView;

public class BasePresenter<V extends MvpView> implements Presenter<V> {

    private V mvpView;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

    protected V getMvpView() {
        return mvpView;
    }
}
