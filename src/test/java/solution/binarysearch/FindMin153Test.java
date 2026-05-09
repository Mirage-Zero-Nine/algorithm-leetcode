package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMin153Test {

    private final FindMin_153 test = new FindMin_153();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.findMin(new int[]{3, 4, 5, 1, 2}));
        assertEquals(0, test.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.findMin(new int[]{1}));
        assertEquals(1, test.findMin(new int[]{1, 2, 3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.findMin(new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5}));
    }
}
