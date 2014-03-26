package gr.siokas.plain.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


public class Decision extends ActionBarActivity {

    Button choise1, choise2, choise3;
    MediaPlayer mp;
    AlertDialog.Builder alert;
    String plainData = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);

        choise1 = (Button) findViewById(R.id.choise1);
        choise2 = (Button) findViewById(R.id.choise2);
        choise3 = (Button) findViewById(R.id.choise3);

        update(getDecision()); // Call update method to change the images

        // Initialize the alert dialog
        alert = new AlertDialog.Builder(Decision.this);
        alert.setTitle("Τελική Επιλογή");
        alert.setMessage("Είστε σίγουροι ότι θέλετε να οριστικοποιήσετε αυτήν την επιλογή;");
        alert.setPositiveButton("Ναι!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
                saveDecision((getDecision() + 1) + "");
                savePlainData("," + getDecision() + "" + plainData); // Save data...
                startActivity(new Intent(Decision.this, Quiz.class));
                finish();
            }
        });
        alert.setNegativeButton("Όχι", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
            }
        });

        choise1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(((getDecision()+1)*10)+1);
                plainData = "A";
                alert.show();
            }
        });

        choise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(((getDecision()+1)*10)+1);
                System.out.println(""+(((getDecision()+1)*10)+1));
                plainData = "B";
                alert.show();
            }
        });

        choise3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(((getDecision()+1)*10)+1);
                plainData = "C";
                alert.show();
            }
        });
    }

    void saveDecision(String x) {
        SharedPreferences settings = getSharedPreferences("decision", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("decision", x);
        editor.commit();
    }

    int getDecision() {
        SharedPreferences settings = getSharedPreferences("decision", 0);
        final String theScore = settings.getString("decision", "0");

        return (Integer.parseInt(theScore));
    }

    void savePlainData(String x) {
        SharedPreferences settings = getSharedPreferences("plaindata", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("plaindata", x);
        editor.commit();
    }


    void update(int x) {

        switch (x) {
            case 0:
                choise1.setBackgroundResource(R.drawable.epiloges0_megafwno);
                choise2.setBackgroundResource(R.drawable.epiloges0_fulakas);
                choise3.setBackgroundResource(R.drawable.epiloges0_fwnes);
                break;

            case 1:
                choise1.setBackgroundResource(R.drawable.epiloges1_1_giagia);
                choise2.setBackgroundResource(R.drawable.epiloges1_1_pateras_paidi);
                choise3.setBackgroundResource(R.drawable.epiloges1_1_tileskopio);
                break;

            case 2:
                choise1.setBackgroundResource(R.drawable.epiloges2_2_tourist_guide);
                choise2.setBackgroundResource(R.drawable.epiloges2_2_paidia);
                choise3.setBackgroundResource(R.drawable.epiloges2_2_skulocookies);
                break;

            case 3:
                choise1.setBackgroundResource(R.drawable.epiloges2_1_eisitiria);
                choise2.setBackgroundResource(R.drawable.epiloges2_1_souvlatzis);
                choise3.setBackgroundResource(R.drawable.epiloges2_1_astun_skulos);
                break;

            case 4:
                choise1.setBackgroundResource(R.drawable.epiloges1_2_psaras);
                choise2.setBackgroundResource(R.drawable.epiloges1_2_odigos);
                choise3.setBackgroundResource(R.drawable.epiloges1_2_kualia);
                break;

            case 5:
                choise1.setBackgroundResource(R.drawable.epiloges3_skatades);
                choise2.setBackgroundResource(R.drawable.epiloges3_astunom);
                choise3.setBackgroundResource(R.drawable.epiloges3_sfurixtra);
                break;


        }
    }

    void playSound(int x) {
        if (x == 11)
            mp = MediaPlayer.create(this, R.raw.zoo_stratigiki_3_anakoinosi_sta_megafona);

        if (x == 12)
            mp = MediaPlayer.create(this, R.raw.zoo_stratigiki_2_erotisi_sto_filaka);

        if (x == 13)
            mp = MediaPlayer.create(this, R.raw.zoo_stratigiki_1_psaksimo);

        if (x == 21)
            mp = MediaPlayer.create(this, R.raw.kastra_epilogi_1_giagia);

        if (x == 22)
            mp = MediaPlayer.create(this, R.raw.kastra_epilogi_2_afentiko_skilou);

        if (x == 23)
            mp = MediaPlayer.create(this, R.raw.kastra_epilogi_3_tileskopio);

        if (x == 31)
            mp = MediaPlayer.create(this, R.raw.rotonda_epilogi_1_politis_eisitirion);

        if (x == 32)
            mp = MediaPlayer.create(this, R.raw.rotonda_epilogi_2_psistis);

        if (x == 33)
            mp = MediaPlayer.create(this, R.raw.rotonda_epilogi_3_astinomikos_skilos);

        if (x == 41)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_1_psaras);

        if (x == 42)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_2_odigos_leoforeiou);

        if (x == 43)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_3_kualia);

        if (x == 51)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_3_kualia);

        if (x == 52)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_3_kualia);

        if (x == 53)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_3_kualia);

        if (x == 61)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_3_kualia);

        if (x == 62)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_3_kualia);

        if (x == 63)
            mp = MediaPlayer.create(this, R.raw.lefkos_epilogi_3_kualia);



        mp.start();
    }

    // Call this method if the back button is pressed (for lower apis)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(Decision.this, MainActivity.class)); // Go to the Main Activity (Game Menu)
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Call this method if the back button is pressed (for higher apis)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Decision.this, MainActivity.class));
        finish();
    }

}
