package gr.siokas.plain.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class Map extends Activity {

    Button zoo_bt, kastra_bt, rotonda_bt, lefkos_bt, dimitrios_bt, alexandros_bt, key;
    Dialog dialog;
    EditText zoo, lefkos, dimitrios, kastra, alexandros, rotonda;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        zoo_bt = (Button) findViewById(R.id.zoo_bt);
        kastra_bt = (Button) findViewById(R.id.kastra_bt);
        rotonda_bt = (Button) findViewById(R.id.rotonda_bt);
        lefkos_bt = (Button) findViewById(R.id.lefkos_bt);
        dimitrios_bt = (Button) findViewById(R.id.dimitrios_bt);
        alexandros_bt = (Button) findViewById(R.id.alex_bt);
        key = (Button) findViewById(R.id.key);

        key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveKeywords();
            }
        });


        zoo_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong();
            }
        });

        kastra_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 3)
                    right();
                else
                    wrong();
            }
        });

        dimitrios_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getScore() == 6)
                    right();
                else
                    wrong();
            }
        });

        rotonda_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 9)
                    right();
                else
                    wrong();
            }
        });

        lefkos_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 12)
                    right();
                else
                    wrong();
            }
        });

        alexandros_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getScore() == 15)
                    right();
                else
                    wrong();
            }
        });

    }

    void right(){
        startActivity(new Intent(Map.this, Game.class));
    }
    void wrong(){
        startActivity(new Intent(Map.this, WrongMapDecision.class));
    }

    void saveKeywords() {
        dialog = new Dialog(Map.this);
        dialog.setContentView(R.layout.alertkeywords);
        dialog.setTitle("Εισαγωγή Λέξης-Κλειδί");
        dialog.show();

        zoo = (EditText) dialog.findViewById(R.id.zooKey);
        lefkos = (EditText) dialog.findViewById(R.id.lefkosKey);
        dimitrios = (EditText) dialog.findViewById(R.id.agiosDimitriosKey);
        alexandros = (EditText) dialog.findViewById(R.id.alexandrosKey);
        kastra = (EditText) dialog.findViewById(R.id.kastraKey);
        rotonda = (EditText) dialog.findViewById(R.id.rotondaKey);
        Button save = (Button) dialog.findViewById(R.id.saveKey);
        Button discard = (Button) dialog.findViewById(R.id.discard);

        checkDatabase();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zoo.getText().toString().toUpperCase().equals("ΙΕΡΑΞ")){
                    saveZoo(zoo.getText().toString().toUpperCase());
                    playCorrectSound();
                }
                else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (alexandros.getText().toString().toUpperCase().equals("ΣΑΡΙΣΑ")){
                    saveAlexandros(alexandros.getText().toString().toUpperCase());
                    playCorrectSound();
                }
                else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (dimitrios.getText().toString().toUpperCase().equals("ΝΑΟΣ")){
                    saveDimitrios(dimitrios.getText().toString().toUpperCase());
                    playCorrectSound();
                }
                else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (lefkos.getText().toString().toUpperCase().equals("ΟΡΙΖΩΝ")){
                    saveLefkos(lefkos.getText().toString().toUpperCase());
                    playCorrectSound();
                }
                else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (kastra.getText().toString().toUpperCase().equals("ΗΛΙΟΣ")){
                    saveKastra(kastra.getText().toString().toUpperCase());
                    playCorrectSound();
                }
                else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (rotonda.getText().toString().toUpperCase().equals("ΣΤΡΟΓΓΥΛΗ")){
                    saveRotonda(rotonda.getText().toString().toUpperCase());
                    playCorrectSound();
                }
                else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);


                dialog.dismiss();

                checkCorrectKeys();
            }
        });
    }

    void playCorrectSound(){
        mp = MediaPlayer.create(Map.this, R.raw.correct);
        mp.start();
    }

    void checkCorrectKeys(){
        if (getCorrectKeys() == 6){
            playCorrectSound();
            changeBack(R.drawable.xartis_complete);
        }
    }

    // Call this method to get current the score value
    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");
        return (Integer.parseInt(theScore));
    }

    void checkDatabase() {
        if (getZoo().equals("ΙΕΡΑΞ")){
            zoo.setText("ΙΕΡΑΞ");
            zoo.setEnabled(false);
            saveCorrectKeys((getCorrectKeys()+1)+"");
        }
        if (getAlexandros().equals("ΣΑΡΙΣΑ")){
            alexandros.setText("ΣΑΡΙΣΑ");
            alexandros.setEnabled(false);
            saveCorrectKeys((getCorrectKeys()+1)+"");
        }
        if (getDimitrios().equals("ΝΑΟΣ")){
            dimitrios.setText("ΝΑΟΣ");
            dimitrios.setEnabled(false);
            saveCorrectKeys((getCorrectKeys()+1)+"");
        }
        if (getKastra().equals("ΗΛΙΟΣ")){
            kastra.setText("ΗΛΙΟΣ");
            kastra.setEnabled(false);
            saveCorrectKeys((getCorrectKeys()+1)+"");
        }
        if (getLefkos().equals("ΟΡΙΖΩΝ")){
            lefkos.setText("ΟΡΙΖΩΝ");
            lefkos.setEnabled(false);
            saveCorrectKeys((getCorrectKeys()+1)+"");
        }
        if (getRotonda().equals("ΣΤΡΟΓΓΥΛΗ")){
            rotonda.setText("ΣΤΡΟΓΓΥΛΗ");
            rotonda.setEnabled(false);
            saveCorrectKeys((getCorrectKeys()+1)+"");
        }
    }

    void saveCorrectKeys(String x) {
        SharedPreferences settings = getSharedPreferences("correctKeys", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("correctKeys", x);
        editor.commit();
    }



    int getCorrectKeys() {
        SharedPreferences settings = getSharedPreferences("correctKeys", 0);
        final String correctKeys = settings.getString("correctKeys", "0");

        return (Integer.parseInt(correctKeys));
    }


    // Call this method to save Zoo Keyword
    void saveZoo(String x) {
        SharedPreferences settings = getSharedPreferences("key_zoo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key_zoo", x);
        editor.commit();
    }


    // Call this method to get the saved Zoo Keyword
    String getZoo() {
        SharedPreferences settings = getSharedPreferences("key_zoo", 0);
        final String theString = settings.getString("key_zoo", "0");
        return (theString);
    }

    // Call this method to save Zoo Keyword
    void saveLefkos(String x) {
        SharedPreferences settings = getSharedPreferences("key_lefkos", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key_lefkos", x);
        editor.commit();
    }

    // Call this method to get the saved Zoo Keyword
    String getLefkos() {
        SharedPreferences settings = getSharedPreferences("key_lefkos", 0);
        final String theString = settings.getString("key_lefkos", "0");
        return (theString);
    }

    // Call this method to save Zoo Keyword
    void saveRotonda(String x) {
        SharedPreferences settings = getSharedPreferences("key_rotonda", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key_rotonda", x);
        editor.commit();
    }

    // Call this method to get the saved Zoo Keyword
    String getRotonda() {
        SharedPreferences settings = getSharedPreferences("key_rotonda", 0);
        final String theString = settings.getString("key_rotonda", "0");
        return (theString);
    }

    // Call this method to save Zoo Keyword
    void saveKastra(String x) {
        SharedPreferences settings = getSharedPreferences("key_kastra", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key_kastra", x);
        editor.commit();
    }

    // Call this method to get the saved Zoo Keyword
    String getKastra() {
        SharedPreferences settings = getSharedPreferences("key_kastra", 0);
        final String theString = settings.getString("key_kastra", "0");
        return (theString);
    }

    // Call this method to save Zoo Keyword
    void saveDimitrios(String x) {
        SharedPreferences settings = getSharedPreferences("key_dimitrios", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key_dimitrios", x);
        editor.commit();
    }

    // Call this method to get the saved Zoo Keyword
    String getDimitrios() {
        SharedPreferences settings = getSharedPreferences("key_dimitrios", 0);
        final String theString = settings.getString("key_dimitrios", "0");
        return (theString);
    }

    // Call this method to save Zoo Keyword
    void saveAlexandros(String x) {
        SharedPreferences settings = getSharedPreferences("key_alexandros", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key_alexandros", x);
        editor.commit();
    }

    // Call this method to get the saved Zoo Keyword
    String getAlexandros() {
        SharedPreferences settings = getSharedPreferences("key_alexandros", 0);
        final String theString = settings.getString("key_alexandros", "0");
        return (theString);
    }

    void changeBack(int img) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.game_layout);
        layout.setBackgroundResource(img);
    }
}
