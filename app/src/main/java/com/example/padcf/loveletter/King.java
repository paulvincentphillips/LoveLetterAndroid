package com.example.padcf.loveletter;

/**
 * This class creates the King card which contains functionality and attributes unique to this class
 * You may trade hands with another player of your choosing
 * Created by padcf, paulvincentphillips & bradyc12 on 01/11/16.
 */

import java.util.Scanner;

public class King implements Card {
    private int cardValue = 6;
    private String cardName = "king";
    private String cardAbility = "Trade hands with another player of your choice.";
    private int imageId = R.drawable.king;

    Scanner sc = new Scanner(System.in);

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

        if(tag == 1){
            if(cardChoice == 1){
                Card temp = targetPlayer1.getCard1();
                targetPlayer1.setCard1(currentPlayer.getCard2());
                currentPlayer.setCard1(temp);
            }else{
                Card temp = targetPlayer1.getCard1();
                targetPlayer1.setCard1(currentPlayer.getCard1());
                currentPlayer.setCard1(temp);
            }
        }
        else if(tag == 2){
            if(cardChoice == 1){
                Card temp = targetPlayer2.getCard1();
                targetPlayer2.setCard1(currentPlayer.getCard2());
                currentPlayer.setCard1(temp);
            }else{
                Card temp = targetPlayer2.getCard1();
                targetPlayer2.setCard1(currentPlayer.getCard1());
                currentPlayer.setCard1(temp);
            }
        }else{
            if(cardChoice == 1){
                Card temp = targetPlayer3.getCard1();
                targetPlayer3.setCard1(currentPlayer.getCard2());
                currentPlayer.setCard1(temp);
            }else{
                Card temp = targetPlayer3.getCard1();
                targetPlayer3.setCard1(currentPlayer.getCard1());
                currentPlayer.setCard1(temp);
            }
        }

        return length;
    }
}