package com.example.padcf.loveletter;

import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.padcf.loveletter.R.id.cardsPlayedP1;
import static com.example.padcf.loveletter.R.id.cardsPlayedP2;
import static com.example.padcf.loveletter.R.id.cardsPlayedP3;
import static com.example.padcf.loveletter.R.id.cardsPlayedP4;
import static com.example.padcf.loveletter.R.styleable.View;

public class playedCards extends MainActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //test to see what the recorded player cards are at any point during the game
        Button p1Cards = (Button) findViewById(R.id.cardsPlayedP1);
        Button p2Cards = (Button) findViewById(R.id.cardsPlayedP2);
        Button p3Cards = (Button) findViewById(R.id.cardsPlayedP3);
        Button p4Cards = (Button) findViewById(R.id.cardsPlayedP4);


        p1Cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!playerOrder[turnOrder].isPlayedCardsArrayEmpty()){
                System.out.println(playerOrder[turnOrder].getPlayedCards());
                //}

            }
        });
        p2Cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!playerOrder[turnOrder].isPlayedCardsArrayEmpty()){
                System.out.println(playerOrder[turnOrder2].getPlayedCards());
                //}

            }
        });
        p3Cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!playerOrder[turnOrder].isPlayedCardsArrayEmpty()){
                System.out.println(playerOrder[turnOrder3].getPlayedCards());
                //}

            }
        });
        p4Cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!playerOrder[turnOrder].isPlayedCardsArrayEmpty()){
                System.out.println(playerOrder[turnOrder4].getPlayedCards());
                //}

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}