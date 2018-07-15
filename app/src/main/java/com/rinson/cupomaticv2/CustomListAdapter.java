package com.rinson.cupomaticv2;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity activity;
    private final String[] element;
    private final String[] key;
    private final ToggleButton[] toggleButtons;
    private Settings settingActivity;
    boolean checked;




    public CustomListAdapter(Activity activity, String[] itemName, String[] element, ToggleButton[] toggleButtons, Settings settingsActivity) {
        super(activity, R.layout.list_view_row, itemName);
        // TODO Auto-generated constructor stub

        this.activity=activity;
        this.key=itemName;
        this.element=element;
        this.toggleButtons = toggleButtons;
        this.settingActivity = settingsActivity;
    }

    public View getView(final int position, View view, ViewGroup parent) {
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
                    checked = toggle.isChecked();
                    setToggleStates(position);
                }
            });
        }

        txtTitle.setText(key[position]);
        extratxt.setText(element[position]);

        return rowView;
    }


    public void setToggleStates(int position){

        switch (position){

            case 0:
                settingActivity.updateAdvancedMode(checked);
                Log.i("Advanced Mode", String.valueOf(checked));
                settingActivity.recreate();
                break;
            case 1:
                settingActivity.updateVoiceMode(checked);
                Log.i("Voice Mode", String.valueOf(checked));

                break;
            case 2:
                settingActivity.updateVibrateMode(checked);
                Log.i("Vibrate Mode", String.valueOf(checked));

                break;
        }


    }
}