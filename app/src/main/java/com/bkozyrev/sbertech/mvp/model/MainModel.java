package com.bkozyrev.sbertech.mvp.model;

import com.bkozyrev.sbertech.interfaces.GetRssCallback;

public interface MainModel {

    void getRss(GetRssCallback callback);
}
