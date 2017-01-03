package com.example.padcf.loveletter;

/**
 * Created by padcf, paulvincentphillips & bradyc12 on 19/11/2016.
 * This class sets up the deck array of 16 card slots
 * It contains methods to populate the array with the appropriate number of each card
 * and to shuffle the deck, making the deck randomised
 * the card at element location 0 in the array is the 'burned' card
 * ie. the card which is removed at the beginning of the game
 */

public class Deck {


    //instantiate the deck
    Card[] deck = new Card[16];

    //getter method for the deck
    public Card[] getDeck() {
        return deck;
    }


    //shuffle the deck randomly
    public void shuffleDeck()
    {
        for(int i=0; i<deck.length; i++)
        {
            int ranNum = (int) (Math.random() * deck.length);
            Card temp;
            temp = deck[i];
            deck[i] = deck[ranNum];
            deck[ranNum] = temp;
        }
    }

    //a method to populate the deck.
    public void populateDeck()
    {

        //5 guards
        for(int i=0; i<5; i++)
        {
            deck[i] = new Guard();
        }

        //Two priests
        for(int i=5; i<7; i++)
        {
            deck[i] = new Priest();
        }

        //2 Barons
        for(int i=7; i<9; i++)
        {
            deck[i] = new Baron();
        }

        //2 Handmaids
        for(int i=9; i<11; i++)
        {
            deck[i] = new Handmaid();
        }

        //2 princes
        for(int i=11; i<13; i++)
        {
            deck[i] = new Prince();
        }

        //One King, Countess and Princess
        deck[13] = new King();
        deck[14] = new Countess();
        deck[15] = new Princess();
    }

}
