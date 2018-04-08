package com.bkozyrev.sbertech.mvp.base.presenter;

import com.bkozyrev.sbertech.mvp.base.view.MvpView;

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
