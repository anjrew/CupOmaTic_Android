package com.rinson.cupomaticv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting){
            openSettingsActivity();
            Log.i("Menu items selected", "Settings");
        }else{
            openHelpActivity();
            Log.i("Menu items selected", "Help");
        }
//        switch (item.getItemId()) {
//            case R.id.action_setting:
//                openSettingsActivity();
//                Log.i("Menu items selected", "Settings");
//            case R.id.help:
//                openHelpActivity();
//                Log.i("Menu items selected", "Help");
//            default:
//                return false;
//        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickButton(View view) {
        openBowlsActivity();
        Log.i("Info", "Button pressed");
    }


    // Open activity section
    public void openBowlsActivity() {
        Intent intent = new Intent(MainActivity.this,bowls.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent settingsIntent = new Intent(MainActivity.this,settings.class);
        startActivity(settingsIntent);
    }

    public void openHelpActivity(){
        Intent intent = new Intent(MainActivity.this,help.class);
        startActivity(intent);
    }

}
