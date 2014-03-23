package gr.siokas.plain.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Map extends Activity {

    Button zoo_bt, kastra_bt, rotonda_bt, lefkos_bt, dimitrios_bt, alexandros_bt, key;
    Dialog dialog;
    EditText zoo, lefkos, dimitrios, kastra, alexandros, rotonda;

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
                Toast.makeText(Map.this,"Kastra",1).show();
                if (getScore() == 3)
                    right();
                else
                    wrong();
            }
        });

        rotonda_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Map.this,"Rot",1).show();
                if (getScore() == 6)
                    right();
                else
                    wrong();
            }
        });

        lefkos_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Map.this,"Lefk",1).show();
                if (getScore() == 9)
                    right();
                else
                    wrong();
            }
        });

        dimitrios_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Map.this,"Jim",1).show();
                if(getScore() == 12)
                    right();
                else
                    wrong();
            }
        });

        alexandros_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Map.this,"Alx",1).show();
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
                if (zoo.getText().toString().toUpperCase().equals("ΙΕΡΑΞ"))
                    saveZoo(zoo.getText().toString().toUpperCase());
                else
                    Toast.makeText(Map.this, "ERROR", 1);

                if (alexandros.getText().toString().toUpperCase().equals("ΣΑΡΙΣΑ"))
                    saveAlexandros(alexandros.getText().toString().toUpperCase());
                else
                    Toast.makeText(Map.this, "ERROR", 1);

                if (dimitrios.getText().toString().toUpperCase().equals("ΝΑΟΣ"))
                    saveDimitrios(dimitrios.getText().toString().toUpperCase());
                else
                    Toast.makeText(Map.this, "ERROR", 1);

                if (lefkos.getText().toString().toUpperCase().equals("ΟΡΙΖΩΝ"))
                    saveLefkos(lefkos.getText().toString().toUpperCase());
                else
                    Toast.makeText(Map.this, "ERROR", 1);

                if (kastra.getText().toString().toUpperCase().equals("ΗΛΙΟΣ"))
                    saveKastra(kastra.getText().toString().toUpperCase());
                else
                    Toast.makeText(Map.this, "ERROR", 1);

                if (rotonda.getText().toString().toUpperCase().equals("ΣΤΡΟΓΓΥΛΗ"))
                    saveRotonda(rotonda.getText().toString().toUpperCase());
                else
                    Toast.makeText(Map.this, "ERROR", 1);

                dialog.dismiss();
            }
        });
    }

    // Call this method to get current the score value
    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");
        return (Integer.parseInt(theScore));
    }

    void checkDatabase() {
        if (getZoo().equals("ΙΕΡΑΞ"))
            zoo.setText("ΙΕΡΑΞ");
        if (getAlexandros().equals("ΣΑΡΙΣΑ"))
            alexandros.setText("ΣΑΡΙΣΑ");
        if (getDimitrios().equals("ΝΑΟΣ"))
            dimitrios.setText("ΝΑΟΣ");
        if (getKastra().equals("ΗΛΙΟΣ"))
            kastra.setText("ΗΛΙΟΣ");
        if (getLefkos().equals("ΟΡΙΖΩΝ"))
            lefkos.setText("ΟΡΙΖΩΝ");
        if (getRotonda().equals("ΣΤΡΟΓΓΥΛΗ"))
            rotonda.setText("ΣΤΡΟΓΓΥΛΗ");
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
}
