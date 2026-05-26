package solutions.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KLengthApart_1437Test {
    private final KLengthApart_1437 solution = new KLengthApart_1437();

    @Test
    void testBasicValid() {
        assertTrue(solution.kLengthApart(new int[]{1, 0, 0, 0, 1, 0, 0, 1}, 2));
    }

    @Test
    void testBasicInvalid() {
        assertFalse(solution.kLengthApart(new int[]{1, 0, 0, 1, 0, 1}, 2));
    }

    @Test
    void testSingleOne() {
        assertTrue(solution.kLengthApart(new int[]{1, 0, 0, 0}, 3));
    }

    @Test
    void testNoOnes() {
        assertTrue(solution.kLengthApart(new int[]{0, 0, 0, 0}, 1));
    }

    @Test
    void testExactDistance() {
        assertTrue(solution.kLengthApart(new int[]{1, 0, 1}, 1));
    }

    @Test
    void testKZero() {
        assertTrue(solution.kLengthApart(new int[]{1, 1, 1, 1}, 0));
    }

    @Test
    void testAdjacentOnesFail() {
        assertFalse(solution.kLengthApart(new int[]{1, 1, 0, 0}, 1));
    }

    @Test
    void testAllZeros() {
        assertTrue(solution.kLengthApart(new int[]{0, 0, 0, 0, 0}, 100));
    }

    @Test
    void testSingleElement() {
        assertTrue(solution.kLengthApart(new int[]{1}, 5));
    }

    @Test
    void testOnesAtEnds() {
        assertTrue(solution.kLengthApart(new int[]{1, 0, 0, 0, 0, 1}, 4));
    }

    @Test
    void testOnesAtEndsTooClose() {
        assertFalse(solution.kLengthApart(new int[]{1, 0, 0, 0, 0, 1}, 5));
    }

    @Test
    void testGiantArray() {
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; i += 10) {
            nums[i] = 1;
        }
        assertTrue(solution.kLengthApart(nums, 9));
        assertFalse(solution.kLengthApart(nums, 10));
    }
}
