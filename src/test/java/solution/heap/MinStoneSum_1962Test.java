package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinStoneSum_1962Test {

    private final MinStoneSum_1962 test = new MinStoneSum_1962();

    @Test
    public void testHappyCases() {
        assertEquals(12, test.minStoneSum(new int[]{5, 4, 9}, 2));
        assertEquals(12, test.minStoneSum(new int[]{4, 3, 6, 7}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.minStoneSum(new int[]{1}, 5));
        assertEquals(0, test.minStoneSum(new int[]{0, 0}, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(22, test.minStoneSum(new int[]{10, 10, 10, 10, 10, 10}, 10));
    }
}
