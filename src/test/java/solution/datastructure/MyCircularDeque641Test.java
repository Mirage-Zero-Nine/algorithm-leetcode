package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/15 18:57
 * Created with IntelliJ IDEA
 */

public class MyCircularDeque641Test {

    @Test
    public void test() {
        MyCircularDeque_641 test = new MyCircularDeque_641(4);
        assertTrue(test.insertFront(9));
        assertTrue(test.deleteLast());
        assertEquals(-1, test.getRear());
        assertEquals(-1, test.getFront());
        assertEquals(-1, test.getFront());
        assertFalse(test.deleteFront());
        assertTrue(test.insertFront(6));
        assertTrue(test.insertLast(5));
        assertTrue(test.insertFront(9));
        assertTrue(test.insertFront(6));
    }
}