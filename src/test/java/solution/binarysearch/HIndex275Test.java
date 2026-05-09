package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HIndex275Test {

    private final HIndex_275 test = new HIndex_275();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.hIndex(new int[]{0, 1, 3, 5, 6}));
        assertEquals(2, test.hIndex(new int[]{1, 2, 100}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.hIndex(new int[]{}));
        assertEquals(0, test.hIndex(new int[]{0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.hIndex(new int[]{5, 5, 5, 5, 5}));
    }
}
