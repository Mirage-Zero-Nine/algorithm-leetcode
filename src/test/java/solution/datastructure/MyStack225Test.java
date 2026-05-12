package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyStack225Test {

    @Test
    public void testHappyCases() {
        MyStack_225 stack = new MyStack_225();
        stack.push(1); stack.push(2);
        assertEquals(2, stack.top());
        assertEquals(2, stack.pop());
        assertFalse(stack.empty());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyStack_225 stack = new MyStack_225();
        assertTrue(stack.empty());
        stack.push(1);
        assertFalse(stack.empty());
        assertEquals(1, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testLargeCase() {
        MyStack_225 stack = new MyStack_225();
        for (int i = 1; i <= 5; i++) stack.push(i);
        for (int i = 5; i >= 1; i--) assertEquals(i, stack.pop());
        assertTrue(stack.empty());
    }
}
