package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumRollsToTarget1155Test {

    private final NumRollsToTarget_1155 test = new NumRollsToTarget_1155();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.numRollsToTarget(1, 6, 3));
        assertEquals(6, test.numRollsToTarget(2, 6, 7));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numRollsToTarget(2, 6, 1));
        assertEquals(1, test.numRollsToTarget(1, 1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(222616187, test.numRollsToTarget(30, 30, 500));
    }
}
