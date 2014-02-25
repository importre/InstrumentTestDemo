package io.github.importre.android.instrumenttestdemo;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.squareup.spoon.Spoon;

public class MainFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;
    private MainFragment fragment;

    public MainFragmentTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        assertNotNull(activity);

        FragmentManager fm = activity.getFragmentManager();
        fragment = (MainFragment) fm.findFragmentByTag(MainFragment.TAG);
    }

    public void testGetViewMustBeNotNull() {
        View view = fragment.getView();
        assertNotNull(view);
    }

    public void testShouldHaveListView() {
        View view = fragment.getView();

        ListView listView = (ListView) view.findViewById(R.id.list_view);
        assertNotNull(listView);

        ListAdapter adapter = listView.getAdapter();
        assertNotNull(adapter);

        assertEquals(true, adapter instanceof ArrayAdapter);

        Spoon.screenshot(activity, "initial_state");
    }
}
