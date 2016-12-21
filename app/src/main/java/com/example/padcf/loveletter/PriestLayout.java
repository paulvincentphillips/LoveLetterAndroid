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

        //create four textViews to display the information
        TextView one = (TextView) findViewById(R.id.chosenplayercards);

        //catch the bundle
        Bundle priestBundle = this.getIntent().getExtras();


        //sort out player played cards from the bundle here,
        String chosenPlayerCards = priestBundle.getString("stringCurrentPlayer");

        //sort out player names from the bundle here,
        String currentPlayerName = priestBundle.getString("nameCurrentPlayer");


        //set the text on the textview to show the player name and current cards
        one.setText(currentPlayerName + "'s current cards: " + chosenPlayerCards);
    }
}

