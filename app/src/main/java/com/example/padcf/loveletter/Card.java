package com.example.padcf.loveletter;

/**
 * The card class will be created to give certain state and functionality to all cards and
 * classes which inherit from the Cards class.
 * Created by padcf & paulvincentphillips on 01/11/16.
 */

public interface Card {

    int getCardValue();
    String getCardAbility();
    String getCardName();
    int getImageID();

    int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck);

}
