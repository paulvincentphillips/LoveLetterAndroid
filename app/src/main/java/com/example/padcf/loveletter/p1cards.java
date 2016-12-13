package com.example.padcf.loveletter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class p1cards extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1cards);

        //if(!playerOrder[turnOrder].isPlayedCardsArrayEmpty()){
        System.out.println(playerOrder[turnOrder].getPlayedCards());
        //}
    }
}
