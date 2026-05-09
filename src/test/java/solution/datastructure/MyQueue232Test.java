package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyQueue232Test {

    @Test
    public void testHappyCases() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1); q.push(2);
        assertEquals(1, q.peek());
        assertEquals(1, q.pop());
        assertFalse(q.empty());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyQueue_232 q = new MyQueue_232();
        assertTrue(q.empty());
        q.push(1);
        assertFalse(q.empty());
        assertEquals(1, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testLargeCase() {
        MyQueue_232 q = new MyQueue_232();
        for (int i = 1; i <= 5; i++) q.push(i);
        for (int i = 1; i <= 5; i++) assertEquals(i, q.pop());
        assertTrue(q.empty());
    }
}
