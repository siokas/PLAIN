package gr.siokas.plain.app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class Intro extends ActionBarActivity {

    // Declare the variables
    Button next, previous;
    int step = 1;
    MediaPlayer mp;
    RelativeLayout layout;
    int resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Associate the variables with the corresponding id's
        next = (Button) findViewById(R.id.intro_next);
        previous = (Button) findViewById(R.id.intro_previous);

        playSound(1);
        previous.setVisibility(View.INVISIBLE); // Set the 'previous' button to be invisible

        // Call this method if the 'next' button is pressed
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (step) {
                    case 1: // If it is the first step do the following
                        mp.pause();
                        changeImage(R.drawable.intro2);
                        previous.setVisibility(View.VISIBLE); // Set the 'previous' button to be visible
                        playSound(2);
                        step++; // Increase the step by one
                        break;
                    case 2: // Again if it is the second step do the following
                        mp.pause();
                        startActivity(new Intent(Intro.this, Game.class)); // Start the 'Game' Activity
                        break;
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                changeImage(R.drawable.intro1); // Change the source of the image
                previous.setVisibility(View.INVISIBLE); // Set the 'previous' button to be visible
                playSound(1);
                step--;
            }
        });

    }


    void playSound(int x) {
        if (x == 1) {
            mp = MediaPlayer.create(this, R.raw.intro_kartela_1_saloni);
        }
        if (x == 2) {
            mp = MediaPlayer.create(this, R.raw.intro_kartela_2_leoforeio);
        }

        mp.start();
    }

    void changeImage(int img) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.intro_layout);
        layout.setBackgroundResource(img);
    }

}
