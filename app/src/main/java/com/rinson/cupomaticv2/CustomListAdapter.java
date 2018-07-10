package com.rinson.cupomaticv2;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity activity;
    private final String[] element;
    private final String[] key;
    private final ToggleButton[] toggleButtons;


    public CustomListAdapter(Activity activity, String[] itemName, String[] element, ToggleButton[] toggleButtons) {
        super(activity, R.layout.list_view_row, itemName);
        // TODO Auto-generated constructor stub

        this.activity=activity;
        this.key=itemName;
        this.element=element;
        this.toggleButtons = toggleButtons;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_row, null,true);

        TextView txtTitle = rowView.findViewById(R.id.Value);
        TextView extratxt = rowView.findViewById(R.id.Option);
        final ToggleButton  toggle = rowView.findViewById(R.id.toggleButton);

        // position of text views
        if(position >= toggleButtons.length) {
            toggle.setVisibility(View.GONE);
        }


        //position of toggle buttons
        if(position < toggleButtons.length){
            txtTitle.setVisibility(View.GONE);
            toggle.setChecked(toggleButtons[position].isChecked());
            toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (toggle.isChecked() == true) {
                        toggle.setChecked(false);
                    } else {
                        toggle.setChecked(false);
                    }

                    if(toggle.isChecked()==true){
                        Log.i("Toggle ", "is Checked");
                    }else{
                        Log.i("Toggle ", "is not Checked");
                    }
                }
            });
        }


        txtTitle.setText(key[position]);
        extratxt.setText(element[position]);

        return rowView;
    }
}