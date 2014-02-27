package io.github.importre.android.instrumenttestdemo.applist;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.spoon.Spoon;

import io.github.importre.android.instrumenttestdemo.R;

public class AppListFragmentTest extends ActivityInstrumentationTestCase2<AppListActivity> {

    private static final String TAG = AppListFragmentTest.class.getSimpleName();
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

        Spoon.screenshot(activity, "initial_state");
    }

    public void testTitleText() throws Exception {
        View view = fragment.getView();

        ListView listView = (ListView) view.findViewById(R.id.app_list_view);
        assertNotNull(listView);

        TextView textView = (TextView) view.findViewById(R.id.title);
        assertNotNull(textView);

        String title = textView.getText().toString();
        assertEquals(listView.getAdapter().getCount(), Integer.parseInt(title));
    }

    public void testShouldShowEmptyMessage() throws Throwable {
        View view = fragment.getView();

        Spoon.screenshot(activity, "initial_state");

        ListView listView = (ListView) view.findViewById(R.id.app_list_view);
        assertNotNull(listView);

        final AppListAdapter adapter = (AppListAdapter) listView.getAdapter();
        assertNotNull(adapter);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "clear adapter");
                adapter.clear();
                adapter.notifyDataSetChanged();
                fragment.onResume();
            }
        });

        Spoon.screenshot(activity, "clear_state");
    }
}
