package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Search_33Test {

    private final Search_33 test = new Search_33();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        assertEquals(0, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        assertEquals(-1, test.search(new int[]{}, 5));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.search(new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testNotRotatedArray() {
        assertEquals(3, test.search(new int[]{1, 2, 3, 4, 5}, 4));
    }

    @Test
    public void testTwoElementRotatedArray() {
        assertEquals(1, test.search(new int[]{2, 1}, 1));
    }

    @Test
    public void testSingleElementFound() {
        assertEquals(0, test.search(new int[]{9}, 9));
    }

    @Test
    public void testSingleElementNotFound() {
        assertEquals(-1, test.search(new int[]{9}, 8));
    }

    @Test
    public void testTargetAtEnd() {
        assertEquals(6, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2));
    }

    @Test
    public void testTargetAtPivot() {
        assertEquals(4, test.search(new int[]{30, 40, 50, 60, 10, 20}, 10));
    }

    @Test
    public void testGiantCase() {
        int n = 2000;
        int[] nums = new int[n];
        int pivot = 777;
        for (int i = 0; i < n; i++) {
            nums[i] = (i + pivot) % n;
        }
        assertEquals(123, test.search(nums, (123 + pivot) % n));
    }
}
