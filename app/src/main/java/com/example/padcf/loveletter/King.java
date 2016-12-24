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

        //player chose player 1(tag 1)
        if(tag == 1){
            //if king is in cardslot1 swap cardslot2
            if(cardChoice == 1){
                //if target player has played card 1 previously swap his card 2 as card 1 no longer exists
                if(targetPlayer1.getCardChoice() == 1){
                    Card temp = targetPlayer1.getCard2();
                    targetPlayer1.setCard2(currentPlayer.getCard2());
                    currentPlayer.setCard2(temp);
                //else player has either not chosen a card to play yet or he has chosen card 2
                }else{
                    Card temp = targetPlayer1.getCard1();
                    targetPlayer1.setCard1(currentPlayer.getCard2());
                    currentPlayer.setCard2(temp);
                }
                //if king is in cardslot2 swap cardslot1
            }else{
                //if target player has played card 1 previously swap his card 2 as card 1 no longer exists
                if(targetPlayer1.getCardChoice() == 1){
                    Card temp = targetPlayer1.getCard2();
                    targetPlayer1.setCard2(currentPlayer.getCard1());
                    currentPlayer.setCard1(temp);
                    //else player has either not chosen a card to play yet or he has chosen card 2
                }else{
                    Card temp = targetPlayer1.getCard1();
                    targetPlayer1.setCard1(currentPlayer.getCard1());
                    currentPlayer.setCard1(temp);
                }
            }
        }
        else if(tag == 2){
            //if king is in cardslot1 swap cardslot2
            if(cardChoice == 1){
                //if target player has played card 1 previously swap his card 2 as card 1 no longer exists
                if(targetPlayer2.getCardChoice() == 1){
                    Card temp = targetPlayer2.getCard2();
                    targetPlayer2.setCard2(currentPlayer.getCard2());
                    currentPlayer.setCard2(temp);
                    //else player has either not chosen a card to play yet or he has chosen card 2
                }else{
                    Card temp = targetPlayer2.getCard1();
                    targetPlayer2.setCard1(currentPlayer.getCard2());
                    currentPlayer.setCard2(temp);
                }
                //if king is in cardslot2 swap cardslot1
            }else{
                if(targetPlayer2.getCardChoice() == 1){
                    Card temp = targetPlayer2.getCard2();
                    targetPlayer2.setCard2(currentPlayer.getCard1());
                    currentPlayer.setCard1(temp);
                    //else player has either not chosen a card to play yet or he has chosen card 2
                }else{
                    Card temp = targetPlayer2.getCard1();
                    targetPlayer2.setCard1(currentPlayer.getCard1());
                    currentPlayer.setCard1(temp);
                }
            }
        }else{
            //if king is in cardslot1 swap cardslot2
            if(cardChoice == 1){
                //if target player has played card 1 previously swap his card 2 as card 1 no longer exists
                if(targetPlayer3.getCardChoice() == 1){
                    Card temp = targetPlayer3.getCard2();
                    targetPlayer3.setCard2(currentPlayer.getCard2());
                    currentPlayer.setCard2(temp);
                    //else player has either not chosen a card to play yet or he has chosen card 2
                }else{
                    Card temp = targetPlayer3.getCard1();
                    targetPlayer3.setCard1(currentPlayer.getCard2());
                    currentPlayer.setCard2(temp);
                }
                //if king is in cardslot2 swap cardslot1
            }else{
                if(targetPlayer3.getCardChoice() == 1){
                    Card temp = targetPlayer3.getCard2();
                    targetPlayer3.setCard2(currentPlayer.getCard1());
                    currentPlayer.setCard1(temp);
                    //else player has either not chosen a card to play yet or he has chosen card 2
                }else{
                    Card temp = targetPlayer3.getCard1();
                    targetPlayer3.setCard1(currentPlayer.getCard1());
                    currentPlayer.setCard1(temp);
                }
            }
        }

        return length;
    }
}