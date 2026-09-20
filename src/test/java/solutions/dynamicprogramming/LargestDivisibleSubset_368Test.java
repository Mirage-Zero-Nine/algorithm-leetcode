package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "divisible subset {0}")
    @CsvSource({"'2,4,8',3", "'3,6,12,24',4", "'2,3,5,7',1", "'1,2,3,6',3", "'4,6,8,12',2", "'5,10,20,25',3", "'2,6,18,54',4", "'7,14,28,9',3", "'1,11,121',3", "'6,10,15,30',2"})
    public void testAdditionalDivisibilityChains(String encoded, int expectedSize) {
        String[] values = encoded.split(","); int[] nums = new int[values.length];
        for (int i = 0; i < values.length; i++) nums[i] = Integer.parseInt(values[i]);
        List<Integer> result = test.largestDivisibleSubset(nums);
        assertEquals(expectedSize, result.size());
        for (int i = 0; i < result.size(); i++) for (int j = i + 1; j < result.size(); j++) {
            int a = result.get(i), b = result.get(j);
            assertTrue(a % b == 0 || b % a == 0);
        }
    }
}
