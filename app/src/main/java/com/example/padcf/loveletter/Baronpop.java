package com.example.padcf.loveletter;

//******
// this is an activity which will display the results of playing the Baron Card to the user
//******

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by padcf on 03/01/2017.
 */
public class Baronpop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.baronpop);


        //you want to get the size of the phone screen here, then set the pop up to be a percentage of that size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);//get display metrics for screen,
        //store as width and height below
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.8)); //set the size of popup

        //below we are catching all necessary information form the main activity intent and storing it to use in this activity.
        Bundle baronInfo = this.getIntent().getExtras();
        String playerName = baronInfo.getString("playerName");
        String targetName = baronInfo.getString("targetName");
        String playerCardName = baronInfo.getString("playerCardName");
        String targetPlayerCardName = baronInfo.getString("targetPlayerCardName");
        int playerCardAbility = baronInfo.getInt("playerCardAbility");
        int targetCardAbility = baronInfo.getInt("targetCardAbility");
        int playerCardImage = baronInfo.getInt("playerCardImage");
        int targetCardImage = baronInfo.getInt("targetCardImage");

        //create the text views for the baron card popup activity
        //set the text to show the player names and player cards and the card scores/abilities
        TextView playerOneName = (TextView) findViewById(R.id.baronPlayerOne);
        playerOneName.setText(playerName + "'s\ncard: ");
        TextView textTargetName = (TextView) findViewById(R.id.baronTargetPlayer);
        textTargetName.setText(targetName + "'s\ncard: ");
        TextView playerOneCardName = (TextView) findViewById(R.id.baronPlayerOneCard);
        playerOneCardName.setText(playerCardName.toUpperCase() + " (" + playerCardAbility + ")");
        TextView targetCardName = (TextView) findViewById(R.id.baronTargetPlayerCard);
        targetCardName.setText(targetPlayerCardName.toUpperCase() + " (" + targetCardAbility + ")");

        //depending on which card has a higher score, inform the user on who has been knocked out of the round.
        //the actual functionality has already been done by the Baron class. This is only to show the user what has happened
        TextView result = (TextView) findViewById(R.id.baronResult);

        if(playerCardAbility > targetCardAbility)
        {
            result.setText(playerName + " has the higher card." + " \n\n\n" + targetName + " is out of this round.");
        }
        else if(playerCardAbility < targetCardAbility)
        {
            result.setText(playerName + " has the lower card." + " \n\n\n" + playerName + " is out of this round.");
        }
        else
        {
            result.setText("It is a draw. No one is out of this round.");
        }





    }
}
