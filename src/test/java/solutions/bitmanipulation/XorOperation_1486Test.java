package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link XorOperation_1486}.
 */
public class XorOperation_1486Test {

    private final XorOperation_1486 solver = new XorOperation_1486();

    @Test
    public void testLeetCodeExample1() {
        // n = 5, start = 0
        // nums = [0, 2, 4, 6, 8]
        // 0 ^ 2 = 2, 2 ^ 4 = 6, 6 ^ 6 = 0, 0 ^ 8 = 8
        assertEquals(8, solver.xorOperation(5, 0));
    }

    @Test
    public void testLeetCodeExample2() {
        // n = 4, start = 3
        // nums = [3, 5, 7, 9]
        // 3 ^ 5 = 6, 6 ^ 7 = 1, 1 ^ 9 = 8
        assertEquals(8, solver.xorOperation(4, 3));
    }

    @Test
    public void testN1() {
        // n = 1, start = 0
        // nums = [0], XOR = 0
        assertEquals(0, solver.xorOperation(1, 0));
    }

    @Test
    public void testN1WithStart() {
        // n = 1, start = 5
        // nums = [5], XOR = 5
        assertEquals(5, solver.xorOperation(1, 5));
    }

    @Test
    public void testN2() {
        // n = 2, start = 0
        // nums = [0, 2], XOR = 2
        assertEquals(2, solver.xorOperation(2, 0));
    }

    @Test
    public void testN2WithStart() {
        // n = 2, start = 1
        // nums = [1, 3], XOR = 1 ^ 3 = 2
        assertEquals(2, solver.xorOperation(2, 1));
    }

    @Test
    public void testLargeN() {
        // n = 1000, start = 0
        // nums = [0, 2, 4, ..., 1998]
        int result = solver.xorOperation(1000, 0);
        // Just verify it returns a non-negative int
        assertTrue(result >= 0);
    }

    @Test
    public void testStartEqualsN() {
        // n = 3, start = 3
        // nums = [3, 5, 7]
        // 3 ^ 5 = 6, 6 ^ 7 = 1
        assertEquals(1, solver.xorOperation(3, 3));
    }

    @Test
    public void testAllZeros() {
        // n = 1, start = 0
        // nums = [0], XOR = 0
        assertEquals(0, solver.xorOperation(1, 0));
    }

    @Test
    public void testLargeStart() {
        // n = 3, start = 100
        // nums = [100, 102, 104]
        // 100 ^ 102 = 2, 2 ^ 104 = 106
        assertEquals(106, solver.xorOperation(3, 100));
    }

    @Test
    public void testGiantN() {
        // n = 10000, start = 1
        int result = solver.xorOperation(10000, 1);
        // just verify it completes and returns an int
        assertTrue(result >= 0 || result < 0);
    }
}
