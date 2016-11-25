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

    //getter and setter methods for state

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
}
