package com.rinson.cupomaticv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

public class Settings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createListView();
    }


    public void saveButtonClick(View view) {

        openMainActivity();

    }


    public void openMainActivity() {

        Intent intent = new Intent(Settings.this, MainActivity.class);

        startActivity(intent);
    }

    public void createListView() {

        ListView settingsListView = (ListView) (findViewById(R.id.settingListView));
        final String[] settingsNames = {"Mode", "Interval", "Break", "Sample", "Round One", "Round Two", "Round Three", "Vibrate"};
        final String[] settingsValues = {"Mode1", "Interval1", "Break1", "Sample1", "Round One1", "Round Two1", "Round Three1", "Vibrate1"};

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.settings_list_view, settingsNames);
        CustomListAdapter customListAdapter = new CustomListAdapter(this, settingsValues, settingsNames);

        settingsListView.setAdapter(customListAdapter);
        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Selected", settingsNames[position]);
                selectOpenActivity(position);
            }
        });
    }

    public void openIntervalActivity(){

        Intent intent = new Intent(Settings.this, Interval.class);

        startActivity(intent);
    }

    public void openBreakActivity() {

        Intent intent = new Intent(Settings.this, BreakTime.class);

        startActivity(intent);
    }

    public void openSampleActivity() {

        Intent intent = new Intent(Settings.this, Sample.class);

        startActivity(intent);
    }
    public void openRoundOneActivity() {

        Intent intent = new Intent(Settings.this, Round_one.class);

        startActivity(intent);
    }

    public void openRoundTwoActivity() {

        Intent intent = new Intent(Settings.this, Round_two.class);

        startActivity(intent);
    }

    public void openRoundThreeActivity() {

        Intent intent = new Intent(Settings.this, Round_three.class);

        startActivity(intent);
    }

    public void selectOpenActivity(int arrayNumber){

        switch (arrayNumber){
            case 1: openIntervalActivity();
                break;
            case 2: openBreakActivity();
                break;
            case 3: openSampleActivity();
                break;
            case 4: openRoundOneActivity();
                break;
            case 5: openRoundTwoActivity();
                break;
            case 6: openRoundThreeActivity();
                break;
        }


    }

}
//
//    public void createTableRows() {
//        for (int i = 0; i < tableRow.length; i++) {
//
//            tableRow[i] = new TableRow(this);
//
//        }
//    }

//    private void createView(TableRow tr, TextView t, String viewdata) {
//
//
//        tr.SetBackgroundColor(Color.Black);
//        tr.setClickable(true);
//
//        tr.setOnClickListener(tablerowOnClickListener);//add OnClickListener Here
//
//        tr.AddView(t); // add TextView to row.
//
//
//    }
//    private OnClickListener tablerowOnClickListener = new OnClickListener() {
//        public void onClick(View v) {
//            //GET TEXT HERE
//            String currenttext = ((TextView)v).getText().toString());
//        }
//    };}