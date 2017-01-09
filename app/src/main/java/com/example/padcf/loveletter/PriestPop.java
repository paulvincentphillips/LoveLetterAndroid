package com.example.padcf.loveletter;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by padcf on 05/01/2017.
 */
public class PriestPop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.priestpop);

        //you want to get the size of the phone screen here, then set the pop up to be a percentage of that size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);//get display metrics for screen,
        //store as width and height below
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.4)); //set the size of popup

        //catch bundle form mainActivity
        Bundle priestInfo = this.getIntent().getExtras();
        //put the information into variables
        String targetPlayerName = priestInfo.getString("targetName");
        String targetPlayerCardName1 = priestInfo.getString("targetCard1");
        String targetPlayerCardName2 = priestInfo.getString("targetCard2");
        int targetPlayerChoice = priestInfo.getInt("targetChoice");

        TextView revealCard = (TextView) findViewById(R.id.priestReveal);

        if(targetPlayerChoice == 1){
            revealCard.setText(targetPlayerName + " has a... " + targetPlayerCardName2.toUpperCase() );
        }else{
            revealCard.setText(targetPlayerName + " has a... " + targetPlayerCardName1.toUpperCase() );
        }


    }
}
