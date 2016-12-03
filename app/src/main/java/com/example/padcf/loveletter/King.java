package com.example.padcf.loveletter;

/**
 * This class creates the King card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
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
        //test
        //System.out.println(currentPlayer.getCard1().getCardName() + " " + currentPlayer.getCard2().getCardName() + " " + targetPlayer2.getCard1().getCardName());

        //having chosen king card, we now want to choose a player to apply that card on.
        //loop around until a player has been chosen. Then do what needs to be done.
        /*
        while (true) {
            System.out.println("Current player: " + currentPlayer.getPlayerName());
            System.out.println("You can target:");
            if(targetPlayer1.getIsPlaying() && !targetPlayer1.isPlayedHandmaid()){
                System.out.println(targetPlayer1.getPlayerName());
            }
            if(targetPlayer2.getIsPlaying() && !targetPlayer2.isPlayedHandmaid()){
                System.out.println(targetPlayer2.getPlayerName());
            }
            if(targetPlayer3.getIsPlaying() && !targetPlayer3.isPlayedHandmaid()){
                System.out.println(targetPlayer3.getPlayerName());
            }
            if((!targetPlayer1.getIsPlaying() || targetPlayer1.isPlayedHandmaid()) && (!targetPlayer2.getIsPlaying() || targetPlayer2.isPlayedHandmaid()) && (!targetPlayer3.getIsPlaying() || targetPlayer3.isPlayedHandmaid())){
                System.out.println("No player can be targeted this round\nYour card is discarded and your turn is over");
                break;
            }

            System.out.println("Choose a player");
            String playerChoice = sc.nextLine();
            playerChoice = playerChoice.toLowerCase();
            //System.out.println(playerChoice);


            //deal with exceptional input
            if (playerChoice.equals(currentPlayer.getPlayerName())) {
                System.out.println("DON'T BE A FOOL - CHOOSE ANOTHER PLAYER");
            }


            //check to see if targetPlayer1 is still in the round and do targetPlayer1 stuff
            else if (playerChoice.equals(targetPlayer1.getPlayerName())) {

                if (!targetPlayer1.getIsPlaying()) {
                    System.out.println("This player is already out of the round");

                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else {
                    //check to see which card slot is currently occupied by king
                    //then swap card which isn't king with target player and vice versa
                    if(currentPlayer.getCard1().getCardName().equals("king")){
                        Card temp = targetPlayer1.getCard1();
                        targetPlayer1.setCard1(currentPlayer.getCard2());
                        currentPlayer.setCard1(temp);

                    }
                    else{
                        Card temp = targetPlayer1.getCard1();
                        targetPlayer1.setCard1(currentPlayer.getCard1());
                        currentPlayer.setCard1(temp);

                    }
                    System.out.println("You have traded hands with " + targetPlayer1.getPlayerName());
                }
            }

            //check to see if targetPlayer2 is still in the round and do targetPlayer2 stuff
            else if (playerChoice.equals(targetPlayer2.getPlayerName())) {

                if (!targetPlayer2.getIsPlaying()) {
                    System.out.println("This player is already out of the round");
                }
                else if(targetPlayer2.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else {
                    //check to see which card slot is currently occupied by king
                    //then swap card which isn't king with target player and vice versa
                    if(currentPlayer.getCard1().getCardName().equals("king")){
                        Card temp = targetPlayer2.getCard1();
                        targetPlayer2.setCard1(currentPlayer.getCard2());
                        currentPlayer.setCard1(temp);
                    }
                    else{
                        Card temp = targetPlayer2.getCard1();
                        targetPlayer2.setCard1(currentPlayer.getCard1());
                        currentPlayer.setCard1(temp);
                    }
                    System.out.println("You have traded hands with " + targetPlayer2.getPlayerName());
                }
            }

            //check to see if targetPlayer3 is still in the round and do targetPlayer3 stuff
            else if (playerChoice.equals(targetPlayer3.getPlayerName())) {

                if (!targetPlayer3.getIsPlaying()) {
                    System.out.println("This player is already out of the round");
                }
                else if(targetPlayer3.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else {
                    //check to see which card slot is currently occupied by king
                    //then swap card which isn't king with target player and vice versa
                    if(currentPlayer.getCard1().getCardName().equals("king")){
                        Card temp = targetPlayer3.getCard1();
                        targetPlayer3.setCard1(currentPlayer.getCard2());
                        currentPlayer.setCard1(temp);
                    }
                    else{
                        Card temp = targetPlayer3.getCard1();
                        targetPlayer3.setCard1(currentPlayer.getCard1());
                        currentPlayer.setCard1(temp);
                    }
                    System.out.println("You have traded hands with " + targetPlayer3.getPlayerName());
                }
            }
            //test
            //System.out.println(currentPlayer.getCard1().getCardName() + " " + targetPlayer2.getCard1().getCardName());
            break;
        }*/
        return length;
    }
}