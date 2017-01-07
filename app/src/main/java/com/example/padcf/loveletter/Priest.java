package com.example.padcf.loveletter;

/**
 * This class creates the Priest card which contains functionality and attributes unique to this class
 * The priest simply allows you to look at another player's hand
 * Created by padcf, paulvincentphillips & bradyc12 on 01/11/16.
 */


public class Priest implements Card {
    private int cardValue = 2;
    private String cardName = "priest";
    private String cardAbility = "Look at another player's hand.";
    private int imageId = R.drawable.priest;

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

        //java rewrite follows

        if(tag == 1){
            System.out.println(targetPlayer1.getPlayerName() + " has a " + targetPlayer1.getCard1().getCardName());
        }
        else if(tag ==2){
            System.out.println(targetPlayer2.getPlayerName() + " has a " + targetPlayer2.getCard1().getCardName());
        }else{
            System.out.println(targetPlayer3.getPlayerName() + " has a " + targetPlayer3.getCard1().getCardName());
        }

        return length;
    }
}
