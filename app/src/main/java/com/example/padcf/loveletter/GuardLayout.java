package com.example.padcf.loveletter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Layout for guard class
 * This is necessary as after you choose your target player you must then guess a card which they may or may not possess
 * A new intent is the cleanest way we found of accomplishing this task
 * Created by padcf, paulvincentphillips & bradyc12 on 16/12/2016.
 */

public class GuardLayout extends Activity {

    //Bundle guardChoice = new Bundle();
    Intent returnIntent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.guard);

        final Button priestChoice = (Button) findViewById(R.id.priestChoice);
        final Button baronChoice = (Button) findViewById(R.id.baronChoice);
        final Button handmaidChoice = (Button) findViewById(R.id.handmaidChoice);
        final Button princeChoice = (Button) findViewById(R.id.princeChoice);
        final Button kingChoice = (Button) findViewById(R.id.kingChoice);
        final Button countessChoice = (Button) findViewById(R.id.countessChoice);
        final Button princessChoice = (Button) findViewById(R.id.princessChoice);

        priestChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.putExtra("guardChoice",2);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();       //destroys this activity and returns to main
            }
        });

        baronChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.putExtra("guardChoice",3);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();       //destroys this activity and returns to main
            }
        });


        handmaidChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.putExtra("guardChoice",4);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();       //destroys this activity and returns to main
            }
        });

        princeChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.putExtra("guardChoice",5);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();       //destroys this activity and returns to main
            }
        });

        kingChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.putExtra("guardChoice",6);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();       //destroys this activity and returns to main
            }
        });

        countessChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.putExtra("guardChoice",7);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();       //destroys this activity and returns to main
            }
        });

        princessChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.putExtra("guardChoice",8);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();       //destroys this activity and returns to main
            }
        });
    }
}
