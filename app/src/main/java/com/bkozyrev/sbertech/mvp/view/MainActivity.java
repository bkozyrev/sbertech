package com.bkozyrev.sbertech.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.bkozyrev.sbertech.R;
import com.bkozyrev.sbertech.mvp.base.view.BaseActivity;
import com.bkozyrev.sbertech.mvp.model.MainModelImpl;
import com.bkozyrev.sbertech.mvp.model.entities.Rss;
import com.bkozyrev.sbertech.mvp.presenter.MainPresenter;
import com.bkozyrev.sbertech.mvp.view.adapters.HubsRecyclerViewAdapter;

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView {

    private MainPresenter mainPresenter;

    private RecyclerView recyclerViewHubs;
    private HubsRecyclerViewAdapter adapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewHubs = findViewById(R.id.recyclerViewHubs);
        recyclerViewHubs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHubs.setAdapter(adapter = new HubsRecyclerViewAdapter());

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        progressBar = findViewById(R.id.progressBar);

        mainPresenter = new MainPresenter(new MainModelImpl());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public MainPresenter getPresenter() {
        return mainPresenter;
    }

    @Override
    public MainMvpView getMvpView() {
        return this;
    }

    @Override
    public void onMvpViewAttached() {
        mainPresenter.loadRss();
    }

    @Override
    public void showRss(Rss rss) {
        runOnUiThread(() -> {
            adapter.setItems(rss.getChannel().getRssItems());
        });
    }

    @Override
    public void showLoading() {
        runOnUiThread(() -> {
            progressBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            progressBar.setVisibility(View.GONE);
        });
    }
}
