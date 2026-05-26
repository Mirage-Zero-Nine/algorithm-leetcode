package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindClosestElements_658Test {

    private final FindClosestElements_658 test = new FindClosestElements_658();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1, 2, 3, 4), test.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
        assertEquals(List.of(1, 2, 3, 4), test.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of(1, 2), test.findClosestElements(new int[]{1, 2, 3}, 2, 1));
        assertEquals(List.of(2, 3, 4), test.findClosestElements(new int[]{1, 2, 3, 4, 5}, 3, 5));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(3, 4, 5, 6, 7), test.findClosestElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, 5));
    }

    @Test
    public void testTiePrefersSmallerElements() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5}, 4, 3, List.of(1, 2, 3, 4));
    }

    @Test
    public void testTargetGreaterThanAll() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        assertEquals(List.of(2, 3, 4), test.findClosestElements(arr, 3, 100));
        assertEquals(List.of(3, 4, 5), test.findClosestElementsBasicBinarySearch(arr, 3, 100));
        assertEquals(List.of(3, 4, 5), test.findClosestElementsTwoPointers(arr, 3, 100));
    }

    @Test
    public void testTargetLessThanAll() {
        assertAllMethods(new int[]{10, 20, 30, 40}, 2, -5, List.of(10, 20));
    }

    @Test
    public void testArrayWithDuplicates() {
        assertAllMethods(new int[]{1, 2, 2, 2, 3, 4}, 3, 2, List.of(2, 2, 2));
    }

    @Test
    public void testNegativeNumbers() {
        assertAllMethods(new int[]{-10, -5, -2, 0, 3}, 2, -4, List.of(-5, -2));
    }

    @Test
    public void testKEqualsArrayLength() {
        assertAllMethods(new int[]{1, 2, 3}, 3, 2, List.of(1, 2, 3));
    }

    @Test
    public void testGiantArray() {
        int n = 200;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        assertAllMethods(arr, 10, 150, List.of(145, 146, 147, 148, 149, 150, 151, 152, 153, 154));
    }

    private void assertAllMethods(int[] arr, int k, int x, List<Integer> expected) {
        assertEquals(expected, test.findClosestElements(arr, k, x));
        assertEquals(expected, test.findClosestElementsBasicBinarySearch(arr, k, x));
        assertEquals(expected, test.findClosestElementsTwoPointers(arr, k, x));
    }
}
