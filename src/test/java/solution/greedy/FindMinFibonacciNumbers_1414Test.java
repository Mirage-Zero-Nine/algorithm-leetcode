package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinFibonacciNumbers_1414Test {
    private final FindMinFibonacciNumbers_1414 solver = new FindMinFibonacciNumbers_1414();

    @Test public void testSeven() {
        // 7 = 5 + 2, so 2 Fibonacci numbers
        assertEquals(2, solver.findMinFibonacciNumbers(7));
    }

    @Test public void testTen() {
        // 10 = 8 + 2, so 2
        assertEquals(2, solver.findMinFibonacciNumbers(10));
    }

    @Test public void testNineteen() {
        // 19 = 13 + 5 + 1, so 3
        assertEquals(3, solver.findMinFibonacciNumbers(19));
    }

    @Test public void testOne() {
        assertEquals(1, solver.findMinFibonacciNumbers(1));
    }

    @Test public void testZero() {
        assertEquals(0, solver.findMinFibonacciNumbers(0));
    }
}
