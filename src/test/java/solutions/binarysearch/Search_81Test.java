package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Search_81Test {

    private final Search_81 test = new Search_81();

    @Test
    public void testHappyCases() {
        assertTrue(test.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        assertTrue(test.search(new int[]{1, 3, 1, 1, 1}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        assertFalse(test.search(new int[]{}, 1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, 2));
        assertFalse(test.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 2));
    }

    @Test
    public void testSingleElementFound() {
        assertTrue(test.search(new int[]{5}, 5));
    }

    @Test
    public void testSingleElementNotFound() {
        assertFalse(test.search(new int[]{5}, 4));
    }

    @Test
    public void testAllElementsSameAndPresent() {
        assertTrue(test.search(new int[]{7, 7, 7, 7}, 7));
    }

    @Test
    public void testAllElementsSameAndAbsent() {
        assertFalse(test.search(new int[]{7, 7, 7, 7}, 8));
    }

    @Test
    public void testNotRotatedWithDuplicates() {
        assertTrue(test.search(new int[]{1, 1, 2, 2, 3, 3}, 2));
    }

    @Test
    public void testPivotNearEnd() {
        assertTrue(test.search(new int[]{3, 4, 5, 1, 2, 2, 2}, 1));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[3000];
        for (int i = 0; i < 2500; i++) {
            nums[i] = 2;
        }
        for (int i = 2500; i < 3000; i++) {
            nums[i] = 3;
        }
        assertTrue(test.search(nums, 3));
    }
@Test
    public void testRandomRotatedMultisetsAgainstLinearSearch() {
        java.util.Random random = new java.util.Random(812026L);
        for (int trial = 0; trial < 200; trial++) {
            int[] sorted = new int[1 + random.nextInt(80)];
            for (int i = 0; i < sorted.length; i++) sorted[i] = random.nextInt(21) - 10;
            java.util.Arrays.sort(sorted);
            int pivot = random.nextInt(sorted.length);
            int[] values = new int[sorted.length];
            for (int i = 0; i < values.length; i++) values[i] = sorted[(i + pivot) % sorted.length];
            for (int target = -11; target <= 11; target++) {
                boolean expected = false;
                for (int value : values) if (value == target) expected = true;
                org.junit.jupiter.api.Assertions.assertEquals(expected, test.search(values, target));
            }
        }
    }

    @Test
    public void testUniqueMinimumAtEveryPositionAmongDuplicates() {
        for (int position = 0; position < 257; position++) {
            int[] values = new int[257];
            java.util.Arrays.fill(values, 9);
            values[position] = -7;
            assertTrue(test.search(values, -7));
            assertFalse(test.search(values, -6));
        }
    }

    @Test
    public void testGiantDuplicateArrayWithSingleMaximum() {
        int[] values = new int[100000];
        java.util.Arrays.fill(values, -1);
        values[80001] = Integer.MAX_VALUE;
        assertTrue(test.search(values, Integer.MAX_VALUE));
        assertFalse(test.search(values, Integer.MIN_VALUE));
    }
}
