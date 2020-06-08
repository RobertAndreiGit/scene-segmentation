package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FolderCleanerTest {

    @Before
    public void setUp() throws Exception {
        new File("E:/testing").mkdirs();
        new File("E:/testing/1.jpg").createNewFile();
        new File("E:/testing/2.jpg").createNewFile();
        new File("E:/testing/3.jpg").createNewFile();
        new File("E:/testing/4.jpg").createNewFile();
        new File("E:/testing/5.jpg").createNewFile();

        File Files = new File("E:/testing");
        assertEquals(5, Files.list().length);
    }

    @After
    public void tearDown() {
        System.out.println("FolderCleaner tests ran successfully!");
    }

    @Test
    public void cleanFolder() {
        FolderCleaner.cleanFolder("E:/testing");

        File Files = new File("E:/testing");
        assertEquals(0, Files.list().length);
    }
}