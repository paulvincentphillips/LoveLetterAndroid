package com.example.padcf.loveletter;


/**
 * Getters and setters for each player object
 * Keeps track of certain variables that are unique to each player such as player score
 * Created by padcf, paulvincentphillips & bradyc12 on 19/11/2016.
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

    public int getPlayedCardsArrayLength() { return playedCardsArrayLength; }

    public void setPlayedCardsArrayLength(int playedCardsArrayLength) { this.playedCardsArrayLength = playedCardsArrayLength; }

    public boolean isPlayedCardsArrayEmpty() {
        if (playedCardsArrayLength == -1) {
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

    //method to add a playedCard to the playedCardsArray
    public void setPlayedCard(Card card)
    {
        playedCardsArrayLength++; //increment the arrayLength
        this.playedCards[playedCardsArrayLength] = card; //insert the card
    }

    public void resetPlayedCardsArray()
    {
        playedCardsArrayLength = -1;
    }
}
