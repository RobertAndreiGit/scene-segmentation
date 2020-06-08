package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileHandlerTest {

    @Before
    public void setUp() throws Exception {
        File file = new File("test.txt");
        file.createNewFile();
        assertTrue(file.exists());
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("test.txt");
        file.delete();
        assertFalse(file.exists());
        System.out.println("FileHandler tests ran successfully!");
    }

    @Test
    public void writeFile() throws IOException {
        File file = new File("test.txt");
        assert (file.exists());

        ArrayList<String> strings = new ArrayList<>();
        strings.add("dog");
        strings.add("dog");
        strings.add("cat");

        FileHandler.writeFile("test.txt", strings);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int index = 0;
        while ((st = br.readLine()) != null) {
            assertEquals(st, strings.get(index));
            index += 1;
        }
        assertEquals(index, strings.size());
        br.close();
    }
}