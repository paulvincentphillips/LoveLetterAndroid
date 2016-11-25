package com.example.padcf.loveletter;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        //instantiate your image buttons here for use
        final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton2);


        //instatiate your toggle button here
        final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

        //set the images to be invisible on start
        ib.setVisibility(View.INVISIBLE);
        ib2.setVisibility(View.INVISIBLE);


        //call the onListner method to do stuff when yohu click on an image button, pass in the two images
        addListnerOnButton(ib, ib2);


        //this method sets the images visible or invisible when clicked...this is the toggle button
        mainButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ib.setVisibility(View.VISIBLE);
                    ib2.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Choose a card", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ib.setVisibility(View.INVISIBLE);
                    ib2.setVisibility(View.INVISIBLE);
                }



            }
        });






    }

    //this method let's you do stuff when you click on a button
    public void addListnerOnButton(View ib, View ib2)
    {

        //here we decide what happens when you click on either image. each image is passed in as a formal parameter, ib or ib2
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You pressed button ONE", Toast.LENGTH_SHORT).show();

            }
        }
        );

        ib2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(getApplicationContext(), "You pressed button TWO", Toast.LENGTH_SHORT).show();
              }
        }
        );




    }



}
