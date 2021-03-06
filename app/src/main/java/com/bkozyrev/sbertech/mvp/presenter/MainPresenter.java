package com.bkozyrev.sbertech.mvp.presenter;

import com.bkozyrev.sbertech.mvp.base.presenter.BasePresenter;
import com.bkozyrev.sbertech.interfaces.GetRssCallback;
import com.bkozyrev.sbertech.mvp.model.MainModel;
import com.bkozyrev.sbertech.mvp.model.entities.Rss;
import com.bkozyrev.sbertech.mvp.view.MainMvpView;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private MainModel mainModel;

    public MainPresenter(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void loadRss() {
        getMvpView().showLoading();
        mainModel.getRss(new GetRssCallback() {
            @Override
            public void onRssLoaded(Rss rss) {
                getMvpView().showRss(rss);
                getMvpView().hideLoading();
            }

            @Override
            public void onRssFailed() {
                getMvpView().hideLoading();
            }
        });
    }
}
