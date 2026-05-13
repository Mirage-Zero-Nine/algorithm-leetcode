package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrailingZeroes_172Test {

    private final TrailingZeroes_172 test = new TrailingZeroes_172();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.trailingZeroes(5));
        assertEquals(2, test.trailingZeroes(10));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.trailingZeroes(0));
        assertEquals(0, test.trailingZeroes(4));
    }

    @Test
    public void testLargeCase() {
        assertEquals(24, test.trailingZeroes(100));
        assertEquals(249, test.trailingZeroes(1000));
    }

    @Test
    public void testN3() {
        assertEquals(0, test.trailingZeroes(3));
    }

    @Test
    public void testN25() {
        // 25 = 5*5, so extra factor of 5
        assertEquals(6, test.trailingZeroes(25));
    }

    @Test
    public void testN30() {
        assertEquals(7, test.trailingZeroes(30));
    }

    @Test
    public void testN125() {
        // 125 = 5^3, contributes 3 fives
        assertEquals(31, test.trailingZeroes(125));
    }

    @Test
    public void testNegativeInput() {
        assertEquals(0, test.trailingZeroes(-1));
    }

    @Test
    public void testGiantN() {
        // 10000! trailing zeroes = 10000/5 + 10000/25 + 10000/125 + 10000/625 + 10000/3125 = 2000+400+80+16+3 = 2499
        assertEquals(2499, test.trailingZeroes(10000));
    }

    @Test
    public void testIterativeMethod() {
        assertEquals(1, TrailingZeroes_172.iterative(5));
        assertEquals(24, TrailingZeroes_172.iterative(100));
        assertEquals(249, TrailingZeroes_172.iterative(1000));
    }
}
