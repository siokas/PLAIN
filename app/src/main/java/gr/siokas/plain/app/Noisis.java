package gr.siokas.plain.app;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;


public class Noisis extends Activity {

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noisis);

        mp = MediaPlayer.create(this, R.raw.correct);
        mp.start();

        Toast.makeText(Noisis.this, "Συγχαρητήρια! Το βρήκες! Ο Άργος κρύβεται στο Μουσίο Τεχνολογίας 'Νόησις' ", Toast.LENGTH_LONG).show();


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
