package utils;

import java.io.File;

public class ImageCleaner {
    public static void cleanFolder(String path){
        File index = new File(path);
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            currentFile.delete();
        }
    }
}
