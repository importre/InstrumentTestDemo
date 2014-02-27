package io.github.importre.android.instrumenttestdemo.applist;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.importre.android.instrumenttestdemo.R;

public class AppListFragment extends Fragment {

    public static final String TAG = AppListFragment.class.getSimpleName();

    private TaskerCallbacks mCallbacks;
    private ListView mListView;
    private TextView mTitle;

    public static AppListFragment newInstance() {
        return new AppListFragment();
    }

    public interface TaskerCallbacks {
        public void onPostExecute(List<AppItem> appItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applist, container, false);
        mListView = (ListView) view.findViewById(R.id.app_list_view);
        mListView.setAdapter(new AppListAdapter(getActivity(), R.layout.item_app_list));

        mTitle = (TextView) view.findViewById(R.id.title);
        new AppListTasker().execute();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof TaskerCallbacks) {
            mCallbacks = (TaskerCallbacks) activity;
        } else {
            mCallbacks = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle();
    }

    private void setTitle() {
        ListAdapter adapter = mListView.getAdapter();
        if (adapter != null) {
            mTitle.setText("" + adapter.getCount());
        }
    }

    public void setAppList(List<AppItem> appItems) {
        AppListAdapter adapter = (AppListAdapter) mListView.getAdapter();
        adapter.clear();
        adapter.addAll(appItems);
        adapter.notifyDataSetChanged();
    }

    public class AppListTasker extends AsyncTask<Void, Void, List<AppItem>> {

        @Override
        protected List<AppItem> doInBackground(Void... params) {
            List<AppItem> result = new ArrayList<AppItem>();
            Activity activity = getActivity();
            if (activity == null) {
                return result;
            }

            PackageManager pm = activity.getPackageManager();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);
            for (ResolveInfo info : list) {
                Drawable icon = info.loadIcon(pm);
                String name = info.loadLabel(pm).toString();
                result.add(new AppItem(icon, name));
            }

            return result;
        }

        @Override
        protected void onPostExecute(List<AppItem> appItems) {
            if (mCallbacks != null) {
                mCallbacks.onPostExecute(appItems);
                mTitle.setText("" + appItems.size());
            }
        }
    }
}
