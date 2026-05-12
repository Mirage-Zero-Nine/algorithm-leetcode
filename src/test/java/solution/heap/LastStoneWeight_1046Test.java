package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastStoneWeight_1046Test {

    private final LastStoneWeight_1046 test = new LastStoneWeight_1046();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        assertEquals(2, test.lastStoneWeight(new int[]{10, 4, 2, 10}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.lastStoneWeight(new int[]{1, 1}));
        assertEquals(9, test.lastStoneWeight(new int[]{9}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.lastStoneWeight(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
    }
}
