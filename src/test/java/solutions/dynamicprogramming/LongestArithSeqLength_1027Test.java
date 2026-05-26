package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestArithSeqLength_1027Test {

    private final LongestArithSeqLength_1027 test = new LongestArithSeqLength_1027();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.dp(new int[]{3, 6, 9, 12}));
        assertEquals(3, test.dp(new int[]{9, 4, 7, 2, 10}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.dp(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.dp(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));
    }

    @Test
    public void testHappyAllSame() {
        // All elements same -> diff=0, length = n
        assertEquals(5, test.dp(new int[]{7, 7, 7, 7, 7}));
    }

    @Test
    public void testHappyDecreasing() {
        // Decreasing sequence with diff -2
        assertEquals(5, test.dp(new int[]{10, 8, 6, 4, 2}));
    }

    @Test
    public void testHappyMixed() {
        assertEquals(4, test.dp(new int[]{20, 1, 15, 3, 10, 5, 8}));
    }

    @Test
    public void testNegativeValues() {
        // Negative numbers
        assertEquals(5, test.dp(new int[]{-3, -1, 1, 3, 5}));
    }

    @Test
    public void testEdgeTwoElements() {
        assertEquals(2, test.dp(new int[]{100, 200}));
    }

    @Test
    public void testEdgeDuplicates() {
        assertEquals(4, test.dp(new int[]{1, 2, 3, 2, 3, 4}));
    }

    @Test
    public void testGiantCase() {
        // Array of 200 elements: 0,1,2,...,199 -> longest arith subseq = 200
        int[] arr = new int[200];
        for (int i = 0; i < 200; i++) arr[i] = i;
        assertEquals(200, test.dp(arr));
    }
}
