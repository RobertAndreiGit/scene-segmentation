package utils;

import java.io.IOException;

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
}
