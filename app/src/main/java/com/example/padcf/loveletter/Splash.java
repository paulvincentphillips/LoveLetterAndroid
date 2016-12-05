package com.example.padcf.loveletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import static com.example.padcf.loveletter.R.drawable.tempestloveletter;

/**
 * Created by Lenovo on 05/12/2016.
 */

public class Splash extends AppCompatActivity {

    //method doesn't work but will implement it later for a different starr
    public void fade (View view) {

        ImageView tempestloveletter = (ImageView) findViewById(R.id.tempestloveletter);

        tempestloveletter.animate().translationXBy(1000f).setDuration(2000);
    }



    /*Splashscreen running onto the main screen with a wait time of 3 secs*/




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread myThread = new Thread() {

            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent startMainScreen = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(startMainScreen);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };      //end of the thread
        myThread.start();
    }
}
