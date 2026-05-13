package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumArray_303Test {

    @Test
    public void testHappyCases() {
        NumArray_303 na = new NumArray_303(new int[]{-2, 0, 3, -5, 2, -1});
        assertEquals(1, na.sumRange(0, 2));
        assertEquals(-1, na.sumRange(2, 5));
        assertEquals(-3, na.sumRange(0, 5));
    }

    @Test
    public void testEdgeCases() {
        NumArray_303 na = new NumArray_303(new int[]{1});
        assertEquals(1, na.sumRange(0, 0));
    }

    @Test
    public void testLargeCase() {
        NumArray_303 na = new NumArray_303(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(55, na.sumRange(0, 9));
        assertEquals(15, na.sumRange(0, 4));
    }

    @Test
    public void testSingleElementRange() {
        NumArray_303 na = new NumArray_303(new int[]{-2, 0, 3, -5, 2, -1});
        assertEquals(-2, na.sumRange(0, 0));
        assertEquals(0, na.sumRange(1, 1));
        assertEquals(3, na.sumRange(2, 2));
    }

    @Test
    public void testAllNegativeNumbers() {
        NumArray_303 na = new NumArray_303(new int[]{-1, -2, -3, -4, -5});
        assertEquals(-15, na.sumRange(0, 4));
        assertEquals(-5, na.sumRange(1, 2));
        assertEquals(-12, na.sumRange(2, 4));
    }

    @Test
    public void testAllZeros() {
        NumArray_303 na = new NumArray_303(new int[]{0, 0, 0, 0, 0});
        assertEquals(0, na.sumRange(0, 4));
        assertEquals(0, na.sumRange(2, 3));
    }

    @Test
    public void testMultipleQueries() {
        NumArray_303 na = new NumArray_303(new int[]{1, -1, 2, -2, 3});
        assertEquals(0, na.sumRange(0, 1));
        assertEquals(0, na.sumRange(2, 3));
        assertEquals(3, na.sumRange(0, 4));
        assertEquals(3, na.sumRange(4, 4));
    }

    @Test
    public void testLargeValues() {
        NumArray_303 na = new NumArray_303(new int[]{100000, -100000, 100000, -100000});
        assertEquals(0, na.sumRange(0, 3));
        assertEquals(0, na.sumRange(0, 1));
        assertEquals(100000, na.sumRange(0, 0));
    }

    @Test
    public void testTwoElements() {
        NumArray_303 na = new NumArray_303(new int[]{5, -3});
        assertEquals(5, na.sumRange(0, 0));
        assertEquals(-3, na.sumRange(1, 1));
        assertEquals(2, na.sumRange(0, 1));
    }

    @Test
    public void testConsecutiveNumbers() {
        NumArray_303 na = new NumArray_303(new int[]{1, 2, 3, 4, 5});
        assertEquals(9, na.sumRange(1, 3));
        assertEquals(12, na.sumRange(2, 4));
        assertEquals(3, na.sumRange(0, 1));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) {
            nums[i] = i + 1;
        }
        NumArray_303 na = new NumArray_303(nums);
        // sum 1..10000 = 50005000
        assertEquals(50005000, na.sumRange(0, 9999));
        // sum 1..100 = 5050
        assertEquals(5050, na.sumRange(0, 99));
        // single element at index 9999 = 10000
        assertEquals(10000, na.sumRange(9999, 9999));
    }
}
