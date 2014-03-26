package gr.siokas.plain.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


public class Quiz extends ActionBarActivity {

    Button goon;
    MediaPlayer mp;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        goon = (Button) findViewById(R.id.goon);
        img = (ImageView) findViewById(R.id.maintanos);

        int step = getQuiz();

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


        goon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                saveQuiz((getQuiz() + 1) + "");
                startActivity(new Intent(Quiz.this, Map.class));
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


}
