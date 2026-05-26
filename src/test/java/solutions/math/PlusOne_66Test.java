package solutions.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class PlusOne_66Test {

    private final PlusOne_66 test = new PlusOne_66();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1, 2, 4}, test.plusOne(new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{4, 3, 2, 2}, test.plusOne(new int[]{4, 3, 2, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{1, 0}, test.plusOne(new int[]{9}));
        assertArrayEquals(new int[]{1, 0, 0}, test.plusOne(new int[]{9, 9}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            test.plusOne(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9}));
    }

    @Test
    public void testSingleDigitNoCarry() {
        assertArrayEquals(new int[]{2}, test.plusOne(new int[]{1}));
    }

    @Test
    public void testZero() {
        assertArrayEquals(new int[]{1}, test.plusOne(new int[]{0}));
    }

    @Test
    public void testMiddleCarry() {
        assertArrayEquals(new int[]{2, 0, 0}, test.plusOne(new int[]{1, 9, 9}));
    }

    @Test
    public void testNoCarryLargeArray() {
        assertArrayEquals(new int[]{5, 6, 7, 9}, test.plusOne(new int[]{5, 6, 7, 8}));
    }

    @Test
    public void testLastDigitNine() {
        assertArrayEquals(new int[]{1, 3, 0}, test.plusOne(new int[]{1, 2, 9}));
    }

    @Test
    public void testAllZerosExceptFirst() {
        assertArrayEquals(new int[]{1, 0, 0, 1}, test.plusOne(new int[]{1, 0, 0, 0}));
    }

    @Test
    public void testGiantAllNines() {
        int[] input = new int[1000];
        for (int i = 0; i < 1000; i++) input[i] = 9;
        int[] expected = new int[1001];
        expected[0] = 1;
        assertArrayEquals(expected, test.plusOne(input));
    }

    @Test
    public void testAllNinesThreeDigits() {
        assertArrayEquals(new int[]{1, 0, 0, 0}, test.plusOne(new int[]{9, 9, 9}));
    }

    @Test
    public void testLargeNumberNoCarry() {
        assertArrayEquals(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 1},
            test.plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
    }

    @Test
    public void testNineNinesExpandsToTenDigits() {
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            test.plusOne(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9}));
    }

    @Test
    public void testLargeNumberWithTrailingZeros() {
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            test.plusOne(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void testSingleDigitSweep() {
        for (int d = 0; d <= 8; d++) {
            assertArrayEquals(new int[]{d + 1}, test.plusOne(new int[]{d}));
        }
    }

    @Test
    public void testHundredDigitsAllNines() {
        int[] input = new int[100];
        for (int i = 0; i < 100; i++) input[i] = 9;
        int[] result = test.plusOne(input);
        assertEquals(101, result.length);
        assertEquals(1, result[0]);
        for (int i = 1; i < 101; i++) {
            assertEquals(0, result[i]);
        }
    }

    @Test
    public void testPropertyBigIntegerReference() {
        int[][] cases = {
            {1, 2, 3}, {9, 9, 9}, {0}, {9}, {4, 9, 9}, {1, 0, 0, 0}
        };
        for (int[] digits : cases) {
            StringBuilder sb = new StringBuilder();
            for (int d : digits) sb.append(d);
            BigInteger original = new BigInteger(sb.toString());
            BigInteger expected = original.add(BigInteger.ONE);

            int[] result = test.plusOne(digits.clone());
            StringBuilder resultStr = new StringBuilder();
            for (int d : result) resultStr.append(d);
            assertEquals(expected, new BigInteger(resultStr.toString()));
        }
    }
}
