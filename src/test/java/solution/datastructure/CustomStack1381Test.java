package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomStack1381Test {

    @Test
    public void testHappyCases() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(1); stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(2); stack.push(3); stack.push(4);
        stack.increment(5, 100);
        stack.increment(2, 100);
        assertEquals(103, stack.pop());
        assertEquals(202, stack.pop());
        assertEquals(201, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        CustomStack_1381 stack = new CustomStack_1381(1);
        assertEquals(-1, stack.pop());
        stack.push(1); stack.push(2);
        assertEquals(1, stack.pop());
    }

    @Test
    public void testLargeCase() {
        CustomStack_1381 stack = new CustomStack_1381(5);
        for (int i = 1; i <= 5; i++) stack.push(i);
        stack.increment(3, 10);
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(13, stack.pop());
    }
}
