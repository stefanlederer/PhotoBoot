import java.util.ArrayList;

/**
 * Created by stefanlederer on 29.05.17.
 */
public class Analysis {

    public static void analysePixels(String[][] pixels) {
        ArrayList<String> headerSegment = new ArrayList<String>();

        boolean equal = false;
        for (int width = 0; width < pixels.length; width++) {
            for (int height = 0; height < pixels[width].length; height++) {
                String segment = pixels[width][height].substring(0, 1) + pixels[width][height].substring(2, 3) + pixels[width][height].substring(4, 5);
                String deviation = pixels[width][height].substring(1, 2) + pixels[width][height].substring(3, 4) + pixels[width][height].substring(5, 6);
                if (headerSegment.size() > 0) {
                    for (int similarity = 0; similarity < headerSegment.size(); similarity++) {
                        if (headerSegment.get(similarity).equals(segment)) {
                            equal = true;
                        }
                    }
                }

                if (!equal) {
                    headerSegment.add(segment);
                } else {
                    equal = false;
                }


            }
        }
        System.out.println(headerSegment);

    }
}
