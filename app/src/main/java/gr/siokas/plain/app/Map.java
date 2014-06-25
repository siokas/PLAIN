package gr.siokas.plain.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Map extends Activity {

    protected static final Message BUTTON_NEUTRAL = null;
    Button zoo_bt, kastra_bt, rotonda_bt, lefkos_bt, dimitrios_bt, alexandros_bt, key, noisis, repeatQuiz;
    Dialog dialog;
    EditText zoo, lefkos, dimitrios, kastra, alexandros, rotonda, noisisK;
    TextView noisisT;
    MediaPlayer mp;
    boolean zooCount = false, lefkosCount = false, dimitriosCount = false, kastraCount = false, alexandrosCount = false, rotondaCount = false;

    AlertDialog alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final int s1 = R.raw.zoo_pros_kastra_quiz_1;
        final int s2 = R.raw.kastra_pros_dimitrio_quiz_2;
        final int s3 = R.raw.dimitrios_pros_rotonda_quiz_3;
        final int s4 = R.raw.rotonda_pros_lefko_quiz_1;
        final int s5 = R.raw.lefkos_pros_alexandro_quizz_1;

        zoo_bt = (Button) findViewById(R.id.zoo_bt);
        kastra_bt = (Button) findViewById(R.id.kastra_bt);
        rotonda_bt = (Button) findViewById(R.id.rotonda_bt);
        lefkos_bt = (Button) findViewById(R.id.lefkos_bt);
        dimitrios_bt = (Button) findViewById(R.id.dimitrios_bt);
        alexandros_bt = (Button) findViewById(R.id.alex_bt);
        repeatQuiz = (Button) findViewById(R.id.mapSound);
        key = (Button) findViewById(R.id.key);
        noisis = (Button) findViewById(R.id.noisis);
        noisis.setEnabled(false);



        repeatQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(getScore()){
                    case 3:
                        playQuizSound(s1);
                        break;
                    case 6:
                        playQuizSound(s2);
                        break;
                    case 9:
                        playQuizSound(s3);
                        break;
                    case 12:
                        playQuizSound(s4);
                        break;
                    case 15:
                        playQuizSound(s5);
                        break;
                }
            }
        });

        noisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Map.this, Noisis.class));
            }
        });

        key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveKeywords();
            }
        });


        zoo_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong(1);
            }
        });

        kastra_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 3)
                    right();
                else
                    wrong(2);
            }
        });

        dimitrios_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 6)
                    right();
                else
                    wrong(3);
            }
        });

        rotonda_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 9)
                    right();
                else
                    wrong(4);
            }
        });

        lefkos_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 12)
                    right();
                else
                    wrong(5);
            }
        });

        alexandros_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 15)
                    right();
                else
                    wrong(6);
            }
        });

    }

    void right() {
        startActivity(new Intent(Map.this, Game.class));
        finish();
    }

    void wrong(int x) {
        Intent intent = new Intent(Map.this, WrongDecision.class);
        intent.putExtra("mon", x);
        startActivity(intent);
        finish();
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
        noisisK = (EditText) dialog.findViewById(R.id.noisisKey);

        noisisT = (TextView) dialog.findViewById(R.id.noisisTxt);

        Button save = (Button) dialog.findViewById(R.id.saveKey);
        Button discard = (Button) dialog.findViewById(R.id.discard);

        zoo.setEnabled(true);
        lefkos.setEnabled(true);
        dimitrios.setEnabled(true);
        kastra.setEnabled(true);
        alexandros.setEnabled(true);
        rotonda.setEnabled(true);

        checkDatabase();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zoo.getText().toString().toUpperCase().equals("ΙΕΡΑΞ")) {
                    saveZoo(zoo.getText().toString().toUpperCase());
                    playCorrectSound();
                } else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (alexandros.getText().toString().toUpperCase().equals("ΣΑΡΙΣΑ")) {
                    saveAlexandros(alexandros.getText().toString().toUpperCase());
                    playCorrectSound();
                } else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (dimitrios.getText().toString().toUpperCase().equals("ΝΑΟΣ")) {
                    saveDimitrios(dimitrios.getText().toString().toUpperCase());
                    playCorrectSound();
                } else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (lefkos.getText().toString().toUpperCase().equals("ΟΡΙΖΩΝ")) {
                    saveLefkos(lefkos.getText().toString().toUpperCase());
                    playCorrectSound();
                } else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (kastra.getText().toString().toUpperCase().equals("ΗΛΙΟΣ")) {
                    saveKastra(kastra.getText().toString().toUpperCase());
                    playCorrectSound();
                } else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (rotonda.getText().toString().toUpperCase().equals("ΣΤΡΟΓΓΥΛΗ")) {
                    saveRotonda(rotonda.getText().toString().toUpperCase());
                    playCorrectSound();
                } else
                    Toast.makeText(Map.this, "Λάθος! Προσπαθήστε ξανά", Toast.LENGTH_LONG);

                if (noisisK.getText().toString().toUpperCase().equals("ΝΟΗΣΙΣ")) {
                    dialog.dismiss();
                    playCorrectSound();
                    changeBack(R.drawable.xartis_complete);
                    noisis.setEnabled(true);
                } else {
                    checkDatabase();
                    if (checkCorrectKeys()) {
                        noisisK.setVisibility(View.VISIBLE);
                        noisisT.setVisibility(View.VISIBLE);
                    } else dialog.dismiss();
                }
            }
        });
    }

    void playCorrectSound() {
        mp = MediaPlayer.create(Map.this, R.raw.correct);
        mp.start();
    }

    void playQuizSound(int x){
        mp = MediaPlayer.create(Map.this, x);
        mp.start();
    }

    boolean checkCorrectKeys() {
        if (getCorrectKeys() >= 5) {
            alert = new AlertDialog.Builder(Map.this).create();
            alert.setTitle("Συγχαρητήρια");
            alert.setMessage("Βρήκατε τις 6 λέξεις κλειδιά από τα μνημεία. \n " +
                    "Πάρτε το πρώτο γράμμα από την κάθε λέξη-κλειδί και βέλτε τα όλα μαζί. Αναγραμματίστε " +
                    "μέχρι να βρείτε την κρυψώνα του Άργου! \n" +
                    "Ζητήστε βοήθεια από κάποιον ενήλικο αν δυσκολευτείτε. Ο Άργος σας περιμένει!");
            alert.setButton("Επιστροφή", BUTTON_NEUTRAL);
            alert.show();

            return true;
        } else
            return false;
    }

    // Call this method to get current the score value
    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");
        return (Integer.parseInt(theScore));
    }

    void checkDatabase() {

        if (getZoo().equals("ΙΕΡΑΞ")) {
            zoo.setText("ΙΕΡΑΞ");
            zoo.setEnabled(false);
            if (!zooCount) {
                saveCorrectKeys((getCorrectKeys() + 1) + "");
                zooCount = true;
            }

        }

        if (getAlexandros().equals("ΣΑΡΙΣΑ")) {
            alexandros.setText("ΣΑΡΙΣΑ");
            alexandros.setEnabled(false);
            if (!alexandrosCount) {
                saveCorrectKeys((getCorrectKeys() + 1) + "");
                alexandrosCount = true;
            }

        }
        if (getDimitrios().equals("ΝΑΟΣ")) {
            dimitrios.setText("ΝΑΟΣ");
            dimitrios.setEnabled(false);
            if (!dimitriosCount) {
                saveCorrectKeys((getCorrectKeys() + 1) + "");
                dimitriosCount = true;
            }

        }

        if (getKastra().equals("ΗΛΙΟΣ")) {
            kastra.setText("ΗΛΙΟΣ");
            kastra.setEnabled(false);
            if (!kastraCount) {
                saveCorrectKeys((getCorrectKeys() + 1) + "");
                kastraCount = true;
            }

        }
        if (getLefkos().equals("ΟΡΙΖΩΝ")) {
            lefkos.setText("ΟΡΙΖΩΝ");
            lefkos.setEnabled(false);
            if (!lefkosCount) {
                saveCorrectKeys((getCorrectKeys() + 1) + "");
                lefkosCount = true;
            }
        }

        if (getRotonda().equals("ΣΤΡΟΓΓΥΛΗ")) {
            rotonda.setText("ΣΤΡΟΓΓΥΛΗ");
            rotonda.setEnabled(false);
            if (rotondaCount) {
                saveCorrectKeys((getCorrectKeys() + 1) + "");
                rotondaCount = true;
            }

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

    // Call this method to save Lefkos Pirgos Keyword
    void saveLefkos(String x) {
        SharedPreferences settings = getSharedPreferences("key_lefkos", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key_lefkos", x);
        editor.commit();
    }

    // Call this method to get the Lefkos Pirgos Keyword
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
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.map_layout);
        layout.setBackgroundResource(img);
    }

    // Call this method if the back button is pressed (for lower apis)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(Map.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Call this method if the back button is pressed (for higher apis)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Map.this, MainActivity.class));
        finish();
    }
}
