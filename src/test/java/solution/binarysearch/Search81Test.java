package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Search81Test {

    private final Search_81 test = new Search_81();

    @Test
    public void testHappyCases() {
        assertTrue(test.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        assertTrue(test.search(new int[]{1, 3, 1, 1, 1}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        assertFalse(test.search(new int[]{}, 1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, 2));
        assertFalse(test.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 2));
    }
}
