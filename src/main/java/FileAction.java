
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by stefanlederer on 21.05.17.
 */
public class FileAction {

    private static String[][] selectedFilesRaw = new String[10][2];
    private static int i = 0;

    static String[][] getSelectedFiles(String name, String path) {

        selectedFilesRaw[i][0] = name;
        selectedFilesRaw[i][1] = path;

        i += 1;

        int a = 0;
        do {
            a++;
        } while (selectedFilesRaw[a][0] != null);

        String[][] selectedFiles = new String[a][2];

        for (int b = 0; b < a; b++) {
            selectedFiles[b][0] = selectedFilesRaw[b][0];
            selectedFiles[b][1] = selectedFilesRaw[b][1];
        }

        return selectedFiles;
    }

    public static String[][] loadSelectedFile(String[] file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(file[1]));

            int height = img.getHeight();
            int width = img.getWidth();
            String[][] pixels = new String[width][height];

            for (int w = 0; w < width; w++) {
                for (int h = 0; h < height; h++) {
                    pixels[w][h] = Integer.toHexString(img.getRGB(w, h)).substring(2, 8);
                }
            }
            return pixels;

        } catch (IOException e) {
            System.out.println("Cannot read File " + file[0]);
        }
        return null;
    }

    public static void createCompressedFile(ArrayList<ArrayList<String>> compressedPixels, String filename) {

        byte[] enjambement = DatatypeConverter.parseBase64Binary(";");
        try {
            File compressedFile = new File("/home/stefanlederer/Dokumente/" + filename + ".bin");
            FileOutputStream filewriter = new FileOutputStream(compressedFile, true);

            for (ArrayList<String> fileareas : compressedPixels) {
                for (String pixelsCode : fileareas) {
                    byte[] b = DatatypeConverter.parseBase64Binary(pixelsCode + ",");
                    filewriter.write(b);
                }
                filewriter.write(enjambement);
            }
            filewriter.flush();
            filewriter.close();

        } catch (Exception e) {
            System.out.println("Cannot create file" + e);
        }
    }

    public static String loadCompressedFile(String[] file) {

        try {

            File f = new File(file[1]);
            byte[] bytes = new byte[(int)f.length()];
            InputStream is = new FileInputStream(f);

            int offset = 0;
            int numRead;
            while (offset < bytes.length
                    && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
            is.close();

            return DatatypeConverter.printBase64Binary(bytes);

        } catch (Exception e) {
            System.out.println("Cannot read File " + file[0]);
        }
        return null;
    }

}
