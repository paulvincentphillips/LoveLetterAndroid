package com.example.padcf.loveletter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isHidden = true;

    public void revealHideCards(View view) {

        if (isHidden) {
            ImageView image1 = (ImageView) findViewById(R.id.card1);
            image1.setImageResource(R.drawable.baron);
            ImageView image2 = (ImageView) findViewById(R.id.card2);
            image2.setImageResource(R.drawable.priest);
            Log.i("Test", "button was clicked on");
            isHidden = false;
        }
        else{
            ImageView image1 = (ImageView) findViewById(R.id.card1);
            image1.setImageResource(R.drawable.cardback);
            ImageView image2 = (ImageView) findViewById(R.id.card2);
            image2.setImageResource(R.drawable.cardback);
            Log.i("Test", "button was clicked off");
            isHidden = true;
        }
    }

    public void choiceCard1(View view) {
        if (!isHidden){
            Toast.makeText(this, "Card 1", Toast.LENGTH_SHORT).show();
        }
    }

    public void choiceCard2(View view) {
        if (!isHidden){
            Toast.makeText(this, "Card 2", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
