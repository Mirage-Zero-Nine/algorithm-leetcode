package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

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

    @Test
    public void testEmptyOnNewStack() {
        MyStack_225 stack = new MyStack_225();
        assertTrue(stack.empty());
    }

    @Test
    public void testPushThenTopReturnsLastPushedLIFO() {
        MyStack_225 stack = new MyStack_225();
        stack.push(100);
        stack.push(200);
        stack.push(300);
        assertEquals(300, stack.top());
    }

    @Test
    public void testPushThenPopReturnsLastPushed() {
        MyStack_225 stack = new MyStack_225();
        stack.push(7);
        stack.push(8);
        assertEquals(8, stack.pop());
    }

    @Test
    public void testTopDoesNotModifyStack() {
        MyStack_225 stack = new MyStack_225();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.top());
        assertEquals(3, stack.top());
        assertEquals(3, stack.top());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void testMultiplePushesThenMultiplePopsLIFO() {
        MyStack_225 stack = new MyStack_225();
        for (int i = 1; i <= 10; i++) stack.push(i * 10);
        for (int i = 10; i >= 1; i--) assertEquals(i * 10, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testMixedPushPopTopSequence() {
        MyStack_225 stack = new MyStack_225();
        stack.push(5);
        assertEquals(5, stack.top());
        stack.push(10);
        assertEquals(10, stack.top());
        assertEquals(10, stack.pop());
        assertEquals(5, stack.top());
        stack.push(15);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(15, stack.pop());
        assertEquals(5, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testEmptyAfterAllPopped() {
        MyStack_225 stack = new MyStack_225();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.empty());
    }

    @Test
    public void testNegativeValues() {
        MyStack_225 stack = new MyStack_225();
        stack.push(-1);
        stack.push(-100);
        stack.push(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, stack.pop());
        assertEquals(-100, stack.pop());
        assertEquals(-1, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testLargeStress1000OpsRandomCrossCheck() {
        MyStack_225 stack = new MyStack_225();
        Deque<Integer> ref = new ArrayDeque<>();
        Random rng = new Random(42L);

        for (int i = 0; i < 1000; i++) {
            int op = ref.isEmpty() ? 0 : rng.nextInt(4);
            switch (op) {
                case 0 -> {
                    int val = rng.nextInt(2001) - 1000;
                    stack.push(val);
                    ref.push(val);
                }
                case 1 -> assertEquals(ref.pop(), stack.pop(), "pop mismatch at op " + i);
                case 2 -> assertEquals(ref.peek(), stack.top(), "top mismatch at op " + i);
                case 3 -> assertEquals(ref.isEmpty(), stack.empty(), "empty mismatch at op " + i);
            }
        }
    }
}
