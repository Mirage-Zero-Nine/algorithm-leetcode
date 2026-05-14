package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LargestDivisibleSubset_368Test {

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

    @Test
    public void testNoDivisiblePair() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{3, 5, 7, 11});
        assertEquals(1, result.size());
    }

    @Test
    public void testTwoElements() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{2, 4});
        assertEquals(2, result.size());
    }

    @Test
    public void testTwoNonDivisible() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{3, 5});
        assertEquals(1, result.size());
    }

    @Test
    public void testWithOneIncluded() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{1, 3, 9, 27});
        assertEquals(4, result.size());
    }

    @Test
    public void testUnsortedInput() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{8, 1, 4, 2});
        assertEquals(4, result.size());
    }

    @Test
    public void testGiantCase() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024});
        assertEquals(11, result.size());
    }

    @Test
    public void testMixedDivisibility() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{1, 2, 4, 8, 3, 9, 27});
        assertTrue(result.size() >= 4);
    }

    @Test
    public void testLargeNonPowerOf2() {
        List<Integer> result = test.largestDivisibleSubset(new int[]{1, 3, 6, 12, 24, 48});
        assertEquals(6, result.size());
    }
}
