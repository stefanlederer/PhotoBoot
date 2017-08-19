import java.util.ArrayList;

/**
 * Created by stefanlederer on 29.05.17.
 */
public class Analysis {

    public static ArrayList<ArrayList<String>> analysePixels(String[][] pixels) {
        ArrayList<ArrayList<String>> analysedPixels = new ArrayList<ArrayList<String>>();
        ArrayList<String> headerSegment = new ArrayList<String>();
        ArrayList<String> compressedPixel = new ArrayList<String>();

        boolean equal = false;
        int counter = 0;
        for (int width = 0; width < pixels.length; width++) {
            for (int height = 0; height < pixels[width].length; height++) {
                String segment = pixels[width][height].substring(0, 1) + pixels[width][height].substring(2, 3) + pixels[width][height].substring(4, 5);
                String deviation = pixels[width][height].substring(1, 2) + pixels[width][height].substring(3, 4) + pixels[width][height].substring(5, 6);
                if (headerSegment.size() > 0) {
                    for (int similarity = 0; similarity < headerSegment.size(); similarity++) {
                        if (headerSegment.get(similarity).equals(segment + "g")) {
                            equal = true;
                        }
                    }
                }

                if (!equal) {
                    counter++;
                    headerSegment.add(segment + "g");
                } else {
                    equal = false;
                }
                String split;
                    if (height == 0) {
                        split = "h";
                    } else {
                        split = "";
                    }
                compressedPixel.add(split + counter + "g" + deviation + "gg");
            }
        }
        analysedPixels.add(headerSegment);
        analysedPixels.add(compressedPixel);

        return analysedPixels;
    }

    public static String[][] decompressData(String compressedData) {

        String headerSegment = compressedData.split("ggh")[0];
        String[] headerSegmentPixels = headerSegment.split("g");

        String[] lineSegment = compressedData.split("gh")[1].split("h");
        int rows = lineSegment.length;
        int columns = lineSegment[0].split("gg").length;

        String[][] pixels = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            String[] pixelLine = lineSegment[0].split("gg");
            for (int b = 1; b < columns; b++) {
                int headerNum = Integer.parseInt(pixelLine[b].split("g")[0]);
                String decompressedPixel = headerSegmentPixels[headerNum] + pixelLine[b].split("g")[1];
                pixels[i][b] = decompressedPixel;
            }
        }


        return pixels;
    }
}
