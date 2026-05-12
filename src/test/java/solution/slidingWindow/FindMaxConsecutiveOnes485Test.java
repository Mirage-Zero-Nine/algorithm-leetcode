package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxConsecutiveOnes485Test {

    private final FindMaxConsecutiveOnes_485 test = new FindMaxConsecutiveOnes_485();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        assertEquals(2, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0}));
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1}));
    }
}
