package com.example.padcf.loveletter;

/**
 * This class creates the Prince card which contains functionality and attributes unique to this class
 * Created by padcf & paulvincentphillips on 01/11/16.
 */

import java.util.Scanner;

public class Prince implements Card{
    private int cardValue = 5;
    private String cardName = "prince";
    private String cardAbility = "Choose any player (including yourself) to discard his or her hand and draw a new card.";
    private int imageID = R.drawable.prince;

    Scanner sc = new Scanner(System.in);

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
    public int getImageID() {
        return this.imageID;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck) {



        //having chosen prince card, we now want to choose a player to apply that card on.
        //loop around until a player has been chosen. Then do what needs to be done.

        while(true) {
            System.out.println("Current player: " + currentPlayer.getPlayerName());
            System.out.println("You can target:");
            System.out.println("Yourself");
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

            //unlike most other cards, the prince may target himself
            //we do not need to check if current player is still in round
            //deal target a new card, replacing current card
            if (playerChoice.equals(currentPlayer.getPlayerName())){
                //check if you are discarding a princess
                //if above is true you are out of the round... numpty!
                if(currentPlayer.getCard1().getCardName().equals("prince") && currentPlayer.getCard2().getCardName().equals("princess")){
                    currentPlayer.setPlaying(false);
                    System.out.println("You've discarded a princess \nYou are out of the game");
                    break;
                }
                else if(currentPlayer.getCard2().getCardName().equals("prince") && currentPlayer.getCard1().getCardName().equals("princess")){
                    currentPlayer.setPlaying(false);
                    System.out.println("You've discarded a princess \nYou are out of the game");
                    break;
                }
                else {
                    currentPlayer.setCard1(deck[length]);
                    length--;
                    System.out.println("You have discarded your hand and drawn a " + currentPlayer.getCard1().getCardName());
                    break;
                }
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
                    //deal target a new card, replacing current card
                    if (playerChoice.equals(targetPlayer1.getPlayerName())){
                        //check if you have made target player discard a princess, setting isPlaying to false if true
                        if(targetPlayer1.getCard1().getCardName().equals("princess")){
                            System.out.println("Target player has discarded a princess because of you! \nTarget player is now out of the game! \n*shakes fist angrily");
                            targetPlayer1.setPlaying(false);
                            break;
                        }
                        //else deal player a new card
                        else{
                            targetPlayer1.setCard1(deck[length]);
                            length--;
                            System.out.println(targetPlayer1.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                            break;
                        }
                    }
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
                    //deal target a new card, replacing current card
                    if (playerChoice.equals(targetPlayer2.getPlayerName())){
                        //check if you have made target player discard a princess, setting isPlaying to false if true
                        if(targetPlayer2.getCard1().getCardName().equals("princess")){
                            System.out.println("Target player has discarded a princess because of you! \nTarget player is now out of the game! \n*shakes fist angrily");
                            targetPlayer2.setPlaying(false);
                            break;
                        }
                        //else deal player a new card
                        else{
                            targetPlayer2.setCard1(deck[length]);
                            length--;
                            System.out.println(targetPlayer2.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                            break;
                        }
                    }
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
                    //deal target a new card, replacing current card
                    if (playerChoice.equals(targetPlayer3.getPlayerName())){
                        //check if you have made target player discard a princess, setting isPlaying to false if true
                        if(targetPlayer3.getCard1().getCardName().equals("princess")){
                            System.out.println("Target player has discarded a princess because of you! \nTarget player is now out of the game! \n*shakes fist angrily");
                            targetPlayer3.setPlaying(false);
                            break;
                        }
                        //else deal player a new card
                        else{
                            targetPlayer3.setCard1(deck[length]);

                            System.out.println(targetPlayer3.getPlayerName() + " has discarded their hand and drawn a new one from the deck");
                            break;
                        }
                    }
                }
            }
        }
        return length;
    }
}
