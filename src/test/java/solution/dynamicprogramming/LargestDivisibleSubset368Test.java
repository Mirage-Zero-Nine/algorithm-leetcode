package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LargestDivisibleSubset368Test {

    private final LargestDivisibleSubset_368 test = new LargestDivisibleSubset_368();

    @Test
    public void testHappyCases() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{1, 2, 3});
        assertTrue(result.size() >= 2);
        List<Integer> result2 = test.largestDivisibleSubset(new int[]{1, 2, 4, 8});
        assertEquals(4, result2.size());
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.largestDivisibleSubset(new int[]{1}).size());
        assertEquals(0, test.largestDivisibleSubset(new int[]{}).size());
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{1, 2, 4, 8, 16, 32});
        assertEquals(6, result.size());
    }
}
