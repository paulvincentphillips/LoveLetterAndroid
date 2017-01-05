package com.example.padcf.loveletter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 21/12/2016.
 */

public class PriestLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.priestlayout); //need to set up a view

        //create player textView to display the player's Cards
        TextView one = (TextView) findViewById(R.id.chosenplayercards);

        //catch the bundle
        Bundle priestBundle = this.getIntent().getExtras();

        //sort out player's current cards from the bundle here,
        String chosenPlayerCards = priestBundle.getString("stringCurrentPlayer");

        //sort out player names from the bundle here,
        String currentPlayerName = priestBundle.getString("nameCurrentPlayer");
        String chosenPlayerName = priestBundle.getString("chosenPlayerName");

        //set the text on the textview to show the player name and current cards
       // one.setText(currentPlayerName + "'s current cards: " + currentPlayerPlayedCard);
        one.setText(chosenPlayerName + "'s current cards: " + chosenPlayerCards);
    }
}

