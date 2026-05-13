package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SingleNumber_260Test {
    private final SingleNumber_260 solver = new SingleNumber_260();

    private int[] sorted(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        return copy;
    }

    @Test public void testBasic() {
        int[] result = solver.singleNumber(new int[]{1, 2, 1, 3, 2, 5});
        assertArrayEquals(new int[]{3, 5}, sorted(result));
    }

    @Test public void testNegative() {
        int[] result = solver.singleNumber(new int[]{-1, 0});
        assertArrayEquals(new int[]{-1, 0}, sorted(result));
    }

    @Test public void testTwoElements() {
        int[] result = solver.singleNumber(new int[]{7, 13});
        assertArrayEquals(new int[]{7, 13}, sorted(result));
    }

    @Test public void testMixed() {
        int[] result = solver.singleNumber(new int[]{1, 1, 2, 2, 3, 3, 4, 5});
        assertArrayEquals(new int[]{4, 5}, sorted(result));
    }

    @Test public void testWithZero() {
        int[] result = solver.singleNumber(new int[]{0, 1, 2, 2});
        assertArrayEquals(new int[]{0, 1}, sorted(result));
    }

    @Test public void testLargeNumbers() {
        int[] result = solver.singleNumber(new int[]{1000000, 999999, 1000000, 888888});
        assertArrayEquals(new int[]{888888, 999999}, sorted(result));
    }

    @Test public void testBothNegative() {
        int[] result = solver.singleNumber(new int[]{-3, -5, 1, 1});
        assertArrayEquals(new int[]{-5, -3}, sorted(result));
    }

    @Test public void testMixedNegativePositive() {
        int[] result = solver.singleNumber(new int[]{-1, 2, -1, 3, 2, 5});
        assertArrayEquals(new int[]{3, 5}, sorted(result));
    }

    @Test public void testPowersOfTwo() {
        int[] result = solver.singleNumber(new int[]{4, 8, 4, 16, 8, 32});
        assertArrayEquals(new int[]{16, 32}, sorted(result));
    }

    @Test public void testGiantCase() {
        // large array: pairs from 1 to 5000, plus two singles 0 and 10001
        int[] nums = new int[10002];
        int idx = 0;
        for (int i = 1; i <= 5000; i++) {
            nums[idx++] = i;
            nums[idx++] = i;
        }
        nums[idx++] = 0;
        nums[idx] = 10001;
        int[] result = solver.singleNumber(nums);
        assertArrayEquals(new int[]{0, 10001}, sorted(result));
    }
}
