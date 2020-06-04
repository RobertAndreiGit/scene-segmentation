package utils;

import org.bytedeco.opencv.presets.opencv_core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

public class PythonRunner {
    public static void runDarknet(){
        try {
            final String command = "cmd /c start /wait py grab.py";
            final Runtime r = Runtime.getRuntime();
            final Process p;
            p = r.exec(command);
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Hashtable<String, Integer> getOutputContent(String file){
        Hashtable<String, Integer> result = new Hashtable<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int ok = 0;
            Hashtable<String, Integer> current = new Hashtable<>();
            while ((line = br.readLine()) != null) {
                if(ok == 0 && line.startsWith("Enter Image Path")){
                    ok = 1;
                }
                if(ok == 1 && !line.startsWith("Enter Image Path")){
                    String key = line.substring(0,line.indexOf(':'));
                    if(current.containsKey(key)){
                        current.put(key, current.get(key) + 1);
                    } else {
                        current.put(key, 1);
                    }
                } else if(ok == 1 && line.startsWith("Enter Image Path")) {
                    combine(result, current);
                    current.clear();
                }
            }

            for(String key : result.keySet()) {
                Integer positions = result.get(key);
                //System.out.println(positions + "x " + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Hashtable<String, Integer> combine(Hashtable<String, Integer> result, Hashtable<String, Integer> current) {
        for(String key : current.keySet()) {
            Integer value = current.get(key);
            if(!result.containsKey(key) || result.get(key) < value){
                result.put(key, value);
            }
        }

        return result;
    }
}
