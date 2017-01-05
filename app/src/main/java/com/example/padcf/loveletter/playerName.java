package com.example.padcf.loveletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by padcf, paulvincentphillips & bradyc12 on 14/12/16.
 * This activity asks users to enter their names. Their names are then stored in a String Array and passed in a bundle using an intent to the main
 * activity.
 */

public class playerName extends AppCompatActivity {

    final String[] namesArray = new String[4];//create a String array which will hold the user names.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        Bundle b = new Bundle();//create a bundle which will be used to collect all required information needed by main activity
        b.putStringArray("namesArray", namesArray); //put the String array in the bundle

        final Intent myIntent = new Intent(playerName.this, MainActivity.class);//create your intent
        myIntent.putExtras(b);//put the bundle with the intent

        //create 4 EditText objects and start them all as invisible. Make visible when required.
        final EditText name = (EditText) findViewById(R.id.nameBox);
        final EditText name1 = (EditText) findViewById(R.id.nameBox1);
        final EditText name2 = (EditText) findViewById(R.id.nameBox2);
        final EditText name3 = (EditText) findViewById(R.id.nameBox3);

        //Set listeners for each editText button
        name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //if the user presses the enter/return key, move on to the next edit text.
                if ((event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER )) {
                    namesArray[0] = name.getText().toString();//add the first username to position 0 in the array
                    name.setVisibility(View.INVISIBLE);//set the first editText box to invisible, as you don't need it anymore
                    name.clearComposingText(); //clear the text box
                    name1.setVisibility(View.VISIBLE);//set the second editText to be visible (this is player2)
                    return true;
                }
                return false;
            }
        });
        //do the same as above for the second editText box
        name1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //name1.focus
                if ((event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER )) {
                    namesArray[1] = name1.getText().toString(); 
                    System.out.println(namesArray[1]);
                    name1.setVisibility(View.INVISIBLE);
                    name1.clearComposingText();
                    name2.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
        //do the same as above for the second editText box
        name2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)) {
                    namesArray[2] = name2.getText().toString();
                    System.out.println(namesArray[2]);
                    name2.setVisibility(View.INVISIBLE);
                    name.clearComposingText();
                    name3.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
        //do the same as above for the second editText box
        //this time we start the mainActivity when the final name has been entered.
        name3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)) {
                    namesArray[3] = name3.getText().toString();
                    System.out.println("Length of array first: " + namesArray.length);

                    startActivity(myIntent);
                    return true;
                }
                return false;
            }
        }
        );


    }

}
