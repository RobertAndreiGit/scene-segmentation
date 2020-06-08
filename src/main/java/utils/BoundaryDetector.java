package utils;

import org.bytedeco.opencv.presets.opencv_core;

import java.util.ArrayList;
import java.util.Hashtable;

public class BoundaryDetector {
    public static void getNumberOfScenes(ArrayList<Hashtable<String, Integer>> shotsData){
        System.out.println("#" + shotsData.size() + " shots detected in the given video.");
        System.out.println("Analyzing scene count.");

        int result = 0;
        int from = 0;
        int size = 4;
        int x = 0;

        for(int i = from + size; i < shotsData.size(); i++){
            if(commonElems(shotsData.get(i), shotsData.get(from)) || commonElems(shotsData.get(i), shotsData.get(from+1))
                    || commonElems(shotsData.get(i), shotsData.get(from+2))){
                from += 1;
            }
            else {
                x++;
                from = i;
                i = from + size;
            }
        }

        System.out.println("#" + (x+1) + " scenes detected.");
        return;
    }

    private static boolean commonElems(Hashtable<String, Integer> toAdd, Hashtable<String, Integer> initial){
        double combined = 0;
        int alone = 0;

        for(String x : initial.keySet()){
            alone += initial.get(x);

            if(toAdd.containsKey(x)){
                combined += Math.min(initial.get(x), toAdd.get(x));
            }
        }

        combined = combined / alone;

        return combined >= 0.9;
    }
}
