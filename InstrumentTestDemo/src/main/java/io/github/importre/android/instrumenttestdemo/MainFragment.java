package io.github.importre.android.instrumenttestdemo;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.github.importre.android.instrumenttestdemo.applist.AppListActivity;

public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = MainFragment.class.getSimpleName();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initListView(view);
        return view;
    }

    private void initListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        String[] menu = getResources().getStringArray(R.array.menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                view.getContext(),
                android.R.layout.simple_list_item_1,
                menu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: // Application List
                Resources resources = getResources();
                boolean hasTwoPanes = resources.getBoolean(R.bool.has_two_panes);
                if (hasTwoPanes) {
                    MainActivity activity = (MainActivity) getActivity();
                    if (activity != null) {
                        activity.initDetailFragment();
                    }
                } else {
                    Intent intent = new Intent(getActivity(), AppListActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                // do nothing
                break;
        }
    }
}
