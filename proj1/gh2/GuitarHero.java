package gh2;


import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class GuitarHero {
    public static ArrayList<GuitarString> strings = new ArrayList<>();
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static void main(String[] args) {
        for (int i = 36; i >= 0; i--) {
            strings.add(new GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0)));
        }
        while (true) {

            /* check if the user has typed a key; if so, process it */

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int i = keyboard.indexOf(key);
                if (i >= 0 && i < 37) {
                    GuitarString stringA = strings.get(i);
                    stringA.pluck();
                }
            }
            double sample = 0;
            /* compute the superposition of samples */
            for (GuitarString string : strings) {
                sample += string.sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}
