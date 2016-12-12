package com.example.padcf.loveletter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Lenovo on 10/12/2016.
 */

public class Menu extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);



        final MediaPlayer buttonSound = MediaPlayer.create(this,R.raw.button);

        //Declaring the imagebuttons used on the menu screen-PlayGame, Tutorial
        ImageButton PlayGame = (ImageButton) findViewById(R.id.play);
        ImageButton videotutorial = (ImageButton) this.findViewById(R.id.tutorial);


        PlayGame.setOnClickListener(this);
        videotutorial.setOnClickListener(this);
    }


    //By clicking the game button on the Menu screen, it will begin a new game from the MainActivity class.
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.play:
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
                break;

     //By clicking the tutorial button on the Menu screen, it will redirect to the below youtube tutorial.
            case R.id.tutorial:
                Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6XHpHrKtIEM&t=502s"));
                startActivity(webintent);
                break;


        }
    }
}





