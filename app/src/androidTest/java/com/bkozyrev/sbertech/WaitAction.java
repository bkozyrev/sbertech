package com.bkozyrev.sbertech;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.anything;

public class WaitAction implements ViewAction {

    private long timeout;
    private long startTime;

    public WaitAction(long timeout) {
        this.timeout = timeout;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public Matcher<View> getConstraints() {
        return (Matcher) anything();
    }

    @Override
    public String getDescription() {
        return WaitAction.class.getSimpleName();
    }

    @Override
    public void perform(UiController uiController, View view) {
        while (System.currentTimeMillis() < startTime + timeout) {
            uiController.loopMainThreadForAtLeast(100);
        }
    }

    public static ViewAction waitFor(long timeout) {
        return new WaitAction(timeout);
    }
}
