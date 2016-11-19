package com.example.padcf.loveletter;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView image1 = (ImageView) findViewById(R.id.imageView1);
        final ImageView image2 = (ImageView) findViewById(R.id.imageView2);
        final ToggleButton mainButton = (ToggleButton) findViewById(R.id.toggleButton);

        image1.setVisibility(View.INVISIBLE);
        image2.setVisibility(View.INVISIBLE);

        mainButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                }
                else
                {
                    image1.setVisibility(View.INVISIBLE);
                    image2.setVisibility(View.INVISIBLE);
                }

            }
        });


    }
}
