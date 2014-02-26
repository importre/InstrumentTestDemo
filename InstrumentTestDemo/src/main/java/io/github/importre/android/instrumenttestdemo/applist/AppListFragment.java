package io.github.importre.android.instrumenttestdemo.applist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import io.github.importre.android.instrumenttestdemo.R;

public class AppListFragment extends Fragment {

    public static final String TAG = AppListFragment.class.getSimpleName();

    public static AppListFragment newInstance() {
        return new AppListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applist, container, false);
        ListView listView = (ListView) view.findViewById(R.id.app_list_view);
        listView.setAdapter(new AppListAdapter(getActivity(), R.layout.item_app_list));
        return view;
    }
}
