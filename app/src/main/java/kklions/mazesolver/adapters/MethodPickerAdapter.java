package kklions.mazesolver.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

/**
 * Adapter for method picker
 *
 * Created by kevin on 1/14/18.
 */

public class MethodPickerAdapter implements SpinnerAdapter {
    public int getCount() {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public int getItemViewType(int position) {
        return 0;
    }

    public int getViewTypeCount() {
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEmpty() {
        return false;
    }

    public void registerDataSetObserver(DataSetObserver observer) {

    }

    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
