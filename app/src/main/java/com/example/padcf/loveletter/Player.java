package com.example.padcf.loveletter;


/**
 * Created by padcf on 19/11/2016.
 */

public class Player {

    private int playerScore = 0;
    private String playerName;
    private boolean isPlaying = true;
    private Card card1;
    private Card card2;
    private boolean playedHandmaid = false;
    private int cardChoice = 0;

    //array to store cards played by a player + counter to store place in array
    private  Card[] playedCards = new Card[16];

    private int playedCardsArrayLength = -1;


    //array to store cards that a current player has + counter to store place in array
    private Card[] currentCards = new Card [16];
   private int currentCardsArrayLength = -1;


    //getter and setter methods for state

    public int getCardChoice(){
        return cardChoice;
    }

    public void setCardChoice(int cardChoice){
        this.cardChoice = cardChoice;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Player(String name)
    {
        playerName = name;
    }


    public void setScore(int score){
        playerScore = score;
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public void setPlayerName(String name){
        playerName = name;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setPlaying(boolean playing){
        isPlaying = playing;
    }

    public boolean getIsPlaying(){
        return isPlaying;
    }

    public boolean isPlayedHandmaid() {
        return playedHandmaid;
    }

    public void setPlayedHandmaid(boolean playedHandmaid) {
        this.playedHandmaid = playedHandmaid;
    }

    public int getPlayedCardsArrayLength() {
        return playedCardsArrayLength;
    }

    public int getCurrentCardsArrayLength() {
        return currentCardsArrayLength;
    }

    public void setPlayedCardsArrayLength(int playedCardsArrayLength) {
        this.playedCardsArrayLength = playedCardsArrayLength;
    }

    public void setCurrentCardsArrayLength(int currentCardsArrayLength) {
        this.currentCardsArrayLength = currentCardsArrayLength;
    }


    public boolean isPlayedCardsArrayEmpty() {
        if (playedCardsArrayLength == -1) {
            return true;
        }
        else {
            return false;
        }
    }


    public boolean isCurrentCardsEmpty() {
        if (currentCardsArrayLength == -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getPlayedCards() {

        String toReturn = "";

        if(!isPlayedCardsArrayEmpty()) //if the array is not empty then print out below
        {
            for(int i=0; i<=playedCardsArrayLength; i++)
            {
                toReturn = toReturn + " " + playedCards[i].getCardName();
                //toReturn + " " + playedCards[0].getCardName();
            }


            return toReturn;
        }
        else
        {
            return  "No cards played in this round";
        }

    }

    public String getCurrentCards() {

        String toReturn = "";

        if(!isCurrentCardsEmpty()) //if the array is not empty then print out below
        {
            for(int i=0; i<=currentCardsArrayLength; i++)
            {
                toReturn = toReturn + " " + currentCards[i].getCardName();
                //toReturn + " " + playedCards[0].getCardName();
            }


            return toReturn;
        }
        else
        {
            return  "The player has no cards to see, they are out of the round";
        }

    }


    //method to add a playedCard to the currentCardsArray
    public void setCurrentCard(Card card)
    {
        currentCardsArrayLength++; //increment the arrayLength
        this.currentCards[currentCardsArrayLength] = card; //insert the card
    }


    //method to add a playedCard to the currentplayedCardsArray
    public void setPlayedCard(Card card)
    {
        playedCardsArrayLength++; //increment the arrayLength
        this.playedCards[playedCardsArrayLength] = card; //insert the card
    }



    public void resetCurrentCardsArray() {
        currentCardsArrayLength = -1;
    }

    public void resetPlayedCardsArray() {
    }
}



