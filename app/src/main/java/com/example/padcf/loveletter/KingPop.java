package com.example.padcf.loveletter;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by padcf on 05/01/2017.
 */
public class KingPop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.kingpop);

        //you want to get the size of the phone screen here, then set the pop up to be a percentage of that size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);//get display metrics for screen,
        //store as width and height below
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.4)); //set the size of popup

        //below we are catching all necessary information form the main activity intent and storing it to use in this activity.
        Bundle kingInfo = this.getIntent().getExtras();
        //String playerName = kingInfo.getString("playerName");
        String targetName = kingInfo.getString("targetName");

        //TextView kingInfoText = (TextView) findViewById(R.id.kingInfo);
        //kingInfoText.setText("You have traded cards with " + targetName);
    }
}
