package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SortColors_75Test {

    private final SortColors_75 test = new SortColors_75();

    @Test
    public void testHappyCases() {
        int[] arr = {2, 0, 2, 1, 1, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, arr);
    }

    @Test
    public void testEdgeCases() {
        int[] arr = {0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0}, arr);
        int[] arr2 = {1, 0};
        test.sortColors(arr2);
        assertArrayEquals(new int[]{0, 1}, arr2);
    }

    @Test
    public void testLargeCase() {
        int[] arr = {2, 2, 2, 1, 1, 1, 0, 0, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, arr);
    }

    @Test
    public void testAlreadySorted() {
        int[] arr = {0, 0, 1, 1, 2, 2};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, arr);
    }

    @Test
    public void testReverseSorted() {
        int[] arr = {2, 1, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 1, 2}, arr);
    }

    @Test
    public void testAllZeros() {
        int[] arr = {0, 0, 0, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 0, 0}, arr);
    }

    @Test
    public void testAllOnes() {
        int[] arr = {1, 1, 1};
        test.sortColors(arr);
        assertArrayEquals(new int[]{1, 1, 1}, arr);
    }

    @Test
    public void testAllTwos() {
        int[] arr = {2, 2, 2};
        test.sortColors(arr);
        assertArrayEquals(new int[]{2, 2, 2}, arr);
    }

    @Test
    public void testTwoElements() {
        int[] arr = {2, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 2}, arr);
    }

    @Test
    public void testNoOnes() {
        int[] arr = {2, 0, 2, 0, 0, 2};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 0, 2, 2, 2}, arr);
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[9000];
        for (int i = 0; i < 3000; i++) arr[i] = 2;
        for (int i = 3000; i < 6000; i++) arr[i] = 0;
        for (int i = 6000; i < 9000; i++) arr[i] = 1;
        test.sortColors(arr);
        for (int i = 0; i < 3000; i++) assertArrayEquals(new int[]{0}, new int[]{arr[i]});
        for (int i = 3000; i < 6000; i++) assertArrayEquals(new int[]{1}, new int[]{arr[i]});
        for (int i = 6000; i < 9000; i++) assertArrayEquals(new int[]{2}, new int[]{arr[i]});
    }
}
