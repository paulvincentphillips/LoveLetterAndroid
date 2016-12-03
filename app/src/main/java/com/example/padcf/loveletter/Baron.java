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

        //having chosen guard card, we now want to choose a player to apply that card on.
        //loop around until a player has been chosen. Then do what needs to be done.

        /*Scanner sc = new Scanner(System.in);


        while(true)
        {
            System.out.println("Current player: " + currentPlayer.getPlayerName());
            System.out.println("You can target:");
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
            //System.out.println(playerChoice);



            //deal with exceptional input
            if(playerChoice.equals(currentPlayer.getPlayerName()))
            {
                System.out.println("DON'T BE A FOOL - CHOOSE ANOTHER PLAYER");
            }




            //check to see if targetPlayer1 is still in the round and do targetPlayer1 stuff
            else if(playerChoice.equals(targetPlayer1.getPlayerName()))
            {

                if(!targetPlayer1.getIsPlaying())
                {
                    System.out.println("This player is already out of the round");

                }
                else if(targetPlayer1.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        System.out.println(currentPlayer.getPlayerName() + "'s card is a " + currentPlayer.getCard1().getCardName());
                        System.out.println(targetPlayer1.getPlayerName() + "'s card is a " + targetPlayer1.getCard1().getCardName());

                        if(currentPlayer.getCard1().getCardValue() > targetPlayer1.getCard1().getCardValue())
                        {
                            targetPlayer1.setPlaying(false);
                            System.out.println(targetPlayer1.getPlayerName() + " has a lower score than " + currentPlayer.getPlayerName() + ". " + targetPlayer1.getPlayerName() + " is out of this round.");
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer1.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println(targetPlayer1.getPlayerName() + " has a higher score than " + currentPlayer.getPlayerName() + ". " + currentPlayer.getPlayerName() + " is out of this round.");
                        }
                        else
                        {
                            System.out.println("Both card scores are the same. Nothing happens");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        System.out.println(currentPlayer.getPlayerName() + "'s card is a " + currentPlayer.getCard2().getCardName());
                        System.out.println(targetPlayer1.getPlayerName() + "'s card is a " + targetPlayer1.getCard1().getCardName());

                        if(currentPlayer.getCard2().getCardValue() > targetPlayer1.getCard1().getCardValue())
                        {
                            targetPlayer1.setPlaying(false);
                            System.out.println(targetPlayer1.getPlayerName() + " has a lower score than " + currentPlayer.getPlayerName() + ". " + targetPlayer1.getPlayerName() + " is out of this round.");
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer1.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println(targetPlayer1.getPlayerName() + " has a higher score than " + currentPlayer.getPlayerName() + ". " + currentPlayer.getPlayerName() + " is out of this round.");

                        }
                        else
                        {
                            System.out.println("Both card scores are the same. Nothing happens");
                        }
                    }

                    break;
                }



            }

            //check to see if targetPlayer2 is still in the round and do targetPlayer2 stuff
            else if(playerChoice.equals(targetPlayer2.getPlayerName()))
            {

                if(!targetPlayer2.getIsPlaying())
                {
                    System.out.println("This player is already out of the round");

                }
                else if(targetPlayer2.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        System.out.println(currentPlayer.getPlayerName() + "'s card is a " + currentPlayer.getCard1().getCardName());
                        System.out.println(targetPlayer2.getPlayerName() + "'s card is a " + targetPlayer2.getCard1().getCardName());

                        if(currentPlayer.getCard1().getCardValue() > targetPlayer2.getCard1().getCardValue())
                        {
                            targetPlayer2.setPlaying(false);
                            System.out.println(targetPlayer2.getPlayerName() + " has a lower score than " + currentPlayer.getPlayerName() + ". " + targetPlayer2.getPlayerName() + " is out of this round.");
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer2.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println(targetPlayer2.getPlayerName() + " has a higher score than " + currentPlayer.getPlayerName() + ". " + currentPlayer.getPlayerName() + " is out of this round.");
                        }
                        else
                        {
                            System.out.println("Both card scores are the same. Nothing happens");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        System.out.println(currentPlayer.getPlayerName() + "'s card is a: " + currentPlayer.getCard2().getCardName());
                        System.out.println(targetPlayer2.getPlayerName() + "'s card is a: " + targetPlayer2.getCard1().getCardName());

                        if(currentPlayer.getCard2().getCardValue() > targetPlayer2.getCard1().getCardValue())
                        {
                            targetPlayer2.setPlaying(false);
                            System.out.println(targetPlayer2.getPlayerName() + " has a lower score than " + currentPlayer.getPlayerName() + ". " + targetPlayer2.getPlayerName() + " is out of this round.");
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer2.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println(targetPlayer2.getPlayerName() + " has a higher score than " + currentPlayer.getPlayerName() + ". " + currentPlayer.getPlayerName() + " is out of this round.");

                        }
                        else
                        {
                            System.out.println("Both card scores are the same. Nothing happens");
                        }

                    }
                    break;
                }



            }

            //check to see if targetPlayer3 is still in the round and do targetPlayer3 stuff
            else if(playerChoice.equals(targetPlayer3.getPlayerName()))
            {

                if(!targetPlayer3.getIsPlaying())
                {
                    System.out.println("This player is already out of the round");

                }
                else if(targetPlayer3.isPlayedHandmaid()){
                    System.out.println("This player has played the handmaid and is immune until their next turn");
                }
                else
                {
                    if(!currentPlayer.getCard1().getCardName().equals("baron"))
                    {
                        System.out.println(currentPlayer.getPlayerName() + "'s card is a: " + currentPlayer.getCard1().getCardName());
                        System.out.println(targetPlayer3.getPlayerName() + "'s card is a: " + targetPlayer3.getCard1().getCardName());

                        if(currentPlayer.getCard1().getCardValue() > targetPlayer3.getCard1().getCardValue())
                        {
                            targetPlayer3.setPlaying(false);
                            System.out.println(targetPlayer3.getPlayerName() + " has a lower score than " + currentPlayer.getPlayerName() + ". " + targetPlayer3.getPlayerName() + " is out of this round.");
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer3.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println(targetPlayer3.getPlayerName() + " has a higher score than " + currentPlayer.getPlayerName() + ". " + currentPlayer.getPlayerName() + " is out of this round.");
                        }
                        else
                        {
                            System.out.println("Both card scores are the same. Nothing happens");
                        }
                    }
                    else if(!currentPlayer.getCard2().getCardName().equals("baron"))
                    {
                        System.out.println(currentPlayer.getPlayerName() + "'s card is a: " + currentPlayer.getCard2().getCardName());
                        System.out.println(targetPlayer3.getPlayerName() + "'s card is a: " + targetPlayer3.getCard1().getCardName());

                        if(currentPlayer.getCard2().getCardValue() > targetPlayer3.getCard1().getCardValue())
                        {
                            targetPlayer3.setPlaying(false);
                            System.out.println(targetPlayer3.getPlayerName() + " has a lower score than " + currentPlayer.getPlayerName() + ". " + targetPlayer3.getPlayerName() + " is out of this round.");
                        }
                        else if(currentPlayer.getCard1().getCardValue() < targetPlayer3.getCard1().getCardValue())
                        {
                            currentPlayer.setPlaying(false);
                            System.out.println(targetPlayer3.getPlayerName() + " has a higher score than " + currentPlayer.getPlayerName() + ". " + currentPlayer.getPlayerName() + " is out of this round.");

                        }
                        else
                        {
                            System.out.println("Both card scores are the same. Nothing happens");
                        }

                    }
                    break;
                }



            }





            else if(!playerChoice.equals(targetPlayer1.getPlayerName()) || !playerChoice.equals(targetPlayer2.getPlayerName()) || !playerChoice.equals(targetPlayer3.getPlayerName()) || !playerChoice.equals(currentPlayer.getPlayerName()))
            {
                //System.out.println("Player choice: " + playerChoice);
                //System.out.println("targetPlayer1: " + targetPlayer1.getPlayerName());
                //System.out.println("targetPlayer2: " + targetPlayer2.getPlayerName());
                //System.out.println("targetPlayer3: " + targetPlayer3.getPlayerName());
                //System.out.println("current player : " + currentPlayer.getPlayerName());
                //System.out.println(!playerChoice.equals(targetPlayer1.getPlayerName()) || !playerChoice.equals(targetPlayer2.getPlayerName()) || !playerChoice.equals(targetPlayer3.getPlayerName()) || !playerChoice.equals(currentPlayer.getPlayerName()));
                System.out.println("What?!");
            }


        }*/



        return length;


    }
}
