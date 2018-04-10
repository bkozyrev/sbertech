package com.bkozyrev.sbertech.mvp.base.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bkozyrev.sbertech.mvp.base.presenter.BasePresenter;

public abstract class BaseActivity<V extends MvpView, P extends BasePresenter<V>>
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().attachView(getMvpView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }

    public abstract P getPresenter();

    public abstract V getMvpView();
}
