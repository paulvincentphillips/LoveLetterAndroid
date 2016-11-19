package com.example.padcf.loveletter;

/**
 * This class creates the Princess card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
 */

public class Princess implements Card {
    private int cardValue = 8;
    private String cardName = "princess";
    private String cardAbility = "If you discard this card, you are out of the round";


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
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck) {

        System.out.println("You have discarded a Princess \nYou are out of the round!");
        currentPlayer.setPlaying(false);
        return length;
    }
}
