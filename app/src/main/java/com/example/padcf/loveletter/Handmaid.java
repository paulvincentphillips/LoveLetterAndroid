package com.example.padcf.loveletter;

/**
 * This class creates the Handmaid card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
 */

public class Handmaid implements Card{
    private int cardValue = 4;
    private String cardName = "handmaid";
    private String cardAbility = "Until your next turn, ignore all effects from other players' cards.";
    private int imageId = R.drawable.handmaid;


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
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck, int tag, int cardChoice) {
        currentPlayer.setPlayedHandmaid(true);
        System.out.println("You are immune this until your next turn");
        //System.out.println(currentPlayer.isPlayedHandmaid());
        return length;
    }
}