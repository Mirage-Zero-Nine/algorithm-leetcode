package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HIndex274Test {

    private final HIndex_274 test = new HIndex_274();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.hIndex(new int[]{3, 0, 6, 1, 5}));
        assertEquals(1, test.hIndex(new int[]{1, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.hIndex(new int[]{0}));
        assertEquals(1, test.hIndex(new int[]{1}));
        assertEquals(0, test.hIndex(new int[]{0, 0, 0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.hIndex(new int[]{5, 5, 5, 5, 5}));
        assertEquals(5, test.hIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
