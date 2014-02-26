package io.github.importre.android.instrumenttestdemo.applist;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.squareup.spoon.Spoon;

public class AppListFragmentTest extends ActivityInstrumentationTestCase2<AppListActivity> {

    private AppListActivity activity;
    private AppListFragment fragment;

    public AppListFragmentTest() {
        super(AppListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        assertNotNull(activity);

        FragmentManager fm = activity.getFragmentManager();
        fragment = (AppListFragment) fm.findFragmentByTag(AppListFragment.TAG);
        assertNotNull(fragment);
    }

    public void testGetViewMustBeNotNull() {
        View view = fragment.getView();
        assertNotNull(view);
    }
}
