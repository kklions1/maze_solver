package kklions.mazesolver.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

/**
 * Adapter for the method selection list
 *
 * Created by kevin on 1/13/18.
 */

public class MethodListAdapter extends BaseExpandableListAdapter {

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        // TODO implement method stub
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO implement method stub
        return true;
    }

    @Override
    public int getGroupCount() {
        //TODO implement method stub
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //TODO implement method stub
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getChild(int groupPosition, int childPosition) {
        //TODO implement method stub
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }
}
