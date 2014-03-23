package gr.siokas.plain.app;

import java.util.Random;

/**
 * Created by apostolossiokas on 3/21/14.
 */
public class Monument {

    String names = "";

    public Monument(String names) {
        this.names = names;
    }

    public String getRandomMonument() {
        String[] monument = names.split(",");
        int number_of_monuments = (monument.length) - 1;

        return monument[getRandNum(0, number_of_monuments)];
    }

    public static int getRandNum(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
