package com.bkozyrev.sbertech.mvp.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView,
        SwipeRefreshLayout.OnRefreshListener {

    private MainPresenter mainPresenter;

    private HubsRecyclerViewAdapter adapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewHubs = findViewById(R.id.recyclerViewHubs);
        recyclerViewHubs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHubs.setAdapter(adapter = new HubsRecyclerViewAdapter());

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        progressBar = findViewById(R.id.progressBar);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        mainPresenter.loadRss();
    }

    @Override
    public MainPresenter getPresenter() {
        if (mainPresenter == null) {
            mainPresenter = new MainPresenter(new MainModelImpl());
        }
        return mainPresenter;
    }

    @Override
    public MainMvpView getMvpView() {
        return this;
    }

    @Override
    public void showRss(Rss rss) {
        runOnUiThread(() -> {
            if (rss != null) {
                adapter.setItems(rss.getChannel().getRssItems());
            }
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
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        adapter.setItems(new ArrayList<>());
        mainPresenter.loadRss();
    }
}
