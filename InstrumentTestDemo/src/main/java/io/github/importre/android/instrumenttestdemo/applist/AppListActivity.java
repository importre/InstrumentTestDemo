package io.github.importre.android.instrumenttestdemo.applist;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import io.github.importre.android.instrumenttestdemo.R;

public class AppListActivity extends Activity implements AppListFragment.TaskerCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);
        initActionBar();
        initFragment();
        // finishIfHasTwoPanes();
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void initFragment() {
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentByTag(AppListFragment.TAG);
        if (fragment == null) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.applist_container, AppListFragment.newInstance(), AppListFragment.TAG);
            transaction.commit();
        }
    }

    private void finishIfHasTwoPanes() {
        Resources resources = getResources();
        boolean hasTwoPanes = resources.getBoolean(R.bool.has_two_panes);
        if (hasTwoPanes) {
            finish();
        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                // do nothing
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onPostExecute(List<AppItem> appItems) {
        FragmentManager fm = getFragmentManager();
        AppListFragment fragment = (AppListFragment) fm.findFragmentByTag(AppListFragment.TAG);
        if (fragment == null) {
            return;
        }

        fragment.setAppList(appItems);
    }
}
