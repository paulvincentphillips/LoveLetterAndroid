package com.example.padcf.loveletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by padcf, paulvincentphillips & bradyc12 on 14/12/16.
 */

public class playerName extends AppCompatActivity {

    final String[] namesArray = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        Bundle b = new Bundle();
        b.putStringArray("namesArray", namesArray);

        final Intent myIntent = new Intent(playerName.this, MainActivity.class);
        myIntent.putExtras(b);

        final EditText name = (EditText) findViewById(R.id.nameBox);
        final EditText name1 = (EditText) findViewById(R.id.nameBox1);
        final EditText name2 = (EditText) findViewById(R.id.nameBox2);
        final EditText name3 = (EditText) findViewById(R.id.nameBox3);

        name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER )) {
                    namesArray[0] = name.getText().toString();
                    System.out.println(namesArray[0]);
                    name.setVisibility(View.INVISIBLE);
                    name.clearComposingText();
                    name1.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });

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
