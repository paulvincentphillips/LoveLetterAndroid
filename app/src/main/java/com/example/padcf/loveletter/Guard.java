package com.example.padcf.loveletter;

/**
 * This class creates the Guard card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
 */


public class Guard implements Card {

    private int cardValue = 1;
    private String cardName = "guard";
    private String cardAbility = "Name a non-Guard card and choose another player. \nIf that player has that card, he or she is out of the round.";
    private int imageId = R.drawable.guard;


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



        System.out.println("You've played a guard");





        return length;
    }
}
