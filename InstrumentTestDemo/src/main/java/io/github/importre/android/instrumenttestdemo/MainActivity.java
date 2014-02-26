package io.github.importre.android.instrumenttestdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import io.github.importre.android.instrumenttestdemo.applist.AppListFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        initMainFragment();
    }

    private void initMainFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.main_container, MainFragment.newInstance(), MainFragment.TAG);
        transaction.commit();
    }

    public void initDetailFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.detail_container, AppListFragment.newInstance(), AppListFragment.TAG);
        transaction.commit();
    }
}
