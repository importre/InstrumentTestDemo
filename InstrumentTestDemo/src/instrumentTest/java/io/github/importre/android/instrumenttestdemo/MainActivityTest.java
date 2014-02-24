package io.github.importre.android.instrumenttestdemo;

import android.app.ActionBar;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

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
}
