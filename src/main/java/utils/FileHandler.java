package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    /**
     * static function used to take care of writing the a list of strings to a given file
     * This function will overwrite the data existing in the file.
     * @param filepath - String, the path to a text file
     * @param toWrite - List of Strings, made up of the strings we want to write in the file
     */
    public static void writeFile(String filepath, ArrayList<String> toWrite) {
        try {
            File file = new File(filepath);
            FileWriter fooWriter = new FileWriter(file, false);
            for (int i = 0; i < toWrite.size(); i++) {
                fooWriter.write(toWrite.get(i) + '\n');
            }
            fooWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
