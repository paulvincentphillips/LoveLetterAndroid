package com.example.padcf.loveletter;


public class Baron implements Card {
    private int cardValue = 3;
    private String cardName = "baron";
    private String cardAbility = "You and another player secretly compare hands. \nThe player with the lower value is out of the round.";
    private int imageId = R.drawable.baron;


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
    public int getImageId() {
        return imageId;
    }

    @Override
    public int specialFunction(Player currentPlayer, Player targetPlayer1, Player targetPlayer2, Player targetPlayer3, int length, Card[] deck, int tag, int cardChoice) {

        //android implementation/ rewrite follows:

        if(cardChoice == 1) {
            if (tag == 1) {
                if (currentPlayer.getCard2().getCardValue() > targetPlayer1.getCard1().getCardValue()) {
                    targetPlayer1.setPlaying(false);
                    System.out.println(targetPlayer1.getPlayerName() + " is out of the round");
                } else if (currentPlayer.getCard2().getCardValue() < targetPlayer1.getCard1().getCardValue()) {
                    currentPlayer.setPlaying(false);
                    System.out.println("You are out of the round");
                } else {
                    System.out.println("Draw");
                }
            } else if (tag == 2) {
                if (currentPlayer.getCard2().getCardValue() > targetPlayer2.getCard1().getCardValue()) {
                    targetPlayer2.setPlaying(false);
                    System.out.println(targetPlayer2.getPlayerName() + " is out of the round");
                } else if (currentPlayer.getCard2().getCardValue() < targetPlayer2.getCard1().getCardValue()) {
                    currentPlayer.setPlaying(false);
                    System.out.println("You are out of the round");
                } else {
                    System.out.println("Draw");
                }
            } else {
                if (currentPlayer.getCard2().getCardValue() > targetPlayer3.getCard1().getCardValue()) {
                    targetPlayer3.setPlaying(false);
                    System.out.println(targetPlayer3.getPlayerName() + " is out of the round");
                } else if (currentPlayer.getCard2().getCardValue() < targetPlayer3.getCard1().getCardValue()) {
                    currentPlayer.setPlaying(false);
                    System.out.println("You are out of the round");
                } else {
                    System.out.println("Draw");
                }
            }
        }
        else if(cardChoice == 2){
            if (currentPlayer.getCard1().getCardValue() > targetPlayer1.getCard1().getCardValue()) {
                targetPlayer1.setPlaying(false);
                System.out.println(targetPlayer1.getPlayerName() + " is out of the round");
            } else if (currentPlayer.getCard1().getCardValue() < targetPlayer1.getCard1().getCardValue()) {
                currentPlayer.setPlaying(false);
                System.out.println("You are out of the round");
            } else {
                System.out.println("Draw");
            }
        } else if (tag == 2) {
            if (currentPlayer.getCard1().getCardValue() > targetPlayer2.getCard1().getCardValue()) {
                targetPlayer2.setPlaying(false);
                System.out.println(targetPlayer2.getPlayerName() + " is out of the round");
            } else if (currentPlayer.getCard1().getCardValue() < targetPlayer2.getCard1().getCardValue()) {
                currentPlayer.setPlaying(false);
                System.out.println("You are out of the round");
            } else {
                System.out.println("Draw");
            }
        } else {
            if (currentPlayer.getCard1().getCardValue() > targetPlayer3.getCard1().getCardValue()) {
                targetPlayer3.setPlaying(false);
                System.out.println(targetPlayer3.getPlayerName() + " is out of the round");
            } else if (currentPlayer.getCard1().getCardValue() < targetPlayer3.getCard1().getCardValue()) {
                currentPlayer.setPlaying(false);
                System.out.println("You are out of the round");
            } else {
                System.out.println("Draw");
            }
        }


        return length;


    }
}
