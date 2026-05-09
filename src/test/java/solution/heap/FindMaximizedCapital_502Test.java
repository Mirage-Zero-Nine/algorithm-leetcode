package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaximizedCapital_502Test {

    private final FindMaximizedCapital_502 test = new FindMaximizedCapital_502();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        assertEquals(11, test.findMaximizedCapital(3, 1, new int[]{2, 3, 5}, new int[]{1, 1, 2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findMaximizedCapital(2, 0, new int[]{1, 2}, new int[]{1, 2}));
        assertEquals(5, test.findMaximizedCapital(0, 5, new int[]{1, 2}, new int[]{0, 0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(23, test.findMaximizedCapital(5, 0, new int[]{1, 2, 3, 4, 5, 10}, new int[]{0, 1, 1, 2, 3, 5}));
    }
}
