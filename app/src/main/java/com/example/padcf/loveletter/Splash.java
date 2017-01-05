package com.example.padcf.loveletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * The Love Letter splash screen
 * If you discard this card, you are out of the round 05/12/2016.
 */

public class Splash extends AppCompatActivity {

    //Splashscreen alternative fade method: will fade out to the right if clicked on.
    public void fade(View view) {

        ImageView tempestloveletter = (ImageView) findViewById(R.id.tempestloveletter);
        tempestloveletter.animate().translationXBy(1000f).setDuration(1000);
    }

    //splash_screen running into the main screen with a wait time of 3 secs
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread myThread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent startMenuScreen = new Intent(getApplicationContext(), Menu.class);
                    startActivity(startMenuScreen);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };      //end of the thread
        myThread.start();
    }
}
