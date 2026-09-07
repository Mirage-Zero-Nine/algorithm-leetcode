package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SearchInsert_35Test {

    private final SearchInsert_35 test = new SearchInsert_35();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.searchInsert(new int[]{1, 3, 5, 6}, 5));
        assertEquals(1, test.searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(4, test.searchInsert(new int[]{1, 3, 5, 6}, 7));
        assertEquals(0, test.searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

    @Test
    public void testLargeCase() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) arr[i] = i * 2;
        assertEquals(50, test.searchInsert(arr, 100));
        assertEquals(51, test.searchInsert(arr, 101));
    }

    @Test
    public void testSingleElementFound() {
        assertEquals(0, test.searchInsert(new int[]{8}, 8));
    }

    @Test
    public void testSingleElementInsertBefore() {
        assertEquals(0, test.searchInsert(new int[]{8}, 3));
    }

    @Test
    public void testSingleElementInsertAfter() {
        assertEquals(1, test.searchInsert(new int[]{8}, 10));
    }

    @Test
    public void testTwoElementsMiddleInsert() {
        assertEquals(1, test.searchInsert(new int[]{2, 6}, 4));
    }

    @Test
    public void testFindFirstElement() {
        assertEquals(0, test.searchInsert(new int[]{2, 4, 6, 8}, 2));
    }

    @Test
    public void testFindLastElement() {
        assertEquals(3, test.searchInsert(new int[]{2, 4, 6, 8}, 8));
    }

    @Test
    public void testExhaustiveSmallSortedArrays() {
        int minValue = -3;
        int maxValue = 3;

        for (int mask = 1; mask < (1 << (maxValue - minValue + 1)); mask++) {
            int[] nums = valuesForMask(mask, minValue, maxValue);
            for (int target = minValue - 1; target <= maxValue + 1; target++) {
                assertEquals(linearInsertionIndex(nums, target), test.searchInsert(nums, target),
                        "nums=" + java.util.Arrays.toString(nums) + ", target=" + target);
            }
        }
    }

    private int[] valuesForMask(int mask, int minValue, int maxValue) {
        int[] values = new int[Integer.bitCount(mask)];
        int index = 0;
        for (int value = minValue; value <= maxValue; value++) {
            if ((mask & (1 << (value - minValue))) != 0) {
                values[index++] = value;
            }
        }
        return values;
    }

    private int linearInsertionIndex(int[] nums, int target) {
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] >= target) {
                return index;
            }
        }
        return nums.length;
    }
@Test
    public void testIntegerExtremesAndInternalGaps() {
        int[] values = {Integer.MIN_VALUE, -1, 1, Integer.MAX_VALUE};
        int[] targets = {Integer.MIN_VALUE, Integer.MIN_VALUE + 1, -1, 0, 1, 2, Integer.MAX_VALUE};
        int[] expected = {0, 1, 1, 2, 2, 3, 3};
        for (int i = 0; i < targets.length; i++) assertEquals(expected[i], test.searchInsert(values, targets[i]));
    }

    @Test
    public void testEveryInsertionGapInGiantSortedArray() {
        int[] values = new int[20000];
        for (int i = 0; i < values.length; i++) values[i] = 3 * i - 30000;
        for (int i = 0; i < values.length; i++) {
            assertEquals(i, test.searchInsert(values, values[i]));
            assertEquals(i + 1, test.searchInsert(values, values[i] + 1));
        }
    }

    @Test
    public void testSearchDoesNotModifySortedInput() {
        int[] values = {-19, -7, 4, 11, 40};
        int[] original = values.clone();
        assertEquals(2, test.searchInsert(values, -5));
        org.junit.jupiter.api.Assertions.assertArrayEquals(original, values);
    }
}
