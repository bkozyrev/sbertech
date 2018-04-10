package com.bkozyrev.sbertech;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.AllOf.allOf;

public class ScrollToPositionViewAction implements ViewAction {

    public static ScrollToPositionViewAction smoothScrollTo(int position) {
        return new ScrollToPositionViewAction(position);
    }

    private final int position;

    private ScrollToPositionViewAction(int position) {
        this.position = position;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Matcher<View> getConstraints() {
        return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
    }
    @Override
    public String getDescription() {
        return "smooth scroll RecyclerView to position: " + position;
    }
    @Override
    public void perform(UiController uiController, View view) {
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.smoothScrollToPosition(position);
    }
}