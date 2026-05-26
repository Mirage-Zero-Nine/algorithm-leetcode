package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/31 18:18
 * Created with IntelliJ IDEA
 */

public class NumArray_307Test {
    private NumArray_307 test;

    @Test
    public void test() {
        test = new NumArray_307(new int[]{0, 9, 5, 7, 3});
        assertEquals(test.sumRange(4, 4), 3);
    }

    @Test
    public void test1() {
        test = new NumArray_307(new int[]{1, 3, 5});
        assertEquals(test.sumRange(0, 2), 9);
        test.update(1, 2);
        assertEquals(test.sumRange(0, 2), 8);
    }

    @Test
    public void testSingleElementArray() {
        test = new NumArray_307(new int[]{42});
        assertEquals(42, test.sumRange(0, 0));
        test.update(0, -3);
        assertEquals(-3, test.sumRange(0, 0));
    }

    @Test
    public void testRangeInsideLeftSubtree() {
        test = new NumArray_307(new int[]{2, 4, 6, 8, 10, 12});
        assertEquals(12, test.sumRange(0, 2));
    }

    @Test
    public void testRangeInsideRightSubtree() {
        test = new NumArray_307(new int[]{2, 4, 6, 8, 10, 12});
        assertEquals(30, test.sumRange(3, 5));
    }

    @Test
    public void testRangeSpanningBothSides() {
        test = new NumArray_307(new int[]{2, 4, 6, 8, 10, 12});
        assertEquals(28, test.sumRange(1, 4));
    }

    @Test
    public void testMultipleUpdatesOnSameIndex() {
        test = new NumArray_307(new int[]{5, 5, 5});
        test.update(1, 10);
        assertEquals(20, test.sumRange(0, 2));
        test.update(1, -5);
        assertEquals(5, test.sumRange(0, 2));
    }

    @Test
    public void testNegativeNumbers() {
        test = new NumArray_307(new int[]{-1, -2, -3, -4});
        assertEquals(-10, test.sumRange(0, 3));
        test.update(2, 3);
        assertEquals(-3, test.sumRange(1, 3));
    }

    @Test
    public void testUpdateFirstAndLastIndex() {
        test = new NumArray_307(new int[]{1, 2, 3, 4, 5});
        test.update(0, 10);
        test.update(4, 20);
        assertEquals(39, test.sumRange(0, 4));
    }

    @Test
    public void testGiantCase() {
        int n = 2000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        test = new NumArray_307(nums);

        assertEquals((n * (n + 1)) / 2, test.sumRange(0, n - 1));
        for (int i = 0; i < n; i += 100) {
            test.update(i, 0);
        }
        assertEquals(495, test.sumRange(1, 30));
    }
}
