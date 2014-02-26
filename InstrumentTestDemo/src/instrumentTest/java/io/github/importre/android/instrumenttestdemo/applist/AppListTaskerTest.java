package io.github.importre.android.instrumenttestdemo.applist;

import android.test.ActivityInstrumentationTestCase2;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AppListTaskerTest extends ActivityInstrumentationTestCase2<AppListActivity> {

    private AppListTasker tasker;

    public AppListTaskerTest() {
        super(AppListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        tasker = new AppListTasker();
        assertNotNull(tasker);
    }

    public void testGatherInstalledAppList() throws Exception {
        List<AppItem> items =  tasker.get(3000, TimeUnit.MILLISECONDS);
        assertNotNull(items);
    }
}
