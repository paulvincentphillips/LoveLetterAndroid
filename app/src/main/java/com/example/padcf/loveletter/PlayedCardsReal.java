package com.example.padcf.loveletter;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by padcf on 14/12/2016.
 */
public class PlayedCardsReal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.playedcardsreal);


        //you want to get the size of the phone screen here, then set the pop up to be a percentage of that size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);//get display metrics for screen,
        //store as width and height below
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.8)); //set the size of popup

        //create four textViews to display the information
        TextView one = (TextView)findViewById(R.id.pC1);
        TextView two = (TextView)findViewById(R.id.pC2);
        TextView three = (TextView)findViewById(R.id.pC3);
        TextView four = (TextView)findViewById(R.id.pC4);

        //catch the bundle
        Bundle bundlePlayedCards = this.getIntent().getExtras();

        //sort out player played cards from the bundle here,
        String currentPlayerPlayedCard = bundlePlayedCards.getString("stringCurrentPlayer");
        String playerTwoPlayedCard = bundlePlayedCards.getString("stringPlayer2");
        String playerThreePlayedCard = bundlePlayedCards.getString("stringPlayer3");
        String playerFourPlayedCard = bundlePlayedCards.getString("stringPlayer4");

        //sort out player names from the bundle here,
        String currentPlayerName = bundlePlayedCards.getString("nameCurrentPlayer");
        String playerTwoName = bundlePlayedCards.getString("namePlayer2");
        String playerThreeName = bundlePlayedCards.getString("namePlayer3");
        String playerFourName = bundlePlayedCards.getString("namePlayer4");

        //set the text on each textview to show the player name and previously burned cards
        one.setText(currentPlayerName + "'s played cards: " + currentPlayerPlayedCard);
        two.setText(playerTwoName + "'s played cards: " + playerTwoPlayedCard);
        three.setText(playerThreeName + "'s played cards: " + playerThreePlayedCard);
        four.setText(playerFourName + "'s played cards: " + playerFourPlayedCard);


    }
}
