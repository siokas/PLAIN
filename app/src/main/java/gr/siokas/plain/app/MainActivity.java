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
    protected void onCreate(final Bundle savedInstanceState) {
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

                String name = getName();



                if (getScore() == 0) {
                    wipeData_player1();
                    showNameInput();
                }
                else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Επιλογή");
                    alert.setMessage("Γειά σου -" + name + "-. Θα ήθελες να συνεχίσεις ή να ξεκινήσεις νέο παιχνίδι;");
                    alert.setPositiveButton("Συνεχεια Παιχνιδιού", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, Game.class);
                            intent.putExtra("score", "0");
                            startActivity(intent);
                        }
                    });
                    alert.setNeutralButton("Νέο Παιχνίδι", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showNameInput();
                        }
                    });

                    alert.show();
                }
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
        list.add("Αγόρι");
        list.add("Κορίτσι");


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

    String getName(){
        SharedPreferences settings = getSharedPreferences("name", 0);
        final String theName = settings.getString("name", "0");
        return theName;
    }

    // Call this method to save score
    void saveScore(String x) {
        SharedPreferences settings = getSharedPreferences("score", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("score", x);
        editor.commit();
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

    void wipeData_player1(){

        SharedPreferences namesettings = getSharedPreferences("score", 0);
        SharedPreferences.Editor name = namesettings.edit();
        name.putString("score", "0");
        name.commit();

        SharedPreferences agesettings = getSharedPreferences("score", 0);
        SharedPreferences.Editor age = agesettings.edit();
        age.putString("score", "0");
        age.commit();

        SharedPreferences sexsettings = getSharedPreferences("score", 0);
        SharedPreferences.Editor sex = sexsettings.edit();
        sex.putString("score", "0");
        sex.commit();

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

        SharedPreferences plaindata = getSharedPreferences("plaindata", 0);
        SharedPreferences.Editor p = plaindata.edit();
        p.putString("plaindata", "0");
        p.commit();

        SharedPreferences correctKeys = getSharedPreferences("correctKeys", 0);
        SharedPreferences.Editor cor = correctKeys.edit();
        cor.putString("correctKeys", "0");
        cor.commit();

        SharedPreferences zooKey = getSharedPreferences("key_zoo", 0);
        SharedPreferences.Editor zoo = zooKey.edit();
        zoo.putString("key_zoo", "0");
        zoo.commit();

        SharedPreferences rotondaKey = getSharedPreferences("key_rotonda", 0);
        SharedPreferences.Editor rot = rotondaKey.edit();
        rot.putString("key_rotonda", "0");
        rot.commit();

        SharedPreferences lefkosKey = getSharedPreferences("key_lefkos", 0);
        SharedPreferences.Editor lefkos = lefkosKey.edit();
        lefkos.putString("key_lefkos", "0");
        lefkos.commit();

        SharedPreferences kastraKey = getSharedPreferences("key_kastra", 0);
        SharedPreferences.Editor kastra = kastraKey.edit();
        kastra.putString("key_kastra", "0");
        kastra.commit();

        SharedPreferences dimitriosKey = getSharedPreferences("key_dimitrios", 0);
        SharedPreferences.Editor jim = dimitriosKey.edit();
        jim.putString("key_dimitrios", "0");
        jim.commit();

        SharedPreferences alexKey = getSharedPreferences("key_alexandros", 0);
        SharedPreferences.Editor alex = alexKey.edit();
        alex.putString("key_alexandros", "0");
        alex.commit();
    }


}
