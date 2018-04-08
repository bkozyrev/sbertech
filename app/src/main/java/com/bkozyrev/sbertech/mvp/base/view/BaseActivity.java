package com.bkozyrev.sbertech.mvp.base.view;

import android.support.v7.app.AppCompatActivity;

import com.bkozyrev.sbertech.mvp.base.presenter.BasePresenter;

public abstract class BaseActivity<V extends MvpView, P extends BasePresenter<V>>
        extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().attachView(getMvpView());
        onMvpViewAttached();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().detachView();
    }

    public abstract P getPresenter();

    public abstract V getMvpView();

    public abstract void onMvpViewAttached();
}
