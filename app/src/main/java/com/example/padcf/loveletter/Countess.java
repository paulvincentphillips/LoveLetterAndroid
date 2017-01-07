package com.example.padcf.loveletter;

/**
 * This class creates the Countess card which contains functionality and attributes unique to this class
 * The countess doesn't do anything
 * however, she must be discarded if the player has either a king or prince in their other card slot
 * Created by padcf, paulvincentphillips & bradyc12 on 01/11/16.
 */


public class Countess implements Card {
    private int cardValue = 7;
    private String cardName = "countess";
    private String cardAbility = "If you have this card and the King or Prince in your hand, \nyou must discard this card";


    private int imageId = R.drawable.countess;

    @Override
    public int getImageId() {
        return imageId;
    }

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
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck, int tag, int cardChoice, int guardChoice) {

        System.out.println(currentPlayer.getPlayerName() + " has discarded a Countess");
        return length;
    }
}