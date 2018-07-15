package com.rinson.cupomaticv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Help extends AppCompatActivity {

    private String[] item;
    private String[] description;
    boolean checked;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setUpValues();
        createListView();
    }

    public void setUpValues(){
        item  =  new String[]{"Advanced mode", "Voice prompts", "Vibrate", "Main Timer", "Interval", "Break", "Sample", "Round one", "Round two", "Round three", "Contact"};
        description = new String[]{"Advanced mode", "Voice prompts", "Vibrate","Main Timer", "Interval", "Break", "Sample", "Round one", "Round two", "Round three", "Contact"};
    }


    public void createListView() {

        ListView helpListView = findViewById(R.id.help_list_view);

        HelpListAdapter helpListAdapter = new HelpListAdapter(this, description, item,this);
        helpListView.setAdapter(helpListAdapter);
        helpListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Selected", item[position]);
            }
        });
    }


}
