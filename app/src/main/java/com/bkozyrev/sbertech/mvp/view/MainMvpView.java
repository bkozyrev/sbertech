package com.bkozyrev.sbertech.mvp.view;

import com.bkozyrev.sbertech.mvp.base.view.MvpView;
import com.bkozyrev.sbertech.mvp.model.entities.Rss;

public interface MainMvpView extends MvpView {

    void showRss(Rss rss);

    void showLoading();

    void hideLoading();
}
