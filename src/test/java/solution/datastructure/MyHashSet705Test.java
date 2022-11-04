package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/05 23:54
 * Created with IntelliJ IDEA
 */

public class MyHashSet705Test {

    private MyHashSet_705 test;

    @BeforeEach
    public void setUp() {
        test = new MyHashSet_705();
    }

    @Test
    public void test() {
        test.add(1);      // set = [1]
        test.add(2);      // set = [1, 2]

        assertTrue(test.contains(1));
        assertFalse(test.contains(Integer.MAX_VALUE));

        test.add(2);      // set = [1, 2]
        assertTrue(test.contains(2));
        test.remove(2);   // set = [1]
        assertFalse(test.contains(2));
    }
}