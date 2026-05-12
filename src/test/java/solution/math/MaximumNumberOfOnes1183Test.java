package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumNumberOfOnes1183Test {

    private final MaximumNumberOfOnes_1183 test = new MaximumNumberOfOnes_1183();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maximumNumberOfOnes(5, 3, 2, 1));
        assertEquals(0, test.maximumNumberOfOnes(2, 2, 2, 0));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maximumNumberOfOnes(3, 3, 2, 0));
        assertEquals(1, test.maximumNumberOfOnes(1, 1, 1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.maximumNumberOfOnes(4, 4, 2, 2));
    }
}
