package com.example.padcf.loveletter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by batemanapproves on 16/12/2016.
 */

public class GuardLayout extends Activity {

    Bundle guardChoice = new Bundle();


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
                guardChoice.putInt("Guard choice", 2);
            }
        });

        baronChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 3);
            }
        });

        handmaidChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 4);
            }
        });

        princeChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 5);
            }
        });

        kingChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 6);
            }
        });

        countessChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 7);
            }
        });

        princessChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 8);
            }
        });
    }
}
