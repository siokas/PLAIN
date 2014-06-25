package gr.siokas.plain.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class WrongDecision extends ActionBarActivity {

    Button back, backToMap;
    MediaPlayer mp;
    int backg=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_decision);
        Intent intent  = getIntent();
        backg = intent.getIntExtra("mon",1);

        back = (Button) findViewById(R.id.wrongBack);
        backToMap = (Button) findViewById(R.id.backToMap);

        changeImg();
        mp = MediaPlayer.create(this, R.raw.lathos_epilogi_mnimeiou);
        mp.start();

        backToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WrongDecision.this, Map.class));
                mp.stop();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WrongDecision.this, WrongMapDecision.class));
                mp.stop();
            }
        });


    }

    void changeImg() {
        if (backg == 1)
            changeBack(R.drawable.sketch1); // Change the source of the image : Zoo
        if (backg == 2)
            changeBack(R.drawable.sketch4); // Change the source of the image : Kastra
        if (backg == 3)
            changeBack(R.drawable.sketch7); // Change the source of the image : Dimitris
        if (backg == 4)
            changeBack(R.drawable.sketch10); // Change the source of the image : Rotonda
        if (backg == 5)
            changeBack(R.drawable.sketch13); // Change the source of the image : Lefkos
        if (backg == 6)
            changeBack(R.drawable.sketch16); // Change the source of the image : Alexandros
    }

    void changeBack(int img) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.wrong_layout);
        layout.setBackgroundResource(img);
    }

    // Call this method to get current the score value
    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");
        return (Integer.parseInt(theScore));
    }


    // Call this method if the back button is pressed (for lower apis)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(WrongDecision.this, MainActivity.class)); // Go to the Main Activity
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Call this method if the back button is pressed (for higher apis)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(WrongDecision.this, MainActivity.class));
        finish();
    }

}
