package com.rinson.cupomaticv2;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HelpListAdapter extends ArrayAdapter<String> {

    private final Activity activity;
    private final String[] element;
    private final String[] key;
    public Help helpActivity;




    public HelpListAdapter(Activity activity, String[] itemName, String[] element, Help helpActivity) {
        super(activity, R.layout.help_list_view_row, itemName);
        // TODO Auto-generated constructor stub

        this.activity = activity;
        this.key=itemName;
        this.element=element;
        this.helpActivity = helpActivity;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.help_list_view_row, null,true);

        TextView txtTitle = rowView.findViewById(R.id.Value);
        TextView extratxt = rowView.findViewById(R.id.Option);

        txtTitle.setText(key[position]);
        extratxt.setText(element[position]);

        return rowView;
    }

}

