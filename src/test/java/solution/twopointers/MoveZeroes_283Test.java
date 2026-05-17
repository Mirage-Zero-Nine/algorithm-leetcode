package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
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
        assertDoesNotThrow(() -> test.moveZeroes(null));
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

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testAllZerosAtStart() {
        int[] arr = {0, 0, 0, 1, 2};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 2, 0, 0, 0}, arr);
    }

    @Test
    public void testAllZerosAtEnd() {
        int[] arr = {1, 2, 0, 0, 0};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 2, 0, 0, 0}, arr);
    }

    @Test
    public void testNegativeNumbersMixed() {
        int[] arr = {-3, 0, -1, 0, 5, 0, -2};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{-3, -1, 5, -2, 0, 0, 0}, arr);
    }

    @Test
    public void testLargeRandom1000Seed42() {
        Random rng = new Random(42L);
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = rng.nextInt(21) - 10; // range [-10, 10], includes zeros
        }
        int[] original = arr.clone();
        long zeroCount = Arrays.stream(original).filter(x -> x == 0).count();
        int[] nonZeros = Arrays.stream(original).filter(x -> x != 0).toArray();

        test.moveZeroes(arr);

        // Property: relative order of non-zeros preserved
        int[] resultNonZeros = Arrays.stream(arr).filter(x -> x != 0).toArray();
        assertArrayEquals(nonZeros, resultNonZeros);

        // Property: zero count preserved and all zeros at end
        for (int i = arr.length - (int) zeroCount; i < arr.length; i++) {
            assertEquals(0, arr[i]);
        }
        for (int i = 0; i < arr.length - (int) zeroCount; i++) {
            assertTrue(arr[i] != 0);
        }
    }

    @Test
    public void testRelativeOrderPreserved() {
        int[] arr = {4, 0, 1, 0, 3, 0, 2};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{4, 1, 3, 2, 0, 0, 0}, arr);
    }

    @Test
    public void testSingleNonZero() {
        int[] arr = {7};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{7}, arr);
    }
}
