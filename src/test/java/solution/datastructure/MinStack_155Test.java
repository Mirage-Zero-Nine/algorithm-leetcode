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
}