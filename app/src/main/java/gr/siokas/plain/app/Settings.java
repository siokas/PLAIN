package gr.siokas.plain.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Settings extends PreferenceActivity {

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Preference button = (Preference)findPreference("reset");
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                SharedPreferences settings = getSharedPreferences("score", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("score", "0");
                editor.commit();

                SharedPreferences mist = getSharedPreferences("mistake", 0);
                SharedPreferences.Editor m = mist.edit();
                m.putString("mistake", "0");
                m.commit();

                SharedPreferences quiz = getSharedPreferences("quiz", 0);
                SharedPreferences.Editor q = quiz.edit();
                q.putString("quiz", "0");
                q.commit();

                SharedPreferences decision = getSharedPreferences("decision", 0);
                SharedPreferences.Editor d = decision.edit();
                d.putString("decision", "0");
                d.commit();

                Toast.makeText(Settings.this, "Game Just Reseted!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

}
