package com.rinson.cupomaticv2;

import android.content.Intent;
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
    public String advancedModeText;
    public String voicePromptsText;
    public String vibrateText;
    public String mainTimerText;
    public String intervalText;
    public String breakText;
    public String sampleText;
    public String roundsText;
    public String contactText;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupDescriptionStrings();
        setUpValues();
        createListView();
    }

    public void setUpValues(){
        item  =  new String[]{"Advanced mode", "Voice prompts", "Vibrate", "Main Timer", "Interval", "Break", "Sample", "Rounds", "Contact"};
        description = new String[]{advancedModeText, voicePromptsText, vibrateText, mainTimerText, intervalText, breakText, sampleText, roundsText, contactText};
    }


    public void createListView() {

        ListView helpListView = findViewById(R.id.help_list_view);

        HelpListAdapter helpListAdapter = new HelpListAdapter(this, description, item,this);
        helpListView.setAdapter(helpListAdapter);
        helpListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Selected", item[position]);
                if (position == 8) {
                    selectOpenActivity(position);
                    Log.i("Position selected",String.valueOf(position));
                }
            }
        });
    }

    public void selectOpenActivity(int arrayNumber) {
//        Log.i(String.valueOf(arrayNumber,));

        switch (arrayNumber) {

            case 8:

                Log.i("Email  ","  In Email");
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@cupomatic.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "CupOmatic Feedback");
                email.putExtra(Intent.EXTRA_TEXT, "Your app is AMAZING!!! Bravo!");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));


                break;
            default:
                Log.i("Warning  ","  You shouldn't be here");
                break;

        }
    }

    public void setupDescriptionStrings(){
        advancedModeText = (String)getText(R.string.Advanced_mode_description);
        voicePromptsText = (String)getText(R.string.Voice_prompts_description);
        vibrateText = (String)getText(R.string.Vibrate_description);
        mainTimerText = (String)getText(R.string.Main_timer_description);
        intervalText = (String)getText(R.string.Interval_description);
        breakText = (String)getText(R.string.Break_description);
        sampleText = (String)getText(R.string.Sample_description);
        roundsText = (String) getText(R.string.Rounds_description);
        contactText = (String) getText(R.string.Contact_description);
    }
}
