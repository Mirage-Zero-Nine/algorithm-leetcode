package solutions.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link KConcatenationMaxSum_1191}.
 */
public class KConcatenationMaxSum_1191Test {

    private final KConcatenationMaxSum_1191 solver = new KConcatenationMaxSum_1191();

    @Test
    public void testLeetCodeExample1() {
        // arr = [1,2], k = 3 → [1,2,1,2,1,2]
        // max subarray sum = 1+2+1+2+1+2 = 9
        assertEquals(9, solver.kConcatenationMaxSum(new int[]{1, 2}, 3));
    }

    @Test
    public void testLeetCodeExample2() {
        // arr = [1,-2,1], k = 5 → [1,-2,1,1,-2,1,1,-2,1,1,-2,1,1,-2,1]
        // max subarray: [1,-2,1,1] = 1
        assertEquals(2, solver.kConcatenationMaxSum(new int[]{1, -2, 1}, 5));
    }

    @Test
    public void testLeetCodeExample3() {
        // arr = [-1,-2], k = 7 → all negative
        // max subarray sum = 0 (empty subarray)
        assertEquals(0, solver.kConcatenationMaxSum(new int[]{-1, -2}, 7));
    }

    @Test
    public void testKEquals1() {
        // arr = [1,2,3], k = 1 → [1,2,3]
        // max subarray = 6
        assertEquals(6, solver.kConcatenationMaxSum(new int[]{1, 2, 3}, 1));
    }

    @Test
    public void testAllZeros() {
        // arr = [0,0], k = 100
        assertEquals(0, solver.kConcatenationMaxSum(new int[]{0, 0}, 100));
    }

    @Test
    public void testSinglePositive() {
        // arr = [5], k = 3 → [5,5,5]
        assertEquals(15, solver.kConcatenationMaxSum(new int[]{5}, 3));
    }

    @Test
    public void testSingleNegative() {
        // arr = [-3], k = 3 → [-3,-3,-3]
        // max subarray = 0 (empty)
        assertEquals(0, solver.kConcatenationMaxSum(new int[]{-3}, 3));
    }

    @Test
    public void testAllNegative() {
        // arr = [-1,-2,-3], k = 7
        assertEquals(0, solver.kConcatenationMaxSum(new int[]{-1, -2, -3}, 7));
    }

    @Test
    public void testMixedWithLargeK() {
        // arr = [1,-1,1,-1], k = 100000
        // sum = 0, so no benefit from k repetitions
        // max subarray in [1,-1,1] = 1
        int result = solver.kConcatenationMaxSum(new int[]{1, -1, 1, -1}, 100000);
        assertEquals(1, result);
    }

    @Test
    public void testPositiveSumWithLargeK() {
        // arr = [1,2], k = 100000
        // sum = 3 > 0
        // max = (k-2)*sum + prefixMax + suffixMax = 99998*3 + 3 + 3 = 300000
        // mod = 300000 % (10^9+7) = 300000
        int expected = 300000;
        assertEquals(expected, solver.kConcatenationMaxSum(new int[]{1, 2}, 100000));
    }

    @Test
    public void testMaxSubarrayInOneCopy() {
        // arr = [3,-1,2], k = 2 → [3,-1,2,3,-1,2]
        // max subarray in one copy = 4 (3,-1,2)
        // max subarray in two copies = [3,-1,2,3] = 7
        assertEquals(8, solver.kConcatenationMaxSum(new int[]{3, -1, 2}, 2));
    }

    @Test
    public void testEmptySubarrayAllowed() {
        // arr = [-5,-3], k = 1
        // max subarray = 0 (empty)
        assertEquals(0, solver.kConcatenationMaxSum(new int[]{-5, -3}, 1));
    }

    @Test
    public void testLargeValuesModulo() {
        // arr = [1000000000], k = 100000
        // sum = 10^9, max = 10^9 * 100000 = 10^14
        // mod = 10^9 + 7
        // 10^14 % (10^9 + 7) = 10^14 - 99999 * (10^9 + 7)
        // = 10^14 - 99999000000000 - 699993 = 100000000000 - 699993 = 99999300007
        // Actually let me compute: (int)(100000000000L % 1000000007)
        // 100000000000 = 99 * 1000000007 + 999999943
        // So result = 999999943
        // Expected: 10^14 % (10^9+7) = 999300007
        assertEquals(999300007, solver.kConcatenationMaxSum(new int[]{1000000000}, 100000));
    }
}
