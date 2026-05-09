package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxConsecutiveOnes487Test {

    private final FindMaxConsecutiveOnes_487 test = new FindMaxConsecutiveOnes_487();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{0}));
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 1, 1, 1, 1, 0, 1}));
    }
}
