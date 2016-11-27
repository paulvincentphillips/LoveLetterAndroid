package com.example.padcf.loveletter;

/**
 * This class creates the Countess card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
 */

public class Countess implements Card {
    private int cardValue = 7;
    private String cardName = "countess";
    private String cardAbility = "If you have this card and the King or Prince in your hand, \nyou must discard this card";
    private int imageID = R.drawable.countess;


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
    public int getImageID() {
        return this.imageID;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck) {

        System.out.println(currentPlayer.getPlayerName() + " has discarded a Countess");
        return length;
    }
}