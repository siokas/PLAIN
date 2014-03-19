package gr.siokas.plain.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class Quiz extends ActionBarActivity {

    Button goon;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        goon = (Button) findViewById(R.id.goon);


        goon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz.this, Map.class));
            }
        });
    }

    void playSound(int x) {
        if (x == 1) {
            mp = MediaPlayer.create(this, R.raw.zoo_kartela_1_pili);
        }
        if (x == 2) {
            mp = MediaPlayer.create(this, R.raw.zoo_kartela_2_arkoudes);
        }
        if (x == 3)
            mp = MediaPlayer.create(this, R.raw.zoo_kartela_3_pili);

        mp.start();
    }

    void changeBack(int img) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.game_layout);
        layout.setBackgroundResource(img);
    }

    // Call this method to get current the score value
    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");
        return (Integer.parseInt(theScore));
    }

}
