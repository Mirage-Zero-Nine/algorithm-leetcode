package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
}
