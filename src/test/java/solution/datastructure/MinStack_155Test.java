package solution.datastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinStack_155Test {
    private MinStack_155 minStack;

    @BeforeEach
    void setUp() {
        minStack = new MinStack_155();
    }

    @Test
    public void valid() {
        minStack.push(10);
        minStack.push(20);
        minStack.push(30);
        minStack.push(40);
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(-1, minStack.getMin());
    }

    @Test
    public void valid1() {
        minStack.push(40);
        minStack.push(30);
        minStack.push(20);
        minStack.push(10);
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(20, minStack.getMin());
        minStack.pop();
        assertEquals(30, minStack.getMin());
        minStack.pop();
        assertEquals(40, minStack.getMin());
        minStack.pop();
        assertEquals(-1, minStack.getMin());
    }

    @Test
    public void valid2() {
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        assertEquals(0, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.getMin());
    }

    @Test
    public void valid3() {
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        assertEquals(-1024, minStack.getMin());
        minStack.pop();
        assertEquals(-1024, minStack.getMin());
        minStack.pop();
        assertEquals(512, minStack.getMin());
    }

    @Test
    public void testEmptyStack() {
        assertEquals(-1, minStack.top());
        assertEquals(-1, minStack.getMin());
    }

    @Test
    public void testPopOnEmpty() {
        minStack.pop(); // should not throw
        assertEquals(-1, minStack.top());
        assertEquals(-1, minStack.getMin());
    }

    @Test
    public void testSingleElement() {
        minStack.push(42);
        assertEquals(42, minStack.top());
        assertEquals(42, minStack.getMin());
        minStack.pop();
        assertEquals(-1, minStack.top());
    }

    @Test
    public void testAllSameValues() {
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        assertEquals(5, minStack.getMin());
        minStack.pop();
        assertEquals(5, minStack.getMin());
        minStack.pop();
        assertEquals(5, minStack.getMin());
    }

    @Test
    public void testMinUpdatesCorrectly() {
        minStack.push(3);
        minStack.push(1);
        minStack.push(2);
        assertEquals(1, minStack.getMin());
        assertEquals(2, minStack.top());
        minStack.pop();
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
    }

    @Test
    public void testGiantCase() {
        for (int i = 1000; i >= 1; i--) {
            minStack.push(i);
            assertEquals(i, minStack.getMin());
        }
        for (int i = 1; i <= 1000; i++) {
            assertEquals(i, minStack.getMin());
            minStack.pop();
        }
        assertEquals(-1, minStack.getMin());
    }
}