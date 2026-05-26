package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testTwoSticks() {
        assertEquals(3, test.connectSticks(new int[]{1, 2}));
    }

    @Test
    public void testAllSameLength() {
        assertEquals(10, test.connectSticks(new int[]{2, 2, 2}));
    }

    @Test
    public void testLargeValues() {
        assertEquals(20000, test.connectSticks(new int[]{10000, 10000}));
    }

    @Test
    public void testFiveSticks() {
        assertEquals(57, test.connectSticks(new int[]{3, 4, 5, 6, 7}));
    }

    @Test
    public void testAllOnes() {
        // 4 sticks of length 1: (1+1)=2, (1+1)=2, (2+2)=4 -> cost = 2+2+4 = 8
        assertEquals(8, test.connectSticks(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testSingleZeroStick() {
        assertEquals(0, test.connectSticks(new int[]{100}));
    }

    @Test
    public void testGiantCase() {
        int[] sticks = new int[1000];
        for (int i = 0; i < 1000; i++) sticks[i] = 1;
        int result = test.connectSticks(sticks);
        // Result should be positive and large
        assertTrue(result > 0);
    }

    @Test
    public void testDescendingOrder() {
        assertEquals(25, test.connectSticks(new int[]{8, 4, 2, 1}));
    }
}
