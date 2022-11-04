package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/04 23:09
 * Created with IntelliJ IDEA
 */

public class MyHashMap706Test {

    private MyHashMap_706 test;

    @BeforeEach
    public void setUp() {
        test = new MyHashMap_706();
    }

    @Test
    public void test() {
        test.put(1, 1);
        test.put(2, 2);
        assertEquals(1, test.get(1));
        assertEquals(-1, test.get(111));
        assertEquals(-1, test.get(31));
        assertEquals(2, test.get(2));
        assertEquals(-1, test.get(Integer.MAX_VALUE));
        assertEquals(-1, test.get(Integer.MIN_VALUE));
        assertEquals(2, test.get(2));
        test.remove(2);
        assertEquals(-1, test.get(2));
    }
}