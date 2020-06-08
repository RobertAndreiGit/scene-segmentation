package utils;

import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class PythonRunnerTest {

    @Test
    public void getOutputContent() {
    }

    @Test
    public void combine() {
        Hashtable<String, Integer> t1 = new Hashtable<>();
        t1.put("dog", 5);
        t1.put("cat", 3);
        t1.put("human", 1);

        Hashtable<String, Integer> t2 = new Hashtable<>();
        t2.put("dog", 3);
        t2.put("cat", 5);
        t2.put("carpet", 2);


        Hashtable<String, Integer> result = PythonRunner.combine(t1, t2);

        assertEquals(4, result.size());

        assertTrue(result.containsKey("dog"));
        assertEquals(5, result.get("dog").intValue());

        assertTrue(result.containsKey("cat"));
        assertEquals(5, result.get("cat").intValue());

        assertTrue(result.containsKey("human"));
        assertEquals(1, result.get("human").intValue());

        assertTrue(result.containsKey("carpet"));
        assertEquals(2, result.get("carpet").intValue());
    }
}