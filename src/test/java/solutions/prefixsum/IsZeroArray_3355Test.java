package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link IsZeroArray_3355}.
 */
public class IsZeroArray_3355Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void queryCoverageMatchesExplicitLegalDecrements(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int trial = 0; trial < 100; trial++) {
            int size = 1 + random.nextInt(15);
            int[] nums = random.ints(size, 0, 5).toArray();
            int[] remaining = nums.clone();
            int[][] queries = new int[1 + random.nextInt(20)][2];
            for (int[] query : queries) {
                query[0] = random.nextInt(size);
                query[1] = query[0] + random.nextInt(size - query[0]);
                for (int i = query[0]; i <= query[1]; i++) remaining[i] = Math.max(0, remaining[i] - 1);
            }
            assertEquals(java.util.Arrays.stream(remaining).allMatch(value -> value == 0),
                    solver.isZeroArray(nums, queries));
        }
    }

    @Test
    void largeOverlappingRangesDistinguishUncoveredEndpoint() {
        int[] nums = new int[100_000];
        java.util.Arrays.fill(nums, 2);
        assertTrue(solver.isZeroArray(nums.clone(), new int[][]{{0, 99_999}, {0, 99_999}}));
        assertFalse(solver.isZeroArray(nums.clone(), new int[][]{{0, 99_999}, {0, 99_998}}));
    }


    private final IsZeroArray_3355 solver = new IsZeroArray_3355();

    @Test
    public void testLeetCodeExample1() {
        // nums = [1,0,1], queries = [[0,2]]
        // After query [0,2]: decrement indices 0,1,2 by 1 → [0,-1,0]
        // nums[0]=0 ≤ 0 ✓, nums[1]=-1 ≤ 0 ✓, nums[2]=0 ≤ 0 ✓ → true
        int[] nums = {1, 0, 1};
        assertTrue(solver.isZeroArray(nums, new int[][]{{0, 2}}));
    }

    @Test
    public void testLeetCodeExample2() {
        // nums = [1,0,0], queries = [[0,2]]
        // After query [0,2]: decrement indices 0,1,2 by 1 → [0,-1,-1]
        // All ≤ 0 → true
        int[] nums = {1, 0, 0};
        assertTrue(solver.isZeroArray(nums, new int[][]{{0, 2}}));
    }

    @Test
    public void testLeetCodeExample3() {
        // nums = [1,0,0], queries = [[0,0],[1,1]]
        // After [0,0]: nums[0] -= 1 → [0,0,0]
        // After [1,1]: nums[1] -= 1 → [0,-1,0]
        // All ≤ 0 → true
        int[] nums = {1, 0, 0};
        assertTrue(solver.isZeroArray(nums, new int[][]{{0, 0}, {1, 1}}));
    }

    @Test
    public void testAlreadyZero() {
        // nums = [0,0,0], queries = [[0,2]]
        // After query: [-1,-1,-1] → all ≤ 0 → true
        int[] nums = {0, 0, 0};
        assertTrue(solver.isZeroArray(nums, new int[][]{{0, 2}}));
    }

    @Test
    public void testCannotZero() {
        // nums = [1,1,1], queries = [[0,0]]
        // After query: [0,1,1] → nums[1]=1 > 0 → false
        int[] nums = {1, 1, 1};
        assertFalse(solver.isZeroArray(nums, new int[][]{{0, 0}}));
    }

    @Test
    public void testNoQueries() {
        // nums = [1,0,1], no queries → nums stays [1,0,1] → nums[0]=1 > 0 → false
        int[] nums = {1, 0, 1};
        assertFalse(solver.isZeroArray(nums, new int[][]{}));
    }

    @Test
    public void testNoQueriesAlreadyZero() {
        // nums = [0,0,0], no queries → already zero → true
        int[] nums = {0, 0, 0};
        assertTrue(solver.isZeroArray(nums, new int[][]{}));
    }

    @Test
    public void testSingleElement() {
        // nums = [1], queries = [[0,0]]
        // After query: [0] → true
        int[] nums = {1};
        assertTrue(solver.isZeroArray(nums, new int[][]{{0, 0}}));
    }

    @Test
    public void testSingleElementOverDecrement() {
        // nums = [1], queries = [[0,0],[0,0]]
        // After first query: [0]
        // After second query: [-1] → true (≤ 0)
        int[] nums = {1};
        assertTrue(solver.isZeroArray(nums, new int[][]{{0, 0}, {0, 0}}));
    }

    @Test
    public void testMultipleQueriesCoverAll() {
        // nums = [2,3,1], queries = [[0,0],[1,2]]
        // After [0,0]: [1,3,1]
        // After [1,2]: [1,2,0] → nums[0]=1 > 0 → false
        int[] nums = {2, 3, 1};
        assertFalse(solver.isZeroArray(nums, new int[][]{{0, 0}, {1, 2}}));
    }

    @Test
    public void testMultipleQueriesCoverAllValid() {
        // nums = [1,2,1], queries = [[0,0],[1,1],[2,2]]
        // After [0,0]: [0,2,1]
        // After [1,1]: [0,1,1]
        // After [2,2]: [0,1,0] → nums[1]=1 > 0 → false
        int[] nums = {1, 2, 1};
        assertFalse(solver.isZeroArray(nums, new int[][]{{0, 0}, {1, 1}, {2, 2}}));
    }

    @Test
    public void testEmptyArray() {
        // nums = [], queries = []
        // diffArr = [0], loop doesn't execute → true
        int[] nums = {};
        assertTrue(solver.isZeroArray(nums, new int[][]{}));
    }
}
