package com.bkozyrev.sbertech;

import com.bkozyrev.sbertech.interfaces.GetRssCallback;
import com.bkozyrev.sbertech.mvp.model.MainModel;
import com.bkozyrev.sbertech.mvp.model.entities.Channel;
import com.bkozyrev.sbertech.mvp.model.entities.Rss;
import com.bkozyrev.sbertech.mvp.model.entities.RssItem;
import com.bkozyrev.sbertech.mvp.presenter.MainPresenter;
import com.bkozyrev.sbertech.mvp.view.MainMvpView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MainModel mainModel;

    @Mock
    private MainMvpView mainMvpView;

    @Captor
    private ArgumentCaptor<GetRssCallback> argumentCaptor;

    private MainPresenter mainPresenter;

    private Rss expectedRss;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpTestRss();
        mainModel = Mockito.mock(MainModel.class);
        mainMvpView = Mockito.mock(MainMvpView.class);

        Mockito.doAnswer((Answer<Object>) invocation -> {
            ((GetRssCallback) invocation.getArguments()[0]).onRssLoaded(expectedRss);
            return null;
        }).when(mainModel).getRss(Mockito.any());

        mainPresenter = new MainPresenter(mainModel);
        mainPresenter.attachView(mainMvpView);
    }

    private void setUpTestRss() {
        expectedRss = new Rss();
        Channel channel = new Channel();
        ArrayList<RssItem> items = new ArrayList<>();
        items.add(new RssItem("Test 1"));
        items.add(new RssItem("Test 2"));
        items.add(new RssItem("Test 3"));
        items.add(new RssItem("Test 4"));
        items.add(new RssItem("Test 5"));
        channel.setRssItems(items);
        expectedRss.setChannel(channel);
    }

    @Test
    public void shouldLoadRss() {
        mainPresenter.loadRss();
        verify(mainMvpView, times(1)).showLoading();
        verify(mainModel).getRss(argumentCaptor.capture());
        verify(mainMvpView, times(1)).hideLoading();
        verify(mainMvpView, times(1)).showRss(expectedRss);
    }
}
