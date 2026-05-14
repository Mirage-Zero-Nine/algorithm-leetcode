package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanPartitionKSubsets_698Test {

    private final CanPartitionKSubsets_698 test = new CanPartitionKSubsets_698();

    @Test
    public void testHappyCases() {
        assertTrue(test.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        assertFalse(test.canPartitionKSubsets(new int[]{1, 2, 3, 4}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canPartitionKSubsets(null, 2));
        assertFalse(test.canPartitionKSubsets(new int[]{1, 2}, 3));
    }

    @Test
    public void testLargeCase() {
        assertFalse(test.canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 4, 5}, 4));
    }

    @Test
    public void testEmptyArray() {
        assertFalse(test.canPartitionKSubsets(new int[]{}, 1));
    }

    @Test
    public void testSingleElementK1() {
        assertTrue(test.canPartitionKSubsets(new int[]{5}, 1));
    }

    @Test
    public void testAllEqualElements() {
        assertTrue(test.canPartitionKSubsets(new int[]{3, 3, 3, 3}, 4));
    }

    @Test
    public void testSumNotDivisibleByK() {
        // sum=11, 11%3 != 0
        assertFalse(test.canPartitionKSubsets(new int[]{1, 2, 3, 5}, 3));
    }

    @Test
    public void testK1AlwaysTrue() {
        assertTrue(test.canPartitionKSubsets(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testTwoSubsets() {
        assertTrue(test.canPartitionKSubsets(new int[]{1, 5, 11, 5}, 2));
    }

    @Test
    public void testTwoSubsetsImpossible() {
        assertFalse(test.canPartitionKSubsets(new int[]{1, 2, 3, 5}, 2));
    }

    @Test
    public void testGiantCase() {
        // 16 elements, each pair sums to 17, k=8
        assertTrue(test.canPartitionKSubsets(new int[]{1, 16, 2, 15, 3, 14, 4, 13, 5, 12, 6, 11, 7, 10, 8, 9}, 8));
    }
}
