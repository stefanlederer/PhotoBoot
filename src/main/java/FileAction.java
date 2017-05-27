import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * Created by stefanlederer on 21.05.17.
 */
public class FileAction {

    private static String[][] selectedFilesRaw = new String [10][2];
    private static int i = 0;

    static String[][] getSelectedFiles(String name, String path) {

        selectedFilesRaw[i][0] = name;
        selectedFilesRaw[i][1] = path;

        i += 1;

        int a = 0;
        do {
            a++;
        } while(selectedFilesRaw[a][0] != null);

        String[][] selectedFiles = new String[a][2];

        for (int b = 0; b < a; b++) {
            selectedFiles[b][0] = selectedFilesRaw[b][0];
            selectedFiles[b][1] = selectedFilesRaw[b][1];
        }

        return selectedFiles;
    }

    public static void loadSelectedFiles(String[][] files) {
        for (String[] file : files) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(file[1]));

                int height = img.getHeight();
                int width = img.getWidth();
                String [][] pixels = new String [width][height];

                for (int w = 0; w < width; w++) {
                    for (int h = 0; h < height; h++) {
                        pixels[w][h] = Integer.toHexString(img.getRGB(w, h)).substring(0,6);
                        System.out.print(pixels[w][h] + " ");
                    }
                    System.out.println();
                }

            } catch (IOException e) {
                System.out.println("Cannot read File " + file[0]);
            }
        }
    }

}
