package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomStack_1381Test {

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

    @Test
    public void testIncrementOnEmptyStackDoesNothing() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.increment(2, 10);
        assertEquals(-1, stack.pop());
    }

    @Test
    public void testIncrementWithZeroKDoesNothing() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(10);
        stack.increment(0, 100);
        assertEquals(10, stack.pop());
    }

    @Test
    public void testIncrementWithNegativeKDoesNothing() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(10);
        stack.increment(-2, 100);
        assertEquals(10, stack.pop());
    }

    @Test
    public void testIncrementTopOnly() {
        CustomStack_1381 stack = new CustomStack_1381(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.increment(1, 50);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(51, stack.pop());
    }

    @Test
    public void testNegativeIncrementValue() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(5);
        stack.push(6);
        stack.increment(2, -2);
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
    }

    @Test
    public void testPushBeyondCapacityIgnored() {
        CustomStack_1381 stack = new CustomStack_1381(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    public void testGiantSequenceOperations() {
        CustomStack_1381 stack = new CustomStack_1381(1000);
        for (int i = 1; i <= 1000; i++) {
            stack.push(i);
        }
        stack.increment(1000, 7);
        stack.increment(500, 3);
        for (int i = 1000; i >= 501; i--) {
            assertEquals(i + 7, stack.pop());
        }
        for (int i = 500; i >= 1; i--) {
            assertEquals(i + 10, stack.pop());
        }
        assertEquals(-1, stack.pop());
    }
}
