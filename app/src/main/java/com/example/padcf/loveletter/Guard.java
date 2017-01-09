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
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck, int tag, int cardChoice, int guardChoice) {

        //compare guardChoice int to the target player's card int
        //if they are the same, eliminate the other player
        System.out.println("yes, this finally works " + guardChoice);

        if(tag == 1){
            if(targetPlayer1.getCardChoice() == 1){
                if(guardChoice == targetPlayer1.getCard2().getCardValue()){
                    targetPlayer1.setPlaying(false);
                    System.out.println(targetPlayer1.getPlayerName() + " is eliminated");
                }
            }else{
                if(guardChoice == targetPlayer1.getCard1().getCardValue()){
                    targetPlayer1.setPlaying(false);
                    System.out.println(targetPlayer1.getPlayerName() + " is eliminated");
                }
            }
        }
        else if(tag == 2){
            if(targetPlayer2.getCardChoice() == 1){
                if(guardChoice == targetPlayer2.getCard2().getCardValue()){
                    targetPlayer2.setPlaying(false);
                    System.out.println(targetPlayer2.getPlayerName() + " is eliminated");
                }
            }else{
                if(guardChoice == targetPlayer2.getCard1().getCardValue()){
                    targetPlayer2.setPlaying(false);
                    System.out.println(targetPlayer2.getPlayerName() + " is eliminated");
                }
            }
        }else{
            if(targetPlayer3.getCardChoice() == 1){
                if(guardChoice == targetPlayer3.getCard2().getCardValue()){
                    targetPlayer3.setPlaying(false);
                    System.out.println(targetPlayer3.getPlayerName() + " is eliminated");
                }
            }else{
                if(guardChoice == targetPlayer3.getCard1().getCardValue()){
                    targetPlayer3.setPlaying(false);
                    System.out.println(targetPlayer3.getPlayerName() + " is eliminated");
                }
            }
        }

        return length;
    }
}
