package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SearchRange_34Test {

    private final SearchRange_34 test = new SearchRange_34();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{3, 4}, test.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        assertArrayEquals(new int[]{0, 0}, test.searchRange(new int[]{1}, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(new int[]{}, 0));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{2, 7}, test.searchRange(new int[]{1, 2, 3, 3, 3, 3, 3, 3, 4, 5}, 3));
    }

    @Test
    public void testTargetAtStart() {
        assertArrayEquals(new int[]{0, 2}, test.searchRange(new int[]{2, 2, 2, 3, 4}, 2));
    }

    @Test
    public void testTargetAtEnd() {
        assertArrayEquals(new int[]{3, 4}, test.searchRange(new int[]{1, 2, 3, 9, 9}, 9));
    }

    @Test
    public void testAllElementsAreTarget() {
        assertArrayEquals(new int[]{0, 4}, test.searchRange(new int[]{6, 6, 6, 6, 6}, 6));
    }

    @Test
    public void testSingleElementNotFound() {
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(new int[]{6}, 3));
    }

    @Test
    public void testTwoElementsTargetFirst() {
        assertArrayEquals(new int[]{0, 0}, test.searchRange(new int[]{3, 5}, 3));
    }

    @Test
    public void testTwoElementsTargetSecond() {
        assertArrayEquals(new int[]{1, 1}, test.searchRange(new int[]{3, 5}, 5));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i < 300 ? 1 : (i < 700 ? 2 : 3);
        }
        assertArrayEquals(new int[]{300, 699}, test.searchRange(arr, 2));
    }
}
