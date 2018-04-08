package com.bkozyrev.sbertech.mvp.model;

import com.bkozyrev.sbertech.interfaces.GetRssCallback;

public interface MainModel {

    void setCallback(GetRssCallback callback);

    void getRss();
}
