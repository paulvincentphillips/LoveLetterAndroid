package com.example.padcf.loveletter;

import android.media.Image;
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

    /*
        Comments here are what my notes from the conversation with my friend Jack , it's a bit messy as we were working on this over lovely pints of IPA :) So if it doesn't all make sense
        don't worry.

        The onCreate method should do feck all except set up some small stuff like:

        1) set up initial game stuff
        2) attach any event listeners i.e 'when this button is pressed, do this'
        3) Will have something like an 'end turn button' that when pressed wi

        We can't create the while loop inside onCreate, it needs to done outside of it in another method. Basically,
        onCreate needs to start and finish, needs to return a 'I'm finsihed creating the stuff' message to android so we don't look around in here.

        In android we don't have the diving force of the overarching while loop that we did in java ( while(true) ) . Game flow needs to be compartmentalised.\
        It's user input through buttons that will progress the game along, similar to what Tom told us in the previous lab.

        Our cardChosen function - each card should have a card ID. This seems ok adn the variable cardAbility is unique to each card. The reason for this is
        that is it difficult to pass a whole class through buttons, it's better to use an ID for each class/object. This is a bit confusing...probably

        //we shouldn't do this - we shouldn't pass through a whole object as a parameter.
        public void CardChosen(Card chosenCard)
        {
            chosenCard.getSpecialAbility();
        }

        instead we should do this, use a reference int, (getCardById) to refer to the ard, then create a new Card variable and create the reference this
        way. This is basically a best practice, I think. It may work doing the above but it can cause problems.

        public void CardChosen(int chosenCardId)
        {
            Card chosenCard = getCardById(chosenCardId);
            chosenCard.getSpecialAbility();
        }

        In general:

        Have to approach dev in android backwards in relation to the java implementation, because in java you tell the user what to do, you define
        flow of events, whereas in android the user tells you what order things happen in, you need to work out 'how do i react to what the user has done'.

        Again, this was done over pints, some of it doesn't make a lot of sense :D
     */

    //CODE BEGINS HERE *********
    //******************
    //******************

    //so i had to create some new methods - beginRound, beginTurn.
    //in beginRound we do stuff that is only required at the beginning of the round. We start beginRound by calling is from onCreate
    //Once everything is finished in beginRound we call beginTurn which then does all the things we need to be doing for each player turn.
    // becuase there are many methods woring here, I needed to increase the scope of quite a few variables/objects, so they are now at the very top of the
    //class as you can see.

    //CLASS VARIABLES AND OBJECTS INSTANTIATED HERE

    //turn order variables. Side note here: there is a way we can use modulo to deal with this. Something to consider when all is finished etc
    int turnOrder = 0;
    int turnOrder2 = 1;
    int turnOrder3 = 2;
    int turnOrder4 = 3;

    //set up the four player objects
    Player player1 = new Player("james");
    //System.out.println("Player 2, please enter your name");
    Player player2 = new Player("patrick");
    //System.out.println("Player 3, please enter your name");
    Player player3 = new Player("paul");
    //System.out.println("Player 4, please enter your name");
    Player player4 = new Player("fiona");

    //instantiate the playerOrder array
    Player[] playerOrder = new Player[4];

    //deck variables setup
    Deck mainDeck = new Deck(); //instantiate the deck of cards
    Card[] deck1 = mainDeck.getDeck(); //get the deck and store it in deck1 variable
    int deckLength = deck1.length - 1;

    //this is the beginTurn method which does stuff for each playerTurn
    private void beginTurn()
    {
        //deal 2nd card to current player
        deckLength = dealCard2(playerOrder[turnOrder], deckLength, deck1);

        //set up button objects to use here
        Button button1 = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);


        //ib.setImageResource(player1.getCard2().getImageId());
        final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton2);
        final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

        //set the images to be invisible on start
        //ib.setVisibility(View.INVISIBLE);
        //ib2.setVisibility(View.INVISIBLE);

        //settting default image to carBack
        ib.setImageResource(R.drawable.cardback);
        ib.setTag(R.drawable.cardback);
        ib2.setImageResource(R.drawable.cardback);
        ib2.setTag(1);

        Integer cardBackTag = (Integer) ib.getTag();

        //call the onListner method to do stuff when you click on an image button, pass in the two images
        //**FIX THIS**
        //if(cardBackTag != R.drawable.cardback)
        //{
        addListnerOnButton(ib, ib2);
        //}

        //set up on click for button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int temp = playerOrder[0].getCard1().specialFunction(playerOrder[0], playerOrder[1],playerOrder[2],playerOrder[3], deckLength, deck1);
                boolean isPlaying = playerOrder[0].getIsPlaying();
                String isPlayingString;

                if(isPlaying)
                {
                    isPlayingString = "TRUE";
                }
                else
                {
                    isPlayingString = "FALSE";
                }
                Toast.makeText(getApplicationContext(), isPlayingString, Toast.LENGTH_SHORT).show();
            }
        });





        //this method sets the images visible or invisible when clicked...this is the toggle button
        mainButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ib.setImageResource(playerOrder[0].getCard1().getImageId());
                    ib2.setImageResource(playerOrder[0].getCard2().getImageId());
                    Toast.makeText(getApplicationContext(),"Choose a card", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ib.setImageResource(R.drawable.cardback);
                    ib2.setImageResource(R.drawable.cardback);

                    RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
                    relLayout.setVisibility(View.INVISIBLE);

                }
            }
        });
    }

    //beginRound method that does the things required for each round
    private void beginRound()
    {
        //CARD SETUP
        mainDeck.populateDeck(); // populate the deck
        mainDeck.shuffleDeck(); //shuffle the deck

        //DEAL CARDS
        deckLength = burnCard(deckLength); //burn off a card before dealing

        //method to deal out the cards and also hold the new value of deckLength returned from method in deckLength variable
        //this method will only work for starting hands as method stores cards in Card1 slot only
        deckLength = dealCards(player1, player2, player3, player4, deckLength, deck1);


        //once finished here, move the program on to beginTurn
        beginTurn();
    }

    //we will need an end round function to check and update stuff at the end of every round
    private void endRound()
    {

    }

    //here's the onCreate which simply set's up the player Order. Perhaps this could be done elsewhere?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up player order
        playerOrder = randomPlayer(playerOrder, player1, player2, player3, player4);

        //send the program off to beginRound
        beginRound();
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

    //this method let's you do stuff when you click on a button
    public void addListnerOnButton(View ib, View ib2)
    {

        //here we decide what happens when you click on either image. each image is passed in as a formal parameter, ib or ib2
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "You pressed button ONE", Toast.LENGTH_SHORT).show();
            RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
            relLayout.setVisibility(View.VISIBLE);
            }
        }
        );

        ib2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              Toast.makeText(getApplicationContext(), "You pressed button TWO", Toast.LENGTH_SHORT).show();
              RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.threeButtonLayout);
              relLayout.setVisibility(View.VISIBLE);
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
