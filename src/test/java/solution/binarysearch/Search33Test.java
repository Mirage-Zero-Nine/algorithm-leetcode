package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Search33Test {

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
}
