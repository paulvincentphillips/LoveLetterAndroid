package com.example.padcf.loveletter;

/**
 * This class creates the Priest card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
 */

import java.util.Scanner;

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
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck) {



        //the current player gets to view the card of the target player
        Scanner sc = new Scanner(System.in);
        while(true)
        {
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
            if(playerChoice.equals(currentPlayer.getPlayerName()))
            {
                System.out.println("DON'T BE A FOOL - CHOOSE ANOTHER PLAYER");
            }




            //check to see if targetPlayer1 is still in the round and do targetPlayer1 stuff
            else if(playerChoice.equals(targetPlayer1.getPlayerName()))
            {

                if(!targetPlayer1.getIsPlaying())
                {
                    System.out.println("This player is already out of the round");

                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else
                {
                    System.out.println(targetPlayer1.getPlayerName() + "'s card is: " + targetPlayer1.getCard1().getCardName());
                    break;
                }
            }

            //check to see if targetPlayer2 is still in the round and do targetPlayer2 stuff
            else if(playerChoice.equals(targetPlayer2.getPlayerName()))
            {

                if(!targetPlayer2.getIsPlaying())
                {
                    System.out.println("This player is already out of the round");

                }
                else if(targetPlayer2.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else
                {
                    System.out.println(targetPlayer2.getPlayerName() + "'s card is: " + targetPlayer2.getCard1().getCardName());
                    break;
                }
            }

            //check to see if targetPlayer3 is still in the round and do targetPlayer3 stuff
            else if(playerChoice.equals(targetPlayer3.getPlayerName()))
            {

                if(!targetPlayer3.getIsPlaying())
                {
                    System.out.println("This player is already out of the round");

                }
                else if(targetPlayer3.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else
                {
                    System.out.println(targetPlayer3.getPlayerName() + "'s card is: " + targetPlayer3.getCard1().getCardName());
                    break;
                }
            }





            else if(!playerChoice.equals(targetPlayer1.getPlayerName()) || !playerChoice.equals(targetPlayer2.getPlayerName()) || !playerChoice.equals(targetPlayer3.getPlayerName()) || !playerChoice.equals(currentPlayer.getPlayerName()))
            {
                //System.out.println("Player choice: " + playerChoice);
                //System.out.println("targetPlayer1: " + targetPlayer1.getPlayerName());
                //System.out.println("targetPlayer2: " + targetPlayer2.getPlayerName());
                //System.out.println("targetPlayer3: " + targetPlayer3.getPlayerName());
                //System.out.println("current player : " + currentPlayer.getPlayerName());
                //System.out.println(!playerChoice.equals(targetPlayer1.getPlayerName()) || !playerChoice.equals(targetPlayer2.getPlayerName()) || !playerChoice.equals(targetPlayer3.getPlayerName()) || !playerChoice.equals(currentPlayer.getPlayerName()));
                System.out.println("What?!");
            }


        }

        return length;
    }
}
