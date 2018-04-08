package com.bkozyrev.sbertech.interfaces;

import com.bkozyrev.sbertech.mvp.model.entities.Rss;

public interface GetRssCallback {

    void onRssLoaded(Rss rss);

    void onRssFailed();
}
