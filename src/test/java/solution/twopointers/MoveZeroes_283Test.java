package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MoveZeroes_283Test {

    private final MoveZeroes_283 test = new MoveZeroes_283();

    @Test
    public void testHappyCases() {
        int[] arr = {0, 1, 0, 3, 12};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, arr);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        int[] arr = {0};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{0}, arr);
        int[] arr2 = {1};
        test.moveZeroes(arr2);
        assertArrayEquals(new int[]{1}, arr2);
    }

    @Test
    public void testLargeCase() {
        int[] arr = {0, 0, 0, 1, 2, 3};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 2, 3, 0, 0, 0}, arr);
    }

    @Test
    public void testNoZeroes() {
        int[] arr = {1, 2, 3, 4, 5};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testAllZeroes() {
        int[] arr = {0, 0, 0, 0};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{0, 0, 0, 0}, arr);
    }

    @Test
    public void testNullInput() {
        test.moveZeroes(null); // should not throw
    }

    @Test
    public void testZeroAtEnd() {
        int[] arr = {1, 2, 3, 0};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 2, 3, 0}, arr);
    }

    @Test
    public void testAlternatingZeroes() {
        int[] arr = {0, 1, 0, 2, 0, 3};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 2, 3, 0, 0, 0}, arr);
    }

    @Test
    public void testNegativeNumbers() {
        int[] arr = {0, -1, 0, -2, 3};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{-1, -2, 3, 0, 0}, arr);
    }

    @Test
    public void testGiantCase() {
        int n = 100000;
        int[] arr = new int[n];
        // first half zeroes, second half non-zero
        for (int i = n / 2; i < n; i++) arr[i] = i;
        test.moveZeroes(arr);
        for (int i = 0; i < n / 2; i++) {
            assertArrayEquals(new int[]{i + n / 2}, new int[]{arr[i]});
        }
        for (int i = n / 2; i < n; i++) {
            assertArrayEquals(new int[]{0}, new int[]{arr[i]});
        }
    }

    @Test
    public void testTwoElements() {
        int[] arr = {0, 1};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 0}, arr);
    }
}
