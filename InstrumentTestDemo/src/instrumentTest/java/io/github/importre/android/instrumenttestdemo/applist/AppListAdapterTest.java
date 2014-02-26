package io.github.importre.android.instrumenttestdemo.applist;

import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.importre.android.instrumenttestdemo.R;

public class AppListAdapterTest extends ActivityInstrumentationTestCase2<AppListActivity> {

    private AppListActivity activity;
    private AppListAdapter adapter;

    public AppListAdapterTest() {
        super(AppListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        assertNotNull(activity);

        adapter = new AppListAdapter(getActivity(), R.layout.item_app_list);
        assertNotNull(adapter);
    }

    private AppItem addOneItem() {
        Drawable icon = activity.getResources().getDrawable(R.drawable.ic_launcher);
        AppItem item = new AppItem(icon, "item");
        adapter.add(item);
        return item;
    }

    public void testGetItem() {
        AppItem item = addOneItem();
        assertEquals(item, adapter.getItem(0));
    }

    public void testGetView() {
        addOneItem();

        View view = adapter.getView(0, null, null);
        assertNotNull(view);

        ImageView icon = (ImageView) view.findViewById(R.id.app_icon);
        assertNotNull(icon);

        TextView name = (TextView) view.findViewById(R.id.app_name);
        assertNotNull(name);
    }

    public void testGetViewRecycle() {
        addOneItem();

        View view1 = adapter.getView(0, null, null);
        View view2 = adapter.getView(0, view1, null);

        assertEquals(view1, view2);
    }

    public void testGetViewRecycleUsingViewHolderPattern() {
        addOneItem();

        View view = adapter.getView(0, null, null);

        ImageView icon = (ImageView) view.findViewById(R.id.app_icon);
        TextView name = (TextView) view.findViewById(R.id.app_name);

        assertEquals(icon, view.getTag(R.id.app_icon));
        assertEquals(name, view.getTag(R.id.app_name));
    }

    public void testAddItem() {
        AppItem item = addOneItem();
        assertEquals(item, adapter.getItem(0));
    }

    public void testGetCount() {
        addOneItem();

        int count = adapter.getCount();
        assertEquals(1, count);
    }
}
