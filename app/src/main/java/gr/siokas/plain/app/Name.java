package gr.siokas.plain.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Name extends ActionBarActivity {

    Button ok;
    EditText name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        ok = (Button) findViewById(R.id.nameOK);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || age.getText().toString().equals("")) {
                    Toast.makeText(Name.this, "WRONG!", Toast.LENGTH_SHORT).show();
                } else {
                    saveName(name.getText().toString());
                    saveAge(age.getText().toString());
                    if (getScore() == 0)
                        startActivity(new Intent(Name.this, Intro.class));
                    else
                        startActivity(new Intent(Name.this, Game.class));
                }
            }
        });


    }

    void saveName(String x) {
        SharedPreferences settings = getSharedPreferences("name", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", x);
        editor.commit();
    }

    void saveAge(String x) {
        SharedPreferences settings = getSharedPreferences("age", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("age", x);
        editor.commit();
    }

    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");

        return (Integer.parseInt(theScore));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.name, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
