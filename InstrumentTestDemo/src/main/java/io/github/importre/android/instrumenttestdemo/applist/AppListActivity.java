package io.github.importre.android.instrumenttestdemo.applist;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;

import io.github.importre.android.instrumenttestdemo.R;

public class AppListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);
        initActionBar();
        initFragment();
        finishIfHasTwoPanes();
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void initFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.applist_container, AppListFragment.newInstance(), AppListFragment.TAG);
        transaction.commit();
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
}
