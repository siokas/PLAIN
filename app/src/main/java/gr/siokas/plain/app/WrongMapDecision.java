package gr.siokas.plain.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class WrongMapDecision extends ActionBarActivity {

    TextView nWrongs;
    Button goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_map_decision);

        goback = (Button) findViewById(R.id.goback);
        nWrongs = (TextView) findViewById(R.id.wrongdeci);

        int mistakes = getMistakes();

        switch (mistakes){
            case 0:
                nWrongs.setText("1");
                saveMistakes("1");
                Toast.makeText(WrongMapDecision.this, "Quiz Reminder", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
            case 1:
                nWrongs.setText("2");
                saveMistakes("2");
                Toast.makeText(WrongMapDecision.this, "Hint 1", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
            case 2:
                nWrongs.setText("3");
                saveMistakes("3");
                Toast.makeText(WrongMapDecision.this, "Hint 2", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
            case 3:
                nWrongs.setText("4");
                saveMistakes("4");
                Toast.makeText(WrongMapDecision.this, "Random Quizzes", Toast.LENGTH_LONG).show();
                mistakes++;
                break;
        }

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WrongMapDecision.this, Map.class));
            }
        });


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wrong_map_decision, menu);
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
