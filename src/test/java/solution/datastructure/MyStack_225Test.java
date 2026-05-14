package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyStack_225Test {

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

    @Test
    public void testTopDoesNotRemove() {
        MyStack_225 stack = new MyStack_225();
        stack.push(42);
        assertEquals(42, stack.top());
        assertEquals(42, stack.top());
        assertFalse(stack.empty());
    }

    @Test
    public void testSingleElement() {
        MyStack_225 stack = new MyStack_225();
        stack.push(99);
        assertEquals(99, stack.top());
        assertEquals(99, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testPushAfterPop() {
        MyStack_225 stack = new MyStack_225();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertEquals(2, stack.top());
        assertEquals(2, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testLIFOOrder() {
        MyStack_225 stack = new MyStack_225();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void testInterleavedPushPop() {
        MyStack_225 stack = new MyStack_225();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testMultipleTopCalls() {
        MyStack_225 stack = new MyStack_225();
        stack.push(5);
        stack.push(10);
        assertEquals(10, stack.top());
        stack.pop();
        assertEquals(5, stack.top());
    }

    @Test
    public void testGiantCase() {
        MyStack_225 stack = new MyStack_225();
        for (int i = 0; i < 1000; i++) stack.push(i);
        for (int i = 999; i >= 0; i--) assertEquals(i, stack.pop());
        assertTrue(stack.empty());
    }
}
