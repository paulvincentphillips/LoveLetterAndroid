package com.example.padcf.loveletter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    /**
     * This is an android implementation of the AEG published card game 'Love Letter'. This is a
     * 'pass and play' version of the game ie. there is no AI. This is an alternative to the
     * physical card game. Players take their individual turns, playing a card and, when their turn
     * is complete, they pass the device to another player.
     * Created by padcf, paulvincentphillips & bradyc12 on 01/11/16
     */

    /*in beginRound we do stuff that is only required at the beginning of the round. We start beginRound by calling is from onCreate
    //Once everything is finished in beginRound we call beginTurn which then does all the things we need to be doing for each player turn.
    //because there are many methods working here, I needed to increase the scope of quite a few variables/objects, so they are now at the very top of the
    class as you can see*/

    //CLASS VARIABLES AND OBJECTS INSTANTIATED HERE


    //turn order variables which will allow us to refer to player1 (turnOrder), player2 (turnOrder2) etc. when needed
    int turnOrder = 0;
    int turnOrder2 = 1;
    int turnOrder3 = 2;
    int turnOrder4 = 3;

    //stores which card the user has pressed
    int cardChoice = 0;
    //store the uniqueID of the card chosen
    int cardChosenId = 0;

    //target card concerning guard card
    int guardChoice = 0;
    //button choice concerning guard card
    int guardButton = 0;

    //set up the four player objects
    Player player1 = new Player("");
    Player player2 = new Player("");
    Player player3 = new Player("");
    Player player4 = new Player("");

    //instantiate the playerOrder array
    Player[] playerOrder = new Player[4];

    //deck variables setup
    Deck mainDeck = new Deck(); //instantiate the deck of cards
    Card[] deck1 = mainDeck.getDeck(); //get the deck and store it in deck1 variable
    int deckLength; //length of deck variable used when dealing cards etc.

    //OptionsMenu not in use for the time being- to be used for other features.
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feeds_menu, menu);
        return true;
    }*/

    //processes which happen when the app begins
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //take in the string array from the previous activity
        Bundle b = this.getIntent().getExtras();
        String[] nameArray = b.getStringArray("namesArray");

        //set up the players with user input from previous activity
        player1.setPlayerName(nameArray[0]);
        player2.setPlayerName(nameArray[1]);
        player3.setPlayerName(nameArray[2]);
        player4.setPlayerName(nameArray[3]);

        //set up player order by calling randomPlayer method
        playerOrder = randomPlayer(playerOrder, player1, player2, player3, player4);

        mainDeck.populateDeck(); // populate the deck


        //this button will show the dialog box for the card's abilities
        //Button cardreference = (Button) findViewById(R.id.cardreferencebutton);
        ImageButton CardList = (ImageButton) findViewById(R.id.cardslist);

        CardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set up dialog
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_cardreference);
                //dialog.setTitle("Card Reference");
                dialog.setCancelable(true);
                //there are a lot of settings, for dialog, here they are all checked to true!

                //set up image view
                ImageView img = (ImageView) dialog.findViewById(R.id.cardreferenceimage);
                img.setImageResource(R.drawable.mylistsofcards);

                dialog.show();
            }
        });

        //create button for showing previously played cards
        //Button playedCardsButton = (Button) findViewById(R.id.playedCardsButton);
        ImageButton VplayedCardsButton = (ImageButton) findViewById(R.id.vplayedCardsButton);
        //create the onClick listen to gather information and put into a bundle
        //next start the popUp window activity and pass information for each player to that activity
        VplayedCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle playedCardsBundle = new Bundle();
                //bundle players previously played cards as strings,
                playedCardsBundle.putString("stringCurrentPlayer", playerOrder[turnOrder].getPlayedCards());
                playedCardsBundle.putString("stringPlayer2", playerOrder[turnOrder2].getPlayedCards());
                playedCardsBundle.putString("stringPlayer3", playerOrder[turnOrder3].getPlayedCards());
                playedCardsBundle.putString("stringPlayer4", playerOrder[turnOrder4].getPlayedCards());
                //bundle players names as strings
                playedCardsBundle.putString("nameCurrentPlayer", playerOrder[turnOrder].getPlayerName());
                playedCardsBundle.putString("namePlayer2", playerOrder[turnOrder2].getPlayerName());
                playedCardsBundle.putString("namePlayer3", playerOrder[turnOrder3].getPlayerName());
                playedCardsBundle.putString("namePlayer4", playerOrder[turnOrder4].getPlayerName());

                Intent playedCardsIntent = new Intent(MainActivity.this, PlayedCardsReal.class);
                playedCardsIntent.putExtras(playedCardsBundle);
                startActivity(playedCardsIntent);

                final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
                final ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton2);

                final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

            }
        });


        //send the program off to beginRound
        beginRound();


    }

    //beginRound method that does the things required for each round
    private void beginRound() {
        //card shuffle sound which happens at the beginning of each round
        final MediaPlayer cardShuffleSound = MediaPlayer.create(this, R.raw.cardfan);
        cardShuffleSound.start();

        //resets players' card choices to 0 (ie. nothing) at the start of each round
        //this will ensure that the first 2nd card dealt to a player will be stored in card2 slot
        //ie. will not overwrite card1
        player1.setCardChoice(0);
        player2.setCardChoice(0);
        player3.setCardChoice(0);
        player4.setCardChoice(0);
        //reset players playedCards to -1
        //each player's playedCardsArray allows us to show the users the history of played cards
        player1.resetPlayedCardsArray();
        player2.resetPlayedCardsArray();
        player3.resetPlayedCardsArray();
        player4.resetPlayedCardsArray();

        //reset every player to playing ie. not eliminated as round has just begun again
        player1.setPlaying(true);
        player2.setPlaying(true);
        player3.setPlaying(true);
        player4.setPlaying(true);

        //set everyone to not played handmaid ie. no player has handmaid immunity because of new round
        player1.setPlayedHandmaid(false);
        player2.setPlayedHandmaid(false);
        player3.setPlayedHandmaid(false);
        player4.setPlayedHandmaid(false);


        //CARD SETUP
        mainDeck.shuffleDeck(); //shuffle the deck

        deckLength = deck1.length - 1;

        //DEAL CARDS
        //deckLength = burnCard(deckLength); //burn off a card before dealing

        //method to deal out the cards and also hold the new value of deckLength returned from method in deckLength variable
        //this method will only work for starting hands as method stores cards in Card1 slot only
        deckLength = dealCards(player1, player2, player3, player4, deckLength, deck1);

        System.out.println(deckLength);
        //once finished here, move the program on to beginTurn
        beginTurn();
    }

    //this is the beginTurn method which does stuff for each playerTurn
    private void beginTurn() {

        //card flip sound which will be used every time a player turns over their cards
        final MediaPlayer cardFlipSound = MediaPlayer.create(this, R.raw.cardflip);

        //if the player has been previously knocked out of the round go to the endTurn method
        if (!playerOrder[turnOrder].getIsPlaying()) {
            endTurn();
        } else {
            //reset players immunity from playing handmaid to false(not immune)
            playerOrder[turnOrder].setPlayedHandmaid(false);

            System.out.println("Dealing from dealCard method");
            //deal 2nd card to current player
            deckLength = dealCard2(playerOrder[turnOrder], deckLength, deck1);

            //reset king and prince from black and white back to color
            if (playerOrder[turnOrder].getCard1().getCardValue() == 6) {
                playerOrder[turnOrder].getCard1().setImageId(R.drawable.king);
            } else if (playerOrder[turnOrder].getCard2().getCardValue() == 6) {
                playerOrder[turnOrder].getCard2().setImageId(R.drawable.king);
            }
            if (playerOrder[turnOrder].getCard1().getCardValue() == 5) {
                playerOrder[turnOrder].getCard1().setImageId(R.drawable.prince);
            } else if (playerOrder[turnOrder].getCard2().getCardValue() == 5) {
                playerOrder[turnOrder].getCard2().setImageId(R.drawable.prince);
            }

            //System outs for debugging purposes. Eg. If we want to target another player's card with the
            //guard we can see what that player has. We can then evaluate if the outcome was as expected
            System.out.println(playerOrder[turnOrder2].getPlayerName() + playerOrder[turnOrder2].getCard1().getCardName());
            System.out.println(playerOrder[turnOrder3].getPlayerName() + playerOrder[turnOrder3].getCard1().getCardName());
            System.out.println(playerOrder[turnOrder4].getPlayerName() + playerOrder[turnOrder4].getCard1().getCardName());


            System.out.println(deckLength);

            final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
            final ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton2);

            final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

            mainButton.setText("Reveal " + playerOrder[turnOrder].getPlayerName() + " 's cards");
            mainButton.setTextOff("Reveal " + playerOrder[turnOrder].getPlayerName() + " 's cards");
            //setting default image to cardBack
            ib.setImageResource(R.drawable.cardback);
            ib2.setImageResource(R.drawable.cardback);
            //give the imageButtons functionality
            addListnerOnButton(ib, ib2);
            //but make them unclickable at the moment, because they currently have their cardBacks facing the user
            ib.setClickable(false);
            ib2.setClickable(false);

            //this method toggles the images between cardBack and the cardFace when REVEAL button is clicked...this is the toggle button
            mainButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //play the cardFlip sound
                    cardFlipSound.start();
                    if (isChecked) {
                        //call the addButtonListen here, when toggle is switched to reveal cards
                        //we can now click and use our cards because they are no longer face down
                        ib.setClickable(true);
                        ib2.setClickable(true);
                        //countess function, see below
                        countess(playerOrder[turnOrder], ib, ib2);
                        //set the images of the cards to what the player currently has in his hand
                        ib.setImageResource(playerOrder[turnOrder].getCard1().getImageId());
                        ib2.setImageResource(playerOrder[turnOrder].getCard2().getImageId());
                        //Toast.makeText(getApplicationContext(), "Choose a card", Toast.LENGTH_SHORT).show();

                    } else {
                        //set the buttons to be unClickable when they are toggled to cardBack
                        ib.setClickable(false);
                        ib2.setClickable(false);
                        ib.setImageResource(R.drawable.cardback);
                        ib2.setImageResource(R.drawable.cardback);


                        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
                        relLayout.setVisibility(View.INVISIBLE);
                        Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);
                        fourSevenEight.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    // this method will contain the different displays for the relevant card
    //pass in the card id and the display() method will select the correct card depending on that ID.
    private void display(int cardId) {

        //instantiate the mainToggle button again to access it
        final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

        //*** BEGIN OF CARD DISPLAYS ***

        //displays for guard
        if (cardChosenId == 1) {         //chose the guard whether its card 1 or card2 on the mainActivity screen

            //bring in the relative layout and make it visible
            RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
            relLayout.setVisibility(View.VISIBLE);

            Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);

            if ((playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) &&
                    (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) &&
                    (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying())) {
                fourSevenEight.setVisibility(View.VISIBLE);
                fourSevenEight.setText("No Effect");

            } else {
                fourSevenEight.setVisibility(View.INVISIBLE);
            }

            //set up button objects to use here
            final Button button1 = (Button) findViewById(R.id.button1);
            final Button button2 = (Button) findViewById(R.id.button2);
            final Button button3 = (Button) findViewById(R.id.button3);

            //Display each players name on the button and their current score
            button1.setText(playerOrder[turnOrder2].getPlayerName() + " " + playerOrder[turnOrder2].getPlayerScore());
            button2.setText(playerOrder[turnOrder3].getPlayerName() + " " + playerOrder[turnOrder3].getPlayerScore());
            button3.setText(playerOrder[turnOrder4].getPlayerName() + " " + playerOrder[turnOrder4].getPlayerScore());

            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);

            if (playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) {
                button1.setEnabled(false);
            }
            if (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) {
                button2.setEnabled(false);
            }
            if (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying()) {
                button3.setEnabled(false);
            }

            fourSevenEight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            //set up on click for buttons
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guardButton = 1;

                    Intent intent = new Intent(MainActivity.this, GuardLayout.class);
                    startActivityForResult(intent, 1);
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guardButton = 2;

                    Intent intent = new Intent(MainActivity.this, GuardLayout.class);
                    startActivityForResult(intent, 1);
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guardButton = 3;

                    Intent intent = new Intent(MainActivity.this, GuardLayout.class);
                    startActivityForResult(intent, 1);
                }
            });
        }

        //Displays for Priest here
        if (cardId == 2) {

            playerOrder[turnOrder].setPlayedHandmaid(false);

            //bring in the 3 button relative layout and make it visible
            RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
            relLayout.setVisibility(View.VISIBLE);

            Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);

            if ((playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) &&
                    (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) &&
                    (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying())) {
                fourSevenEight.setVisibility(View.VISIBLE);
                fourSevenEight.setText("No Effect");

            } else {
                fourSevenEight.setVisibility(View.INVISIBLE);
            }

            //set up button objects to use here
            final Button button1 = (Button) findViewById(R.id.button1);
            final Button button2 = (Button) findViewById(R.id.button2);
            final Button button3 = (Button) findViewById(R.id.button3);

            //Display each players name on the button and their current score
            button1.setText(playerOrder[turnOrder2].getPlayerName() + " " + playerOrder[turnOrder2].getPlayerScore());
            button2.setText(playerOrder[turnOrder3].getPlayerName() + " " + playerOrder[turnOrder3].getPlayerScore());
            button3.setText(playerOrder[turnOrder4].getPlayerName() + " " + playerOrder[turnOrder4].getPlayerScore());

            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);


            if (playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) {
                button1.setEnabled(false);
            }
            if (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) {
                button2.setEnabled(false);
            }
            if (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying()) {
                button3.setEnabled(false);
            }

            //set up on click for buttons
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Intent myIntent = new Intent(MainActivity.this, PriestPop.class);

                    Bundle priestInfo = new Bundle();

                    //bundle target player's name for use in pop-up activity
                    priestInfo.putString("targetName", playerOrder[turnOrder2].getPlayerName());
                    //bundle target player's card for use in pop-up activity
                    if(playerOrder[turnOrder2].getCardChoice() == 0){
                        priestInfo.putString("targetCard1", playerOrder[turnOrder2].getCard1().getCardName());
                    }else{
                        priestInfo.putString("targetCard1", playerOrder[turnOrder2].getCard1().getCardName());
                        priestInfo.putString("targetCard2", playerOrder[turnOrder2].getCard2().getCardName());
                    }
                    //bundle target player's cardChoice
                    priestInfo.putInt("targetChoice", playerOrder[turnOrder2].getCardChoice());
                    //pass the extra into the intent
                    myIntent.putExtras(priestInfo);

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    startActivity(myIntent);
                    endTurn();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Intent myIntent = new Intent(MainActivity.this, PriestPop.class);
                    Bundle priestInfo = new Bundle();

                    //bundle target player's name for use in pop-up activity
                    priestInfo.putString("targetName", playerOrder[turnOrder3].getPlayerName());
                    //bundle target player's card for use in pop-up activity
                    if(playerOrder[turnOrder3].getCardChoice() == 0){
                        priestInfo.putString("targetCard1", playerOrder[turnOrder3].getCard1().getCardName());
                    }else{
                        priestInfo.putString("targetCard1", playerOrder[turnOrder3].getCard1().getCardName());
                        priestInfo.putString("targetCard2", playerOrder[turnOrder3].getCard2().getCardName());
                    }
                    //bundle target player's cardChoice
                    priestInfo.putInt("targetChoice", playerOrder[turnOrder3].getCardChoice());
                    //pass the extra into the intent
                    myIntent.putExtras(priestInfo);

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    startActivity(myIntent);
                    endTurn();
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Intent myIntent = new Intent(MainActivity.this, PriestPop.class);
                    Bundle priestInfo = new Bundle();

                    //bundle target player's name for use in pop-up activity
                    priestInfo.putString("targetName", playerOrder[turnOrder4].getPlayerName());
                    //bundle target player's card for use in pop-up activity
                    if(playerOrder[turnOrder4].getCardChoice() == 0){
                        priestInfo.putString("targetCard1", playerOrder[turnOrder4].getCard1().getCardName());
                    }else{
                        priestInfo.putString("targetCard1", playerOrder[turnOrder4].getCard1().getCardName());
                        priestInfo.putString("targetCard2", playerOrder[turnOrder4].getCard2().getCardName());
                    }
                    //bundle target player's cardChoice
                    priestInfo.putInt("targetChoice", playerOrder[turnOrder4].getCardChoice());
                    //pass the extra into the intent
                    myIntent.putExtras(priestInfo);

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    startActivity(myIntent);
                    endTurn();
                }
            });
        }

        //Baron
        if (cardId == 3) {

            playerOrder[turnOrder].setPlayedHandmaid(false);

            //bring in the 3 button relative layout and make it visible
            RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
            relLayout.setVisibility(View.VISIBLE);

            Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);

            if ((playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) &&
                    (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) &&
                    (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying())) {
                fourSevenEight.setVisibility(View.VISIBLE);
                fourSevenEight.setText("No Effect");

            } else {
                fourSevenEight.setVisibility(View.INVISIBLE);
            }

            //set up button objects to use here
            final Button button1 = (Button) findViewById(R.id.button1);
            final Button button2 = (Button) findViewById(R.id.button2);
            final Button button3 = (Button) findViewById(R.id.button3);

            //Display each players name on the button and their current score
            button1.setText(playerOrder[turnOrder2].getPlayerName() + " " + playerOrder[turnOrder2].getPlayerScore());
            button2.setText(playerOrder[turnOrder3].getPlayerName() + " " + playerOrder[turnOrder3].getPlayerScore());
            button3.setText(playerOrder[turnOrder4].getPlayerName() + " " + playerOrder[turnOrder4].getPlayerScore());

            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);


            if (playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) {
                button1.setEnabled(false);
            }
            if (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) {
                button2.setEnabled(false);
            }
            if (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying()) {
                button3.setEnabled(false);
            }

            //set up on click for buttons
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //testing popup activity here
                    //bundle information about cards before the logic is run and cards are changed. We do this so that it appears to the user that the logic
                    //happens in the activity popup screen.
                    final Intent myIntent = new Intent(MainActivity.this, Baronpop.class);//create your intent
                    Bundle baronInfo = new Bundle();

                    //bundle player and target player's name
                    baronInfo.putString("playerName", playerOrder[turnOrder].getPlayerName());
                    baronInfo.putString("targetName", playerOrder[turnOrder2].getPlayerName());
                    //cardChoice for current and target player
                    baronInfo.putInt("playerChoice", cardChoice);
                    baronInfo.putInt("targetChoice", playerOrder[turnOrder2].getCardChoice());
                    //bundle player's card ability and target player's value
                    baronInfo.putInt("playerCardAbility1", playerOrder[turnOrder].getCard1().getCardValue());
                    baronInfo.putInt("playerCardAbility2", playerOrder[turnOrder].getCard2().getCardValue());
                    if(playerOrder[turnOrder2].getCardChoice()==0){
                        baronInfo.putInt("targetCardAbility1", playerOrder[turnOrder2].getCard1().getCardValue());
                        baronInfo.putString("targetPlayerCardName1", playerOrder[turnOrder2].getCard1().getCardName());
                    }else{
                        baronInfo.putInt("targetCardAbility1", playerOrder[turnOrder2].getCard1().getCardValue());
                        baronInfo.putInt("targetCardAbility2", playerOrder[turnOrder2].getCard2().getCardValue());
                        baronInfo.putString("targetPlayerCardName1", playerOrder[turnOrder2].getCard1().getCardName());
                        baronInfo.putString("targetPlayerCardName2", playerOrder[turnOrder2].getCard2().getCardName());
                    }

                    //bundle player and target player's card images
                    //baronInfo.putInt("playerCardImage", playerOrder[turnOrder].getCard2().getImageId());
                    //baronInfo.putInt("targetCardImage", playerOrder[turnOrder2].getCard1().getImageId());
                    //bundle player and target player's card names
                    baronInfo.putString("playerCardName1", playerOrder[turnOrder].getCard1().getCardName());
                    baronInfo.putString("playerCardName2", playerOrder[turnOrder].getCard2().getCardName());

                    myIntent.putExtras(baronInfo);//put the bundle with the intent

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice


                    startActivity(myIntent);

                    endTurn();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //testing popup activity here
                    //bundle information about cards before the logic is run and cards are changed. We do this so that it appears to the user that the logic
                    //happens in the activity popup screen.
                    final Intent myIntent = new Intent(MainActivity.this, Baronpop.class);//create your intent
                    Bundle baronInfo = new Bundle();

                    //bundle player and target player's name
                    baronInfo.putString("playerName", playerOrder[turnOrder].getPlayerName());
                    baronInfo.putString("targetName", playerOrder[turnOrder3].getPlayerName());
                    //cardChoice for current and target player
                    baronInfo.putInt("playerChoice", playerOrder[turnOrder].getCardChoice());
                    baronInfo.putInt("targetChoice", playerOrder[turnOrder3].getCardChoice());
                    //bundle player's card ability and target player's value
                    baronInfo.putInt("playerCardAbility1", playerOrder[turnOrder].getCard1().getCardValue());
                    baronInfo.putInt("playerCardAbility2", playerOrder[turnOrder].getCard2().getCardValue());
                    if(playerOrder[turnOrder3].getCardChoice()==0){
                        baronInfo.putInt("targetCardAbility1", playerOrder[turnOrder3].getCard1().getCardValue());
                        baronInfo.putString("targetPlayerCardName1", playerOrder[turnOrder3].getCard1().getCardName());
                    }else{
                        baronInfo.putInt("targetCardAbility1", playerOrder[turnOrder3].getCard1().getCardValue());
                        baronInfo.putInt("targetCardAbility2", playerOrder[turnOrder3].getCard2().getCardValue());
                        baronInfo.putString("targetPlayerCardName1", playerOrder[turnOrder3].getCard1().getCardName());
                        baronInfo.putString("targetPlayerCardName2", playerOrder[turnOrder3].getCard2().getCardName());
                    }

                    //bundle player and target player's card images
                    //baronInfo.putInt("playerCardImage", playerOrder[turnOrder].getCard2().getImageId());
                    //baronInfo.putInt("targetCardImage", playerOrder[turnOrder2].getCard1().getImageId());
                    //bundle player and target player's card names
                    baronInfo.putString("playerCardName1", playerOrder[turnOrder].getCard1().getCardName());
                    baronInfo.putString("playerCardName2", playerOrder[turnOrder].getCard2().getCardName());

                    myIntent.putExtras(baronInfo);//put the bundle with the intent

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice

                    startActivity(myIntent);
                    endTurn();
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //testing popup activity here
                    //bundle information about cards before the logic is run and cards are changed. We do this so that it appears to the user that the logic
                    //happens in the activity popup screen.
                    final Intent myIntent = new Intent(MainActivity.this, Baronpop.class);//create your intent
                    Bundle baronInfo = new Bundle();

                    //bundle player and target player's name
                    baronInfo.putString("playerName", playerOrder[turnOrder].getPlayerName());
                    baronInfo.putString("targetName", playerOrder[turnOrder4].getPlayerName());
                    //cardChoice for current and target player
                    baronInfo.putInt("playerChoice", playerOrder[turnOrder].getCardChoice());
                    baronInfo.putInt("targetChoice", playerOrder[turnOrder4].getCardChoice());
                    //bundle player's card ability and target player's value
                    baronInfo.putInt("playerCardAbility1", playerOrder[turnOrder].getCard1().getCardValue());
                    baronInfo.putInt("playerCardAbility2", playerOrder[turnOrder].getCard2().getCardValue());
                    if(playerOrder[turnOrder4].getCardChoice()==0){
                        baronInfo.putInt("targetCardAbility1", playerOrder[turnOrder4].getCard1().getCardValue());
                        baronInfo.putString("targetPlayerCardName1", playerOrder[turnOrder4].getCard1().getCardName());
                    }else{
                        baronInfo.putInt("targetCardAbility1", playerOrder[turnOrder4].getCard1().getCardValue());
                        baronInfo.putInt("targetCardAbility2", playerOrder[turnOrder4].getCard2().getCardValue());
                        baronInfo.putString("targetPlayerCardName1", playerOrder[turnOrder4].getCard1().getCardName());
                        baronInfo.putString("targetPlayerCardName2", playerOrder[turnOrder4].getCard2().getCardName());
                    }

                    //bundle player and target player's card images
                    //baronInfo.putInt("playerCardImage", playerOrder[turnOrder].getCard2().getImageId());
                    //baronInfo.putInt("targetCardImage", playerOrder[turnOrder2].getCard1().getImageId());
                    //bundle player and target player's card names
                    baronInfo.putString("playerCardName1", playerOrder[turnOrder].getCard1().getCardName());
                    baronInfo.putString("playerCardName2", playerOrder[turnOrder].getCard2().getCardName());

                    myIntent.putExtras(baronInfo);//put the bundle with the intent

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    startActivity(myIntent);
                    endTurn();
                }
            });
        }

        //Display for the King goes here
        if (cardId == 6) {

            playerOrder[turnOrder].setPlayedHandmaid(false);

            //bring in the 3 button relative layout and make it visible
            RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
            relLayout.setVisibility(View.VISIBLE);

            Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);

            if ((playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) &&
                    (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) &&
                    (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying())) {
                fourSevenEight.setVisibility(View.VISIBLE);
                fourSevenEight.setText("No Effect");

            } else {
                fourSevenEight.setVisibility(View.INVISIBLE);
            }

            //set up button objects to use here
            final Button button1 = (Button) findViewById(R.id.button1);
            final Button button2 = (Button) findViewById(R.id.button2);
            final Button button3 = (Button) findViewById(R.id.button3);

            //Display each players name on the button and their current score
            button1.setText(playerOrder[turnOrder2].getPlayerName() + " " + playerOrder[turnOrder2].getPlayerScore());
            button2.setText(playerOrder[turnOrder3].getPlayerName() + " " + playerOrder[turnOrder3].getPlayerScore());
            button3.setText(playerOrder[turnOrder4].getPlayerName() + " " + playerOrder[turnOrder4].getPlayerScore());

            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);


            if (playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) {
                button1.setEnabled(false);
            }
            if (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) {
                button2.setEnabled(false);
            }
            if (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying()) {
                button3.setEnabled(false);
            }

            //set up on click for buttons
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent myIntent = new Intent(MainActivity.this, KingPop.class);
                    Bundle kingInfo = new Bundle();

                    //bundle player and target player's name
                    kingInfo.putString("playerName", playerOrder[turnOrder].getPlayerName());
                    kingInfo.putString("targetName", playerOrder[turnOrder2].getPlayerName());
                    myIntent.putExtras(kingInfo);

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    startActivity(myIntent);
                    endTurn();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent myIntent = new Intent(MainActivity.this, KingPop.class);
                    Bundle kingInfo = new Bundle();

                    //bundle player and target player's name
                    kingInfo.putString("playerName", playerOrder[turnOrder].getPlayerName());
                    kingInfo.putString("targetName", playerOrder[turnOrder3].getPlayerName());
                    myIntent.putExtras(kingInfo);


                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    startActivity(myIntent);
                    endTurn();
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent myIntent = new Intent(MainActivity.this, KingPop.class);
                    Bundle kingInfo = new Bundle();

                    //bundle player and target player's name
                    kingInfo.putString("playerName", playerOrder[turnOrder].getPlayerName());
                    kingInfo.putString("targetName", playerOrder[turnOrder4].getPlayerName());
                    myIntent.putExtras(kingInfo);

                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    startActivity(myIntent);
                    endTurn();
                }
            });
        }

        //display for handmaid, countess and princess done here
        if (cardChosenId == 4 || cardChosenId == 7 || cardChosenId == 8) {

            //set up on click for buttons
            final Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);
            if (cardChosenId == 4) {
                fourSevenEight.setText("Play Handmaid");
            } else if (cardChosenId == 7) {
                fourSevenEight.setText("Play Countess");
            } else {
                fourSevenEight.setText("Play Princess");
            }

            fourSevenEight.setVisibility(View.VISIBLE);

            RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
            relLayout.setVisibility(View.INVISIBLE);

            fourSevenEight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    fourSevenEight.setVisibility(View.INVISIBLE);
                    if (cardChosenId == 4) {
                        Toast.makeText(getApplicationContext(), "You are now immune and cannot be targeted for one round." + playerOrder[turnOrder2].getPlayerName() + " to discard his hand and pick up a new card.", Toast.LENGTH_SHORT).show();
                        System.out.print("HANDMAID");
                    } else if (cardChosenId == 7) {
                        Toast.makeText(getApplicationContext(), "You have played the countess", Toast.LENGTH_SHORT).show();
                        System.out.print("countess");

                    } else {
                        Toast.makeText(getApplicationContext(), "You have played the Princess. You are out of this round.", Toast.LENGTH_SHORT).show();
                        System.out.print("princess");

                    }
                    endTurn();
                }
            });


        }

        //displays for prince
        if (cardChosenId == 5) {
            //bring in the relative layout and make it visible
            RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
            relLayout.setVisibility(View.VISIBLE);

            //set up button objects to use here
            final Button button1 = (Button) findViewById(R.id.button1);
            final Button button2 = (Button) findViewById(R.id.button2);
            final Button button3 = (Button) findViewById(R.id.button3);

            //Display each players name on the button and their current score
            button1.setText(playerOrder[turnOrder2].getPlayerName() + " " + playerOrder[turnOrder2].getPlayerScore());
            button2.setText(playerOrder[turnOrder3].getPlayerName() + " " + playerOrder[turnOrder3].getPlayerScore());
            button3.setText(playerOrder[turnOrder4].getPlayerName() + " " + playerOrder[turnOrder4].getPlayerScore());

            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);

            if (playerOrder[turnOrder2].isPlayedHandmaid() || !playerOrder[turnOrder2].getIsPlaying()) {
                button1.setEnabled(false);
            }
            if (playerOrder[turnOrder3].isPlayedHandmaid() || !playerOrder[turnOrder3].getIsPlaying()) {
                button2.setEnabled(false);
            }
            if (playerOrder[turnOrder4].isPlayedHandmaid() || !playerOrder[turnOrder4].getIsPlaying()) {
                button3.setEnabled(false);
            }

            //set up on click for buttons
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    Toast.makeText(getApplicationContext(), "You have forced " + playerOrder[turnOrder2].getPlayerName() + " to discard his hand and pick up a new card.", Toast.LENGTH_SHORT).show();
                    System.out.print("PRINCE");
                    endTurn();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    Toast.makeText(getApplicationContext(), "You have forced " + playerOrder[turnOrder3].getPlayerName() + " to discard his hand and pick up a new card.", Toast.LENGTH_SHORT).show();
                    System.out.print("PRINCE");
                    endTurn();
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    Toast.makeText(getApplicationContext(), "You have forced " + playerOrder[turnOrder4].getPlayerName() + " to discard his hand and pick up a new card.", Toast.LENGTH_SHORT).show();
                    System.out.print("PRINCE");
                    endTurn();
                }
            });

            //set up on click for buttons
            final Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);

            fourSevenEight.setText("Target Yourself");

            fourSevenEight.setVisibility(View.VISIBLE);
            fourSevenEight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    fourSevenEight.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "You have discarded your own hand and taken a fresh card form the deck.", Toast.LENGTH_SHORT).show();
                    endTurn();
                }
            });
        }

    }


    //we will need an end round function to check and update stuff at the end of every round
    private void endTurn() {
        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
        relLayout.setVisibility(View.INVISIBLE);

        //check to see if 3 players are knocked out
        int isOutCount = 0;

        if (!player1.getIsPlaying()) {
            isOutCount++;
        }
        if (!player2.getIsPlaying()) {
            isOutCount++;
        }
        if (!player3.getIsPlaying()) {
            isOutCount++;
        }
        if (!player4.getIsPlaying()) {
            isOutCount++;
        }

        //check to see which player is left standing -- award one point
        if (isOutCount == 3) {
            if (player1.getIsPlaying()) {
                player1.setScore(player1.getPlayerScore() + 1);
                System.out.println(player1.getPlayerName() + " is the last player standing and has won the round!");
                toastWinner(player1);
                winnerTurn(player1, playerOrder);
                if (player1.getPlayerScore() == 4) {
                    endGame();
                } else {

                    beginRound();
                }
            } else if (player2.getIsPlaying()) {
                player2.setScore(player2.getPlayerScore() + 1);
                System.out.println(player2.getPlayerName() + " is the last player standing and has won the round!");
                toastWinner(player2);
                winnerTurn(player2, playerOrder);
                if (player2.getPlayerScore() == 4) {
                    endGame();
                } else {
                    beginRound();
                }
            } else if (player3.getIsPlaying()) {
                player3.setScore(player3.getPlayerScore() + 1);
                System.out.println(player3.getPlayerName() + " is the last player standing and has won the round!");
                toastWinner(player3);
                winnerTurn(player3, playerOrder);
                if (player3.getPlayerScore() == 4) {
                    endGame();
                } else {
                    beginRound();
                }
            } else {
                player4.setScore(player4.getPlayerScore() + 1);
                System.out.println(player4.getPlayerName() + " is the last player standing and has won the round!");
                toastWinner(player4);
                winnerTurn(player4, playerOrder);
                if (player4.getPlayerScore() == 4) {
                    endGame();
                } else {
                    beginRound();
                }
            }
        }

        //check if deck is empty -- then compare isPlaying players' cards for highest value -- award point if highest value -- no point if draw -- reveal burned card
        //IMPORTANT: Another rule to implement:
        //If highest cards revealed = draw ie. I have a baron and so do you and we're the last players remaining
        //Count up all other discarded cards and highest sum wins

        //if deck is empty do the following
        //the 'burned card' at the start of the game is stored in deck array slot 0
        //therefore the deck is technically empty at deckLength==1 however there are circumstances where
        //a player may have to draw the burned card (see Prince card class)
        //IMPORTANT: Double check if this or statement makes sense
        if (deckLength == 0 || deckLength == 1) {
            //set up variables to store card values of players' held card at the end of the game
            int cardVal1 = 0;
            int cardVal2 = 0;
            int cardVal3 = 0;
            int cardVal4 = 0;

            //check to see if player is playing then store his card value
            if (player1.getIsPlaying()) {
                cardVal1 = player1.getCard1().getCardValue();
            }
            if (player2.getIsPlaying()) {
                cardVal2 = player2.getCard1().getCardValue();
            }
            if (player3.getIsPlaying()) {
                cardVal3 = player3.getCard1().getCardValue();
            }
            if (player4.getIsPlaying()) {
                cardVal4 = player4.getCard1().getCardValue();
            }

            //now compare each player's card value with everyone else's. If player has highest card award point
            //if player's points = 4 then end the game. Else, begin a new round
            //repeat until someone reaches the goal of 4 points
            if (cardVal1 > cardVal2 && cardVal1 > cardVal3 && cardVal1 > cardVal4) {
                player1.setScore(player1.getPlayerScore() + 1);
                System.out.println(player1.getPlayerName() + " has the highest value card and has won the round!");
                toastWinner(player1);
                //winner turn will set the turn order for the next round
                //if starts with the winner of the previous round
                winnerTurn(player1, playerOrder);
                if (player1.getPlayerScore() == 4) {
                    endGame();
                } else {
                    beginRound();
                }
            } else if (cardVal2 > cardVal1 && cardVal2 > cardVal3 && cardVal2 > cardVal4) {
                player2.setScore(player2.getPlayerScore() + 1);
                System.out.println(player2.getPlayerName() + " has the highest value card and has won the round!");
                toastWinner(player2);
                winnerTurn(player2, playerOrder);
                if (player2.getPlayerScore() == 4) {
                    endGame();
                } else {
                    beginRound();
                }
            } else if (cardVal3 > cardVal1 && cardVal3 > cardVal2 && cardVal3 > cardVal4) {
                player3.setScore(player3.getPlayerScore() + 1);
                System.out.println(player3.getPlayerName() + " has the highest value card and has won the round!");
                toastWinner(player3);
                winnerTurn(player3, playerOrder);
                if (player3.getPlayerScore() == 4) {
                    endGame();
                } else {
                    beginRound();
                }
            } else if (cardVal4 > cardVal1 && cardVal4 > cardVal2 && cardVal4 > cardVal3) {
                player4.setScore(player4.getPlayerScore() + 1);
                System.out.println(player4.getPlayerName() + " has the highest value card and has won the round!");
                toastWinner(player4);
                winnerTurn(player4, playerOrder);
                if (player4.getPlayerScore() == 4) {
                    endGame();
                } else {
                    beginRound();
                }
            } else {
                System.out.println("No one has the highest value card\nThis round is a draw!");
                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                beginRound();
            }
        }

        playerOrder[turnOrder].setCardChoice(cardChoice);

        //if none of the above is true increment each players' turn variable which will begin the next player's turn
        nextTurn();
    }


    private void endGame() {
        //send the following information to EndGame intent
        //this will simple bring up an intent to display who won the game
        Bundle playerScoresNames = new Bundle();
        playerScoresNames.putInt("Player 1 Score", player1.getPlayerScore());
        playerScoresNames.putInt("Player 2 Score", player2.getPlayerScore());
        playerScoresNames.putInt("Player 3 Score", player3.getPlayerScore());
        playerScoresNames.putInt("Player 4 Score", player4.getPlayerScore());
        playerScoresNames.putString("Player 1 Name", player1.getPlayerName());
        playerScoresNames.putString("Player 2 Name", player2.getPlayerName());
        playerScoresNames.putString("Player 3 Name", player3.getPlayerName());
        playerScoresNames.putString("Player 4 Name", player4.getPlayerName());


        Intent endGameIntent = new Intent(MainActivity.this, EndGame.class);
        endGameIntent.putExtras(playerScoresNames);
        startActivity(endGameIntent);
    }

    //deal out cards
    public static int dealCards(Player pOne, Player pTwo, Player pThree, Player pFour, int deckLength, Card[] deck) {
        //set a player's card to current deckLength in the array then decrement deckLength, simulating
        //a deck decreasing in size
        pOne.setCard1(deck[deckLength]);
        deckLength--;
        pTwo.setCard1(deck[deckLength]);
        deckLength--;
        pThree.setCard1(deck[deckLength]);
        deckLength--;
        pFour.setCard1(deck[deckLength]);
        deckLength--;
        return deckLength;
    }

    //random player method to randomise player order
    public static Player[] randomPlayer(Player[] array, Player pOne, Player pTwo, Player pThree, Player pFour) {
        //random number between 1 + 4
        int random = (int) (Math.random() * 4 + 1);

        //if the random number is 1, set player1 to first player then 2nd player as player 2 etc.
        if (random == 1) {
            array[0] = pOne;
            array[1] = pTwo;
            array[2] = pThree;
            array[3] = pFour;
        }
        ////if the random number is 2, set player2 to first player then 3rd player as player 3 etc.
        else if (random == 2) {
            array[0] = pTwo;
            array[1] = pThree;
            array[2] = pFour;
            array[3] = pOne;
        } else if (random == 3) {
            array[0] = pThree;
            array[1] = pFour;
            array[2] = pOne;
            array[3] = pTwo;
        } else {
            array[0] = pFour;
            array[1] = pOne;
            array[2] = pTwo;
            array[3] = pThree;
        }

        return array;
    }

    public void toastWinner(Player winner) {
        Toast.makeText(this, winner.getPlayerName() + " has won the round!", Toast.LENGTH_SHORT).show();
    }

    //a method to
    public void nextTurn() {
        //next player
        if (turnOrder == 3) {
            turnOrder = 0;
        } else {
            turnOrder++;
        }

        //shift every other player up a number
        if (turnOrder2 == 3) {
            turnOrder2 = 0;
        } else {
            turnOrder2++;
        }

        if (turnOrder3 == 3) {
            turnOrder3 = 0;
        } else {
            turnOrder3++;
        }

        if (turnOrder4 == 3) {
            turnOrder4 = 0;
        } else {
            turnOrder4++;
        }

        beginTurn();
    }

    //this will change the turnorder variables depending on who won the last round
    //we do this by setting up a for loop and a variable x
    //we then loop through the player array
    public void winnerTurn(Player winner, Player[] playerArray) {
        for (int x = 0; x < playerArray.length; x++) {
            //if the winner's name is equal to the name we are currently accessing in the player array
            if (winner.getPlayerName().equals(playerArray[x].getPlayerName())) {
                //turnOrder = x ie. turnOrder is the same as the slot the winning player's name is in the player array
                turnOrder = x;
                //if the player is in the player array 0 slot then we set everyone else's turnOrder variables
                //in relation to his position
                if (turnOrder == 0) {
                    turnOrder2 = 1;
                    turnOrder3 = 2;
                    turnOrder4 = 3;
                } else if (turnOrder == 1) {
                    turnOrder2 = 2;
                    turnOrder3 = 3;
                    turnOrder4 = 0;
                } else if (turnOrder == 2) {
                    turnOrder2 = 3;
                    turnOrder3 = 0;
                    turnOrder4 = 1;
                } else {
                    turnOrder2 = 0;
                    turnOrder3 = 1;
                    turnOrder4 = 2;
                }
            }
        }
    }

    //guard functionality happens here
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                guardChoice = data.getIntExtra("guardChoice", 1);
                //System.out.println(guardChoice);
                //set up button objects to use here
                final Button button1 = (Button) findViewById(R.id.button1);
                final Button button2 = (Button) findViewById(R.id.button2);
                final Button button3 = (Button) findViewById(R.id.button3);

                final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

                if(guardButton == 1){
                    if (cardChoice == 1) {

                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());

                    } else { //if(cardChoice  ==2 ) the other card

                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());

                    }
                }
                else if(guardButton == 2){
                    if (cardChoice == 1) {

                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());

                    } else {

                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                }else{
                    if (cardChoice == 1) {



                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());

                    } else {



                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice, guardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());

                    }
                }

                mainButton.setChecked(false); //set toggle button back when a player has made their choice
                endTurn();
            }
        }
    }//onActivityResult

    //this method let's you do stuff when you click on a button
    public void addListnerOnButton(View ib, View ib2) {
        //here we decide what happens when you click on either image. each image is passed in as a formal parameter, ib or ib2
        ib.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      cardChoice = 1;
                                      cardChosenId = playerOrder[turnOrder].getCard1().getCardValue();
                                      RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
                                      display(cardChosenId);
                                      //relLayout.setVisibility(View.VISIBLE);
                                  }
                              }
        );

        ib2.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       cardChoice = 2;
                                       cardChosenId = playerOrder[turnOrder].getCard2().getCardValue();
                                       RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
                                       display(cardChosenId); //pass in the chosenCardID here, because it needs to then be passed to display() which in turn will filter the content to display
                                       //relLayout.setVisibility(View.VISIBLE);
                                   }
                               }
        );


    }

    //dealCard2 will deal the current player's 2nd card to them
    //when a turn begins you only hold 1 card until your 2nd is dealt to you
    //the logic below simply tells the device which card to overwrite in the player's hand
    //ie. if the player played card1 then replace card1 with the new card
    //if player played card2 then replace card2 with new card
    public static int dealCard2(Player player, int deckLength, Card[] deck) {
        if (player.getCardChoice() == 1) {
            player.setCard1(deck[deckLength]);
        } else {
            player.setCard2(deck[deckLength]);
        }
        deckLength--;
        return deckLength;
    }

    //method for countess
    //if you hold either the king and countess or the prince and countess you are unable to choose any card but the countess
    //This method will change the non-countess card to black and white and make the card unclickable

    public static void countess(Player player, ImageButton ib1, ImageButton ib2) {

        if (player.getCard1().getCardValue() == 7 && player.getCard2().getCardValue() == 6) {
            player.getCard2().setImageId(R.drawable.kingcountess);
            //set card2 to unclickable
            ib2.setClickable(false);
            System.out.println("false");
        } else if (player.getCard1().getCardValue() == 7 && player.getCard2().getCardValue() == 5) {
            player.getCard2().setImageId(R.drawable.princecountess);
            //set card2 to unclickable
            ib2.setClickable(false);
            System.out.println("false");
        } else if (player.getCard2().getCardValue() == 7 && player.getCard1().getCardValue() == 6) {
            player.getCard1().setImageId(R.drawable.kingcountess);
            //set card1 to unclickable
            ib1.setClickable(false);
            System.out.println("false");
        } else if (player.getCard2().getCardValue() == 7 && player.getCard1().getCardValue() == 5) {
            player.getCard1().setImageId(R.drawable.princecountess);
            //set card1 to unclickable
            ib1.setClickable(false);
            System.out.println("false");
        }
    }
}

