package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumOfSubarrays1524Test {

    private final NumOfSubarrays_1524 test = new NumOfSubarrays_1524();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numOfSubarrays(new int[]{1, 3, 5}));
        assertEquals(0, test.numOfSubarrays(new int[]{2, 4, 6, 8, 10}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numOfSubarrays(new int[]{2, 4, 6}));
        assertEquals(1, test.numOfSubarrays(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(16, test.numOfSubarrays(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }
}
