package gr.siokas.plain.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;


public class Noisis extends Activity {

    MediaPlayer mp;
    protected static final Message BUTTON_NEUTRAL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noisis);

        mp = MediaPlayer.create(this, R.raw.correct);
        mp.start();

        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Συγχαρητήρια!");
        alert.setMessage("Βρήκατε την κρυψώνα του Άργου, στο μουσίο τεχνολογίας ΝΟΗΣΙΣ. Παραδώστε σε ένα χαρτί" +
                "τις λέξεις κλειδιά μαζί με το όνομά σας στην υποδοχή, για να πάραλάβετε" +
                "την αναμνηστική κονκάρδα νικητή!");
        alert.setButton("Επιστροφή", BUTTON_NEUTRAL);
        alert.show();

    }

    // Call this method if the back button is pressed (for lower apis)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(Noisis.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Call this method if the back button is pressed (for higher apis)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Noisis.this, MainActivity.class));
        finish();
    }


}
