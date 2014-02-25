package io.github.importre.android.instrumenttestdemo;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public static final String TAG = MainActivityTest.class.getSimpleName();
    private MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        Log.d(TAG, "setUp");
        super.setUp();

        activity = getActivity();
        assertNotNull(activity);
    }

    @Override
    protected void tearDown() throws Exception {
        Log.d(TAG, "tearDown");
        super.tearDown();
    }

    public void testShouldHaveTitleInActionBar() {
        Log.d(TAG, "testShouldHaveTitleInActionBar");

        ActionBar actionBar = activity.getActionBar();
        assertNotNull(actionBar);

        CharSequence title = actionBar.getTitle();
        assertNotNull(title);
        assertEquals("InstrumentTestDemo", title);
    }

    public void testNavigationModeShouldBeStandard() {
        Log.d(TAG, "testNavigationModeShouldBeStandard");

        ActionBar actionBar = activity.getActionBar();
        assertNotNull(actionBar);

        final int naviMode = actionBar.getNavigationMode();
        assertEquals(ActionBar.NAVIGATION_MODE_STANDARD, naviMode);
    }

    public void testShouldHaveContainers() {
        Resources resources = activity.getResources();
        boolean hasTwoPanes = resources.getBoolean(R.bool.has_two_panes);

        View main = activity.findViewById(R.id.main_container);
        assertNotNull(main);

        if (hasTwoPanes) {
            View detail = activity.findViewById(R.id.detail_container);
            assertNotNull(detail);
        }
    }
}
