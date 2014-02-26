package io.github.importre.android.instrumenttestdemo.applist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.importre.android.instrumenttestdemo.R;

public class AppListAdapter extends ArrayAdapter<AppItem> {

    private final Context mContext;
    private final int mResource;

    public AppListAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView icon;
        TextView name;

        if (convertView == null) {
            convertView = inflater.inflate(mResource, parent, false);
            icon = (ImageView) convertView.findViewById(R.id.app_icon);
            name = (TextView) convertView.findViewById(R.id.app_name);
            convertView.setTag(R.id.app_icon, icon);
            convertView.setTag(R.id.app_name, name);
        } else {
            icon = (ImageView) convertView.getTag(R.id.app_icon);
            name = (TextView) convertView.getTag(R.id.app_name);
        }

        AppItem item = getItem(position);
        icon.setImageDrawable(item.getIcon());
        name.setText(item.getName());

        return convertView;
    }
}
