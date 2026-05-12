package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSubarraysWithSum930Test {

    private final NumSubarraysWithSum_930 test = new NumSubarraysWithSum_930();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        assertEquals(27, test.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 0));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numSubarraysWithSum(new int[]{1, 1, 1}, 3));
        assertEquals(1, test.numSubarraysWithSum(new int[]{1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.numSubarraysWithSum(new int[]{1, 1, 1, 1, 1, 1}, 3));
    }
}
