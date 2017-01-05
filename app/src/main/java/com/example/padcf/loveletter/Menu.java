package com.example.padcf.loveletter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Main menu intent for the game
 * Will allow you to view a YouTube tutorial for the game and begin a new game
 * Created by padcf, paulvincentphillips & bradyc12 on 10/12/2016.
 */

public class Menu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);
        final MediaPlayer buttonSound = MediaPlayer.create(this,R.raw.button);

        //Declaring the imagebuttons used on the menu screen-PlayGame, Tutorial
        ImageButton PlayGame = (ImageButton) this.findViewById(R.id.play);
        ImageButton videotutorial = (ImageButton) this.findViewById(R.id.tutorial);


        PlayGame.setOnClickListener(this);
        videotutorial.setOnClickListener(this);
    }

    //By clicking the game button on the Menu screen, it will begin a new game from the MainActivity class.
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.play:
                Intent playerName = new Intent(Menu.this, playerName.class);
                startActivity(playerName);
                break;

     //By clicking the tutorial button on the Menu screen, it will redirect to the below youtube tutorial.
            case R.id.tutorial:
                Intent Video = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=5sgbgXsDxfc"));
                startActivity(Video);
                break;
        }
    }
}





