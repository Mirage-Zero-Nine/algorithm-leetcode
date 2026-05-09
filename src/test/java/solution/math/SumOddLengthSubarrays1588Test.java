package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SumOddLengthSubarrays1588Test {

    private final SumOddLengthSubarrays_1588 test = new SumOddLengthSubarrays_1588();

    @Test
    public void testHappyCases() {
        assertEquals(58, test.sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
        assertEquals(3, test.sumOddLengthSubarrays(new int[]{1, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(10, test.sumOddLengthSubarrays(new int[]{10}));
        assertEquals(1, test.sumOddLengthSubarrays(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(57, test.sumOddLengthSubarrays(new int[]{1, 2, 3, 4, 5}));
    }
}
