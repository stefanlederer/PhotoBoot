
/**
 * Created by stefanlederer on 21.05.17.
 */
public class FileAction {

    static String[][] selectedFiles = new String [10][2];
    static int i = 0;

    static String[][] getSelectedFiles(String name, String path) {

        selectedFiles[i][0] = name;
        selectedFiles[i][1] = path;

        i += 1;

        return selectedFiles;
    }

}
