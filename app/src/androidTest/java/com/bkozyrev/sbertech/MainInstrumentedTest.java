package com.bkozyrev.sbertech;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.bkozyrev.sbertech.mvp.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void recyclerViewTest() {
        RecyclerView recyclerView = mainActivityTestRule.getActivity()
                .findViewById(R.id.recyclerViewHubs);

        onView(withId(R.id.recyclerViewHubs)).perform(ScrollToPositionViewAction
                .smoothScrollTo(recyclerView.getAdapter().getItemCount() - 1));
        onView(withId(R.id.recyclerViewHubs)).perform(WaitAction.waitFor(2000));
        onView(withId(R.id.recyclerViewHubs)).perform(ScrollToPositionViewAction
                .smoothScrollTo(0));
        onView(withId(R.id.recyclerViewHubs)).perform(WaitAction.waitFor(2000));
    }
}
