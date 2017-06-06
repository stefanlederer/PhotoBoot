import java.util.ArrayList;

/**
 * Created by stefanlederer on 29.05.17.
 */
public class Analysis {

    public static void analysePixels(String[][] pixels) {
        ArrayList<ArrayList<String>> valence = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 3; i++) {
            valence.add(new ArrayList<String>());
        }
        boolean equal = false;
        for (int width = 0; width < pixels.length; width++) {
            for (int height = 0; height < pixels[width].length; height++) {
                for (int priority = 0; priority < 3; priority++) {
                    if (valence.get(priority).size() > 0) {
                        for (int similarity = 0; similarity < valence.get(priority).size(); similarity++) {
                            if (valence.get(priority).get(similarity).equals(pixels[width][height].substring(0, priority + 1))) {
                                equal = true;
                            }
                        }
                    }

                    if (!equal) {
                        valence.get(priority).add(pixels[width][height].substring(0, priority + 1));
                    } else {
                        equal = false;
                    }
                }


            }
        }
        System.out.println(valence);

    }
}
