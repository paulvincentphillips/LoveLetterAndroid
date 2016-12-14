package com.example.padcf.loveletter;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    //turn order variables. Side note here: there is a way we can use modulo to deal with this. Something to consider when all is finished etc
    int turnOrder = 0;
    int turnOrder2 = 1;
    int turnOrder3 = 2;
    int turnOrder4 = 3;

    //stores which card the user has pressed
    int cardChoice = 0;
    //store the uniqueID of the card chosen
    int cardChosenId = 0;

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
    int deckLength;


    //On create method for the options menu in top rght hand corner of main screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.feeds_menu, menu);
        return true;
    }

    //method for selecting items in that menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.playCardsMenu) {
            Intent intent = new Intent(MainActivity.this, playedCards.class);
            startActivity(intent);
            return true;
        }

        //switch (item.getItemId()) {
        // case R.id.playedCardsMenu:

        // Intent intent = new Intent(this, playedCards.class);
        //  startActivity(intent);


        // break;
        //case R.id.settings:
        //break;
        //case R.id.help:
        //}
        return super.onOptionsItemSelected(item);
    }


    //onCreate which simply set's up the player Order. Perhaps this could be done elsewhere?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //take in the string array from the previous activity
        Bundle b = this.getIntent().getExtras();
        String[] nameArray = b.getStringArray("namesArray");

        //System.out.println("First" + nameArray[0]);
        //System.out.println("Second" +nameArray[1]);
        //System.out.println("Third" +nameArray[2]);
        //System.out.println(nameArray.length);

        //set up the players with user input from previous activity
        player1.setPlayerName(nameArray[0]);
        player2.setPlayerName(nameArray[1]);
        player3.setPlayerName(nameArray[2]);
        player4.setPlayerName(nameArray[3]);

        //set up player order
        playerOrder = randomPlayer(playerOrder, player1, player2, player3, player4);

        mainDeck.populateDeck(); // populate the deck


        //send the program off to beginRound
        beginRound();


    }

    //beginRound method that does the things required for each round
    private void beginRound()
    {
        //reset players playedCards to -1
        player1.resetPlayedCardsArray();
        player2.resetPlayedCardsArray();
        player3.resetPlayedCardsArray();
        player4.resetPlayedCardsArray();

        player1.setPlaying(true);
        player2.setPlaying(true);
        player3.setPlaying(true);
        player4.setPlaying(true);

        player1.setPlayedHandmaid(false);
        player2.setPlayedHandmaid(false);
        player3.setPlayedHandmaid(false);
        player4.setPlayedHandmaid(false);

        //CARD SETUP
        mainDeck.shuffleDeck(); //shuffle the deck

        deckLength = deck1.length - 1;

        //DEAL CARDS
        deckLength = burnCard(deckLength); //burn off a card before dealing

        //method to deal out the cards and also hold the new value of deckLength returned from method in deckLength variable
        //this method will only work for starting hands as method stores cards in Card1 slot only
        deckLength = dealCards(player1, player2, player3, player4, deckLength, deck1);

        //once finished here, move the program on to beginTurn
        beginTurn();
    }

    //this is the beginTurn method which does stuff for each playerTurn
    private void beginTurn()
    {
        if (!playerOrder[turnOrder].getIsPlaying()) {
            endTurn();
        } else {
            playerOrder[turnOrder].setPlayedHandmaid(false);
        }

        //deal 2nd card to current player
        deckLength = dealCard2(playerOrder[turnOrder], deckLength, deck1);

        //ib.setImageResource(player1.getCard2().getImageId());
        final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton2);

        final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

        mainButton.setText("Reveal " + playerOrder[turnOrder].getPlayerName() + " 's cards" );
        mainButton.setTextOff("Reveal " + playerOrder[turnOrder].getPlayerName() + " 's cards" );
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
                if (isChecked) {
                    //call the addButtonListen here, when toggle is switched to reveal cards
                    ib.setClickable(true);
                    ib2.setClickable(true);
                    ib.setImageResource(playerOrder[turnOrder].getCard1().getImageId());
                    ib2.setImageResource(playerOrder[turnOrder].getCard2().getImageId());
                    Toast.makeText(getApplicationContext(), "Choose a card", Toast.LENGTH_SHORT).show();
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

    // this method will contain the different displays for the relevant card
    //pass in the card id and the display() method will select the correct card depending on that ID.
    private void display(int cardId)
    {
        //instantiate the mainToggle button again to access it
        final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);


        //*** BEGIN OF CARD DISPLAYS ***

        //displays for guard
        if(cardChosenId == 1)
        {
            //code goes here
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
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });
        }

        //Displays for priest, Baron and King
        if(cardId == 2 || cardId == 3 || cardId == 6)
        {

            playerOrder[turnOrder].setPlayedHandmaid(false);

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
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });
        }

        //display for handmaid, countess and princess done here
        if(cardChosenId == 4 || cardChosenId == 7 || cardChosenId == 8)
        {

            //set up on click for buttons
            final Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);
            if(cardChosenId == 4)
            {
                fourSevenEight.setText("Play Handmaid");
            }
            else if(cardChosenId == 7)
            {
                fourSevenEight.setText("Play Countess");
            }
            else
            {
                fourSevenEight.setText("Play Princess");
            }

            fourSevenEight.setVisibility(View.VISIBLE);
            fourSevenEight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    fourSevenEight.setVisibility(View.INVISIBLE);
                    endTurn();
                }
            });



        }

        //displays for prince
        if(cardChosenId == 5)
        {
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
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button1.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button2.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, Integer.parseInt(button3.getTag().toString()), cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    endTurn();
                }
            });

            //display for handmaid, countess and princess done here
            //set up on click for buttons
            final Button fourSevenEight = (Button) findViewById(R.id.fourSevenEight);

            fourSevenEight.setText("Play Prince");

            fourSevenEight.setVisibility(View.VISIBLE);
            fourSevenEight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardChoice == 1) {
                        deckLength = playerOrder[turnOrder].getCard1().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard1());
                    } else {
                        deckLength = playerOrder[turnOrder].getCard2().specialFunction(playerOrder[turnOrder],
                                playerOrder[turnOrder2], playerOrder[turnOrder3], playerOrder[turnOrder4], deckLength,
                                deck1, 0, cardChoice);
                        //add the played card to the array of played cards in the Player Class
                        playerOrder[turnOrder].setPlayedCard(playerOrder[turnOrder].getCard2());
                    }
                    mainButton.setChecked(false); //set toggle button back when a player has made their choice
                    fourSevenEight.setVisibility(View.INVISIBLE);
                    endTurn();
                }
            });
        }


    }






    //we will need an end round function to check and update stuff at the end of every round
    private void endTurn()
    {
        RelativeLayout relLayout = (RelativeLayout)findViewById(R.id.threeButtonLayout);
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
        if (deckLength == 0) {
            int cardVal1 = 0;
            int cardVal2 = 0;
            int cardVal3 = 0;
            int cardVal4 = 0;

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

            if (cardVal1 > cardVal2 && cardVal1 > cardVal3 && cardVal1 > cardVal4) {
                player1.setScore(player1.getPlayerScore() + 1);
                System.out.println(player1.getPlayerName() + " has the highest value card and has won the round!");
                toastWinner(player1);
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



        nextTurn();

        beginTurn();
    }


    private void endGame(){

    }


    //BELOW ARE THE OTHER METHODS TAKEN FORM THE JAVA VERSION

    //deal out cards
    public static int dealCards(Player pOne, Player pTwo, Player pThree, Player pFour, int deckLength, Card[] deck)
    {
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

    //burn off one card
    public static int burnCard(int deckLength)
    {
        deckLength--;
        return deckLength;
    }

    //random player method to randomise player order
    public static Player [] randomPlayer(Player[] array, Player pOne, Player pTwo, Player pThree, Player pFour){
        int random =(int)(Math.random()*4);

        if(random == 1){
            array[0] = pOne;
            array[1] = pTwo;
            array[2] = pThree;
            array[3] = pFour;
        }
        else if(random == 2){
            array[0] = pTwo;
            array[1] = pThree;
            array[2] = pFour;
            array[3] = pOne;
        }
        else if(random == 3){
            array[0] = pThree;
            array[1] = pFour;
            array[2] = pOne;
            array[3] = pTwo;
        }else{
            array[0] = pFour;
            array[1] = pOne;
            array[2] = pTwo;
            array[3] = pThree;
        }

        return array;
    }

    public void toastWinner(Player winner){
        Toast.makeText(this, winner.getPlayerName() + " has won the round!", Toast.LENGTH_SHORT).show();
    }

    public void nextTurn(){
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
    }

    public void winnerTurn(Player winner, Player [] playerArray){
        for(int x = 0; x < playerArray.length; x++) {
            if(winner.getPlayerName().equals(playerArray[x].getPlayerName())){
                turnOrder = x;
                if(turnOrder == 0){
                    turnOrder2 = 1;
                    turnOrder3 = 2;
                    turnOrder4 = 3;
                }
                else if(turnOrder==1){
                    turnOrder2 = 2;
                    turnOrder3 = 3;
                    turnOrder4 = 0;
                }
                else if(turnOrder==2){
                    turnOrder2 = 3;
                    turnOrder3 = 0;
                    turnOrder4 = 1;
                }else{
                    turnOrder2 = 0;
                    turnOrder3 = 1;
                    turnOrder4 = 2;
                }
            }
        }
    }

    //this method let's you do stuff when you click on a button
    public void addListnerOnButton(View ib, View ib2)
    {
        //here we decide what happens when you click on either image. each image is passed in as a formal parameter, ib or ib2
        ib.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      cardChoice = 1;
                                      cardChosenId = playerOrder[turnOrder].getCard1().getCardValue();
                                      Toast.makeText(getApplicationContext(), playerOrder[turnOrder].getCard1().getCardName(), Toast.LENGTH_SHORT).show();
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
                                       Toast.makeText(getApplicationContext(), playerOrder[turnOrder].getCard2().getCardName(), Toast.LENGTH_SHORT).show();
                                       RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
                                       display(cardChosenId); //pass in the chosenCardID here, because it needs to then be passed to display() which in turn will filter the content to display
                                       //relLayout.setVisibility(View.VISIBLE);
                                   }
                               }
        );




    }

    public static int dealCard2(Player player, int deckLength, Card[] deck){
        player.setCard2(deck[deckLength]);
        deckLength--;
        return deckLength;
    }


}