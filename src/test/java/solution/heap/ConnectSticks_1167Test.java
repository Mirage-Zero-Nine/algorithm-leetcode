package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConnectSticks_1167Test {

    private final ConnectSticks_1167 test = new ConnectSticks_1167();

    @Test
    public void testHappyCases() {
        assertEquals(14, test.connectSticks(new int[]{2, 4, 3}));
        assertEquals(30, test.connectSticks(new int[]{1, 8, 3, 5}));
    }

    @Test
    public void testNegativeAndInvalidCases() {
        assertEquals(0, test.connectSticks(new int[]{5}));
        assertEquals(0, test.connectSticks(new int[]{0}));
    }

    @Test
    public void testEdgeAndLargeCases() {
        assertEquals(5, test.connectSticks(new int[]{1, 1, 1}));
        assertEquals(173, test.connectSticks(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
