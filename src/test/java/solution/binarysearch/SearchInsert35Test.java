package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SearchInsert35Test {

    private final SearchInsert_35 test = new SearchInsert_35();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.searchInsert(new int[]{1, 3, 5, 6}, 5));
        assertEquals(1, test.searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(4, test.searchInsert(new int[]{1, 3, 5, 6}, 7));
        assertEquals(0, test.searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

    @Test
    public void testLargeCase() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) arr[i] = i * 2;
        assertEquals(50, test.searchInsert(arr, 100));
        assertEquals(51, test.searchInsert(arr, 101));
    }
}
