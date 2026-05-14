package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestConsecutive_128Test {

    private final LongestConsecutive_128 test = new LongestConsecutive_128();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        assertEquals(9, test.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestConsecutive(new int[]{}));
        assertEquals(1, test.longestConsecutive(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.longestConsecutive(new int[]{1, 2, 3, 4, 5, 100, 200}));
    }

    @Test
    public void testDuplicates() {
        assertEquals(3, test.longestConsecutive(new int[]{1, 2, 0, 1}));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(5, test.longestConsecutive(new int[]{-2, -1, 0, 1, 2}));
    }

    @Test
    public void testNegativeOnly() {
        assertEquals(3, test.longestConsecutive(new int[]{-5, -3, -4, 10, 20}));
    }

    @Test
    public void testAllSameElements() {
        assertEquals(1, test.longestConsecutive(new int[]{5, 5, 5, 5}));
    }

    @Test
    public void testTwoSeparateSequences() {
        assertEquals(3, test.longestConsecutive(new int[]{10, 11, 12, 50, 51, 52}));
    }

    @Test
    public void testSingleNegative() {
        assertEquals(1, test.longestConsecutive(new int[]{-1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) {
            nums[i] = i;
        }
        assertEquals(10000, test.longestConsecutive(nums));
    }
}
