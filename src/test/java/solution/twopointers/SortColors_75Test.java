package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;

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

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        test.sortColors(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSingleElements() {
        int[] a = {1};
        test.sortColors(a);
        assertArrayEquals(new int[]{1}, a);
        int[] b = {2};
        test.sortColors(b);
        assertArrayEquals(new int[]{2}, b);
    }

    @Test
    public void testReverseSortedFull() {
        int[] arr = {2, 2, 1, 1, 0, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, arr);
    }

    @Test
    public void testOnlyOnesAndTwos() {
        int[] arr = {2, 1, 2, 1, 1, 2};
        test.sortColors(arr);
        assertArrayEquals(new int[]{1, 1, 1, 2, 2, 2}, arr);
    }

    @Test
    public void testOnlyZerosAndOnes() {
        int[] arr = {1, 0, 1, 0, 0, 1};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 0, 1, 1, 1}, arr);
    }

    @Test
    public void testDutchFlagBugPatterns() {
        int[] a = {2, 0, 1};
        test.sortColors(a);
        assertArrayEquals(new int[]{0, 1, 2}, a);

        int[] b = {1, 2, 0};
        test.sortColors(b);
        assertArrayEquals(new int[]{0, 1, 2}, b);

        int[] c = {2, 0, 2, 1, 1, 0};
        test.sortColors(c);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, c);
    }

    @Test
    public void testLargeRandomCrossCheck() {
        Random rand = new Random(42L);
        int[] arr = new int[1500];
        for (int i = 0; i < arr.length; i++) arr[i] = rand.nextInt(3);
        int[] expected = arr.clone();
        Arrays.sort(expected);
        test.sortColors(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testPropertyCountsPreserved() {
        int[] arr = {2, 0, 1, 2, 0, 1, 0, 2, 1, 0};
        int[] countsBefore = new int[3];
        for (int v : arr) countsBefore[v]++;
        test.sortColors(arr);
        int[] countsAfter = new int[3];
        for (int v : arr) countsAfter[v]++;
        assertArrayEquals(countsBefore, countsAfter);
        // also verify sorted
        assertArrayEquals(new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 2}, arr);
    }

    @Test
    public void testAlternatingPattern() {
        int[] arr = {0, 2, 0, 2, 0, 2, 1, 1, 1};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, arr);
    }
}
