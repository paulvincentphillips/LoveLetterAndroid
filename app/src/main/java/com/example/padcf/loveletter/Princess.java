package com.example.padcf.loveletter;

/**
 * This class creates the Princess card which contains functionality and attributes unique to this class
 * If you discard the princess, you are out of the round
 * If another player forces you to discard the princess, ie. through the use of a prince
 * Then you are also out of the game
 * Created by padcf, paulvincentphillips & bradyc12 on 01/11/16.
 */

public class Princess implements Card {
    private int cardValue = 8;
    private String cardName = "princess";
    private String cardAbility = "If you discard this card, you are out of the round";
    private int imageId = R.drawable.princess;

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

        System.out.println("You have discarded a Princess \nYou are out of the round!");
        currentPlayer.setPlaying(false);
        return length;
    }
}

