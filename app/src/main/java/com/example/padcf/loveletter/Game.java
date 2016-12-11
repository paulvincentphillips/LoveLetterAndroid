package com.example.padcf.loveletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Lenovo on 10/12/2016.
 */

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);


        Button PlayGame = (Button) findViewById(R.id.play);
        PlayGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent( Game.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
