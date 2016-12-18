package com.example.padcf.loveletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class
EndGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Button playAgainButton = (Button)findViewById(R.id.playAgainButon);
        Button mainMenuButton = (Button)findViewById(R.id.mainMenuButton);
        TextView winner = (TextView)findViewById(R.id.winnerTextView);

        Bundle bundlePlayerScoresNames = this.getIntent().getExtras();

        int player1Score = bundlePlayerScoresNames.getInt("Player 1 Score");
        int player2Score = bundlePlayerScoresNames.getInt("Player 2 Score");
        int player3Score = bundlePlayerScoresNames.getInt("Player 3 Score");

        String player1Name = bundlePlayerScoresNames.getString("Player 1 Name");
        String player2Name = bundlePlayerScoresNames.getString("Player 2 Name");
        String player3Name = bundlePlayerScoresNames.getString("Player 3 Name");
        String player4Name = bundlePlayerScoresNames.getString("Player 4 Name");

        if(player1Score == 4){
            winner.setText(player1Name +  " wins!");
        }
        else if(player2Score == 4){
            winner.setText(player2Name +  " wins!");
        }
        else if(player3Score == 4){
            winner.setText(player3Name +  " wins!");
        }else{
            winner.setText(player4Name +  " wins!");
        }


        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(EndGame.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenuIntent = new Intent(EndGame.this, Menu.class);
                startActivity(mainMenuIntent);
            }
        });
    }
}

