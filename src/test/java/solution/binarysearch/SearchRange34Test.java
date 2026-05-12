package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SearchRange34Test {

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
}
