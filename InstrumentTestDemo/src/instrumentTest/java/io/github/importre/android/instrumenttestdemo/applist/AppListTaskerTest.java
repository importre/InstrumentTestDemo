package io.github.importre.android.instrumenttestdemo.applist;

import android.test.ActivityInstrumentationTestCase2;

import java.util.List;

public class AppListTaskerTest extends ActivityInstrumentationTestCase2<AppListActivity> {

    private AppListFragment.AppListTasker tasker;

    public AppListTaskerTest() {
        super(AppListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        AppListFragment fragment = AppListFragment.newInstance();
        tasker = fragment.new AppListTasker();
        assertNotNull(tasker);
    }

    public void testGatherInstalledAppList() throws Exception {
        List<AppItem> items =  tasker.execute().get();
        assertNotNull(items);
    }
}
