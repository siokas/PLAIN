package gr.siokas.plain.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class WrongMapDecision extends Activity {

    TextView nWrongs;
    Button goback;
    MediaPlayer mp;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_map_decision);

        goback = (Button) findViewById(R.id.goback);
        nWrongs = (TextView) findViewById(R.id.wrongdeci);
        img = (ImageView) findViewById(R.id.imageView123);

        int mistakes = getMistakes();

        switch (mistakes){
            case 0:
                nWrongs.setText("1");
                saveMistakes("1");
                Toast.makeText(WrongMapDecision.this, "Λάθος... Άκουσε τον γρίφο και προσπάθησε ξανά!", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
            case 1:
                nWrongs.setText("2");
                saveMistakes("2");
                Toast.makeText(WrongMapDecision.this, "Λάθος... Άκουσε τον γρίφο και προσπάθησε ξανά!", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
            case 2:
                nWrongs.setText("3");
                saveMistakes("3");
                Toast.makeText(WrongMapDecision.this, "Λάθος... Άκουσε τον γρίφο και προσπάθησε ξανά!", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
            case 3:
                nWrongs.setText("4");
                saveMistakes("4");
                Toast.makeText(WrongMapDecision.this, "Λάθος... Άκουσε τον γρίφο και προσπάθησε ξανά!", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
        }


        int step = getQuiz();
        step--;
        System.out.println(step + "steppppppp");

        switch (step) {
            case 0:
                img.setImageResource(R.drawable.maintanos_brad);
                playSound(1);
                break;

            case 1:
                img.setImageResource(R.drawable.maintanos_touristas);
                playSound(2);
                break;

            case 2:
                img.setImageResource(R.drawable.maintanos_koritsi);
                playSound(3);
                break;

            case 3:
                img.setImageResource(R.drawable.maintanos_koulourtzis);
                playSound(4);
                break;

            case 4:
                img.setImageResource(R.drawable.maintanos_podilatis);
                playSound(5);
                break;

            case 5:
                img.setImageResource(R.drawable.maintanos_jogging);
                playSound(6);
                break;

        }


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WrongMapDecision.this, Map.class));
                finish();
            }
        });


    }

    void playSound(int x) {
        int rand = getRandNum(1,3);

        switch (x){
            case 1:
                mp = MediaPlayer.create(this, R.raw.zoo_katharistis_eisagogiki_ataka);
                break;

            case 2:
                if(rand == 1)
                    mp = MediaPlayer.create(this, R.raw.kastra_pros_dimitrio_quiz_1);
                if(rand == 2)
                    mp = MediaPlayer.create(this, R.raw.kastra_pros_dimitrio_quiz_2);
                if(rand == 3)
                    mp = MediaPlayer.create(this, R.raw.kastra_pros_dimitrio_quiz_3);
                break;

            case 3:
                if(rand == 1)
                    mp = MediaPlayer.create(this, R.raw.dimitrios_pros_rotonda_quiz_1);
                if(rand == 2)
                    mp = MediaPlayer.create(this, R.raw.dimitrios_pros_rotonda_quiz_2);
                if(rand == 3)
                    mp = MediaPlayer.create(this, R.raw.dimitrios_pros_rotonda_quiz_3);
                break;

            case 4:
                if(rand == 1)
                    mp = MediaPlayer.create(this, R.raw.rotonda_pros_lefko_quiz_1);
                if(rand == 2)
                    mp = MediaPlayer.create(this, R.raw.rotonda_pros_lefko_quiz_2);
                if(rand == 3)
                    mp = MediaPlayer.create(this, R.raw.rotonda_pros_lefko_quiz_3);
                break;

            case 5:
                if(rand == 1)
                    mp = MediaPlayer.create(this, R.raw.lefkos_pros_alexandro_quizz_1);
                if(rand == 2)
                    mp = MediaPlayer.create(this, R.raw.lefkos_pros_alexandro_quizz_2);
                if(rand == 3)
                    mp = MediaPlayer.create(this, R.raw.lefkos_pros_alexandro_quizz_3);
                break;

            case 6:
                if(rand == 1)
                    mp = MediaPlayer.create(this, R.raw.correct);
                if(rand == 2)
                    mp = MediaPlayer.create(this, R.raw.correct);
                if(rand == 3)
                    mp = MediaPlayer.create(this, R.raw.correct);
                break;
        }

        mp.start();
    }

    void saveScore(String x) {
        SharedPreferences settings = getSharedPreferences("score", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("score", x);
        editor.commit();
    }

    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");

        return (Integer.parseInt(theScore));
    }

    void saveMistakes(String x) {
        SharedPreferences settings = getSharedPreferences("mistake", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("mistake", x);
        editor.commit();
    }

    int getMistakes() {
        SharedPreferences settings = getSharedPreferences("mistake", 0);
        final String theScore = settings.getString("mistake", "0");

        return (Integer.parseInt(theScore));
    }

    // Call this method to save monuments
    void saveQuiz(String x) {
        SharedPreferences settings = getSharedPreferences("quiz", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("quiz", x);
        editor.commit();
    }

    // Call this method to get current the score value
    int getQuiz() {
        SharedPreferences settings = getSharedPreferences("quiz", 0);
        final String theScore = settings.getString("quiz", "0");
        return (Integer.parseInt(theScore));
    }

    public static int getRandNum(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    // Call this method if the back button is pressed (for lower apis)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(WrongMapDecision.this, MainActivity.class)); // Go to the Main Activity
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Call this method if the back button is pressed (for higher apis)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(WrongMapDecision.this, MainActivity.class));
        finish();
    }


}
