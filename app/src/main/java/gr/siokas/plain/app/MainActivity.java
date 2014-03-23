package gr.siokas.plain.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Button start, settings, info;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        start = (Button) findViewById(R.id.start);
        settings = (Button) findViewById(R.id.settings);
        info = (Button) findViewById(R.id.info);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Οδηγείες Παιχνιδιού");
                alert.setMessage("Ο Άργος, το χρυσό κυνηγόσκυλο, χάθηκε στη Θεσσαλονίκη.\n" +
                        "Δουλειά του Φοίβου και της Άρτεμης είναι να τον βρούν. \n" +
                        "Βοήθησέ τους! \n" +
                        " \n" +
                        "Προτείνετε αποτελεσματικές στρατηγικές αναζήτησης του Άργου. \n" +
                        "Λύστε τους γρίφους που οδηγούν στο μνημείο όπου είναι κρυμμένος ο Άργος!\n" +
                        "Επισκεφτείτε τα πιο εμβληματικά μνημεία της Θεσσαλονίκης ακολουθώντας τα στοιχεία που παρέχουν οι χαρακτήρες του παιχνιδιού. Εξερευνήστε την πόλη!.\n" +
                        "Ανακαλύψτε τις κρυμμένες αυτοκόλλητες ετικέτες που υπάρχουν στα μνημεία και αποκρυππτογραφείστε τους κωδικούς.\n" +
                        "Ανακαλύψτε που κρύβεται  ο Άργος και κερδίστε!\n" +
                        "\n" +
                        "Tα παιχνίδια “Ο Άργος τό ‘σκασε…” παίζονται σε 3 επίπεδα:\n" +
                        "\n" +
                        "Καθοδηγήστε τη στρατηγική αναζήτησης που εφαρμόζουν ο Φοίβος και η Άρτεμις σε κάθε μνημείο (εύκολοι γρίφοι για μικρότερα παιδιά)\n" +
                        "Προάγετε την αναζήτηση απο το ένα μνημείο στο επόμενο (γρίφος μνημείων)\n" +
                        "Αναζητήστε την ακριβή θέση  όπου βρισκόταν η σκιτσογράφος του PLAIN όταν ζωγράφισε την κάθε καρτέλα μνημείου. Σταθείτε στην ίδια θέση και κοιτάξτε τη σκηνή. " +
                        "Κάτι κοντά στη διασταύρωση των διαγωνίων του σκίτσου θα λείπει! Ψάξτε στο σημείο αυτό, βρείτε και σκανάρετε την ετικέτα PLAIN (QR code), συλλέξτε τη λέξη κλειδί " +
                        "που υπάρχει στο κάθε μνημείο σε 2 θέσεις (κυνήγι θησαυρού).\n");
                alert.setNegativeButton("Επιστροφή", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getScore() == 0)
                    showNameInput();
                else
                    startActivity(new Intent(MainActivity.this, Game.class));
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });
    }

    void showNameInput() {
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.alertdialog);
        dialog.setTitle("Αποθήκευση Στοιχείων");

        final EditText name = (EditText) dialog.findViewById(R.id.name);
        final EditText age = (EditText) dialog.findViewById(R.id.age);
        final Spinner sex = (Spinner) dialog.findViewById(R.id.sex);
        Button save = (Button) dialog.findViewById(R.id.nameSave);


        List<String> list = new ArrayList<String>();
        list.add("Άρρεν");
        list.add("Θήλι");


        ArrayAdapter<String> dataAd = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(dataAd);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || age.getText().toString().equals("")) {
                    // Do this
                } else {
                    saveName(name.getText().toString());
                    saveAge(age.getText().toString());
                    if (sex.getSelectedItem().toString().equals("Άρρεν"))
                        saveSex("male");
                    else
                        saveSex("female");
                    startActivity(new Intent(MainActivity.this, Intro.class));
                }
            }
        });

        dialog.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    int getScore() {
        SharedPreferences settings = getSharedPreferences("score", 0);
        final String theScore = settings.getString("score", "0");

        return (Integer.parseInt(theScore));
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

    void saveSex(String x) {
        SharedPreferences settings = getSharedPreferences("sex", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("sex", x);
        editor.commit();
    }


}
