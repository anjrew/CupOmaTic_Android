package com.rinson.cupomaticv2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity activity;
    private final String[] element;
    private final String[] key;

    public CustomListAdapter(Activity activity, String[] itemName, String[] element) {
        super(activity, R.layout.list_view_row, itemName);
        // TODO Auto-generated constructor stub

        this.activity=activity;
        this.key=itemName;
        this.element=element;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_row, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.Value);
        TextView extratxt = (TextView) rowView.findViewById(R.id.Option);

        txtTitle.setText(key[position]);
        extratxt.setText(element[position]);
        return rowView;
    }
}