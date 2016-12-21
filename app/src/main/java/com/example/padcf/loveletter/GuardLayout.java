package com.example.padcf.loveletter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext()," Priest", Toast.LENGTH_SHORT).show();
                onBackPressed();     //destroys this activity and returns to main
            }
        });

        baronChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 3);
                onBackPressed();     //destroys this activity and returns to main
                Toast.makeText(getApplicationContext()," Baron", Toast.LENGTH_SHORT).show();
            }
        });

        //Intent intent = new Intent(getApplicationContext(), Main.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(intent);

        handmaidChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 4);
                finish();       //destroys this activity and returns to main
                Toast.makeText(getApplicationContext()," handmaid", Toast.LENGTH_SHORT).show();
            }
        });

        princeChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 5);
                finish();       //destroys this activity and returns to main
                Toast.makeText(getApplicationContext()," handmaid", Toast.LENGTH_SHORT).show();
            }
        });

        kingChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 6);
                finish();       //destroys this activity and returns to main
                Toast.makeText(getApplicationContext()," king", Toast.LENGTH_SHORT).show();
            }
        });

        countessChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 7);
                finish();       //destroys this activity and returns to main
                Toast.makeText(getApplicationContext()," countess", Toast.LENGTH_SHORT).show();
            }
        });

        princessChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardChoice.putInt("Guard choice", 8);
                finish();       //destroys this activity and returns to main
                Toast.makeText(getApplicationContext()," princess", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
