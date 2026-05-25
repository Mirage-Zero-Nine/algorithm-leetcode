package solution.datastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    @Test
    public void testPushThenTopEqualsPushed() {
        minStack.push(99);
        assertEquals(99, minStack.top());
        minStack.push(-7);
        assertEquals(-7, minStack.top());
    }

    @Test
    public void testPushDuplicatesPopOneGetMinStillCorrect() {
        minStack.push(1);
        minStack.push(1);
        minStack.push(1);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
    }

    @Test
    public void testMixedSequenceGetMin() {
        minStack.push(3);
        minStack.push(2);
        minStack.push(5);
        minStack.push(1);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(2, minStack.getMin());
        minStack.pop();
        assertEquals(2, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
    }

    @Test
    public void testNegativeValues() {
        minStack.push(-3);
        minStack.push(-1);
        minStack.push(-5);
        assertEquals(-5, minStack.getMin());
        assertEquals(-5, minStack.top());
        minStack.pop();
        assertEquals(-3, minStack.getMin());
        assertEquals(-1, minStack.top());
    }

    @Test
    public void testIntMaxAndIntMinValues() {
        minStack.push(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, minStack.getMin());
        minStack.push(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        assertEquals(Integer.MIN_VALUE, minStack.top());
        minStack.pop();
        assertEquals(Integer.MAX_VALUE, minStack.getMin());
    }

    @Test
    public void testTopReturnsLastPushedNotLastPopped() {
        minStack.push(10);
        minStack.push(20);
        minStack.push(30);
        assertEquals(30, minStack.top());
        minStack.pop();
        assertEquals(20, minStack.top());
        minStack.pop();
        assertEquals(10, minStack.top());
    }

    @Test
    public void testManyPushesThenManyPops() {
        for (int i = 0; i < 500; i++) {
            minStack.push(i);
        }
        assertEquals(0, minStack.getMin());
        assertEquals(499, minStack.top());
        for (int i = 0; i < 500; i++) {
            minStack.pop();
        }
        assertEquals(-1, minStack.getMin());
        assertEquals(-1, minStack.top());
    }

    @Test
    public void testStress1000RandomOpsSeed42() {
        Random rand = new Random(42L);
        List<Integer> reference = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (reference.isEmpty() || rand.nextBoolean()) {
                int val = rand.nextInt(20001) - 10000;
                minStack.push(val);
                reference.add(val);
            } else {
                minStack.pop();
                reference.removeLast();
            }
            if (!reference.isEmpty()) {
                int expectedMin = Collections.min(reference);
                assertEquals(expectedMin, minStack.getMin(), "Mismatch at op " + i);
                assertEquals(reference.getLast(), minStack.top(), "Top mismatch at op " + i);
            }
        }
    }
}