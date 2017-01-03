package com.example.padcf.loveletter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class CurrentPlayersCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_players_cards);

        //you want to get the size of the phone screen here, then set the pop up to be a percentage of that size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);//get display metrics for screen,
        //store as width and height below
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.8)); //set the size of popup

      /*  //initial cards dealt
        System.out.println("Player 1 has " +  deck1[0]);
        System.out.println("Player 2 has " +  deck1[1]);
        System.out.println("Player 3 has " +  deck1[2]);
        System.out.println("Player 4 has " +  deck1[3]);*/

        //create four textViews to display the information
        TextView one = (TextView)findViewById(R.id.pCC1);
        TextView two = (TextView)findViewById(R.id.pCC2);
        TextView three = (TextView)findViewById(R.id.pCC3);
        TextView four = (TextView)findViewById(R.id.pCC4);

        //catch the bundle
        Bundle priestBundle = this.getIntent().getExtras();

        //sort out player played cards from the bundle here,
        String currentPlayerCurrentCards = priestBundle.getString("stringCurrentPlayer");
        String playerTwoCurrentCards = priestBundle.getString("stringPlayer2");
        String playerThreeCurrentCards = priestBundle.getString("stringPlayer3");
        String playerFourCurrentCards = priestBundle.getString("stringPlayer4");

        //sort out player names from the bundle here,
        String currentPlayerName = priestBundle.getString("nameCurrentPlayer");
        String playerTwoName = priestBundle.getString("namePlayer2");
        String playerThreeName = priestBundle.getString("namePlayer3");
        String playerFourName = priestBundle.getString("namePlayer4");

        //set the text on each textview to show the player name and previously burned cards
        one.setText(currentPlayerName + "'s current card: " + currentPlayerCurrentCards);
        two.setText(playerTwoName + "'s current card: " + playerTwoCurrentCards);
        three.setText(playerThreeName + "'s current card: " + playerThreeCurrentCards);
        four.setText(playerFourName + "'s current card: " + playerFourCurrentCards);
}
}
