package com.example.padcf.loveletter;

/**
 * This class creates the Prince card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
 */

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Scanner;



public class Prince implements Card{
    private int cardValue = 5;
    private String cardName = "prince";
    private String cardAbility = "Choose any player (including yourself) to discard his or her hand and draw a new card.";
    private int imageId = R.drawable.prince;


    //Jack
    public int getImageId() {
        return imageId;
    }



    Scanner sc = new Scanner(System.in);

    //Scanner sc = new Scanner(System.in);

    @Override
    public int getCardValue() {
        return this.cardValue;
    }

    @Override
    public String getCardAbility() {
        return this.cardAbility;
    }

    @Override
    public String getCardName() {
        return this.cardName;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck, int tag, int cardChoice) {

        //we check to see if deckLength is 0 because:
        //if a prince is played it forces a player to discard their card and draw a new one
        //but what happens if length is 0(deck is empty)?
        //then that player draws the burned card from the start of the game deck[15]
        if(length == 0){
            if(tag == 1){
                if(targetPlayer1.getCard1().getCardValue() == 8){
                    targetPlayer1.setPlaying(false);
                }else {
                    targetPlayer1.setCard1(deck[0]);
                }
            }
            else if(tag == 2){
                if(targetPlayer2.getCard1().getCardValue() == 8){
                    targetPlayer1.setPlaying(false);
                }else {
                    targetPlayer2.setCard1(deck[0]);
                }
            }
            else if(tag == 3){
                if(targetPlayer2.getCard1().getCardValue() == 8){
                    targetPlayer1.setPlaying(false);
                }else {
                    targetPlayer2.setCard1(deck[0]);
                }
            }
            else{
                if(currentPlayer.getCard1().getCardValue() == 8 || currentPlayer.getCard2().getCardValue() == 8)
                {
                    currentPlayer.setPlaying(false);
                }else {
                    currentPlayer.setCard1(deck[0]);
                }
            }
        }else {
            if (tag == 1) {
                if (targetPlayer1.getCard1().getCardValue() == 8) {
                    targetPlayer1.setPlaying(false);
                } else {
                    targetPlayer1.setCard1(deck[length]);
                    length--;
                    System.out.println(targetPlayer1.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                }
            } else if (tag == 2) {
                if (targetPlayer2.getCard1().getCardValue() == 8) {
                    targetPlayer2.setPlaying(false);
                } else {
                    targetPlayer2.setCard1(deck[length]);
                    length--;
                    System.out.println(targetPlayer2.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                }
            } else if (tag == 3) {
                if (targetPlayer3.getCard1().getCardValue() == 8) {
                    targetPlayer3.setPlaying(false);
                } else {
                    targetPlayer3.setCard1(deck[length]);
                    length--;
                    System.out.println(targetPlayer3.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                }
            }
            else
            {
                if(currentPlayer.getCard1().getCardValue() == 8 || currentPlayer.getCard2().getCardValue() == 8)
                {
                    currentPlayer.setPlaying(false);
                }
                else{
                    currentPlayer.setCard1(deck[length]);
                    length--;
                    System.out.println(currentPlayer.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                }

            }
        }

        return length;
    }
}
