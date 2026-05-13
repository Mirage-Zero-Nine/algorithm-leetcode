package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link WaysToSplit_1712}.
 */
public class WaysToSplit_1712Test {

    private final WaysToSplit_1712 solver = new WaysToSplit_1712();

    @Test
    public void testLeetCodeExample1() {
        // nums = [1,1,1]
        // split at index 0: left=[1], mid=[1], right=[1]. 1<=1<=1 ✓
        // only 1 way
        assertEquals(1, solver.waysToSplit(new int[]{1, 1, 1}));
    }

    @Test
    public void testLeetCodeExample2() {
        // nums = [1,2,2,2,5,0]
        // 3 good ways
        assertEquals(3, solver.waysToSplit(new int[]{1, 2, 2, 2, 5, 0}));
    }

    @Test
    public void testLeetCodeExample3() {
        // nums = [3,2,1]
        // No good split: sum left <= sum mid <= sum right impossible
        // split at 0: left=[3], mid=[2], right=[1]. 3<=2? No.
        // split at 1: left=[3,2], mid=[1], right=[]. Not valid (right empty).
        assertEquals(0, solver.waysToSplit(new int[]{3, 2, 1}));
    }

    @Test
    public void testThreeEqualElements() {
        // nums = [2,2,2]
        // split at 0: left=[2], mid=[2], right=[2]. 2<=2<=2 ✓
        assertEquals(1, solver.waysToSplit(new int[]{2, 2, 2}));
    }

    @Test
    public void testAllZeros() {
        // nums = [0,0,0,0]
        // split at 0: left=[0], mid=[0], right=[0,0]. 0<=0<=0 ✓
        // split at 1: left=[0,0], mid=[0], right=[0]. 0<=0<=0 ✓
        assertEquals(3, solver.waysToSplit(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testTooShort() {
        // nums length < 3, no valid split
        assertEquals(0, solver.waysToSplit(new int[]{1, 2}));
        assertEquals(0, solver.waysToSplit(new int[]{1}));
        assertEquals(0, solver.waysToSplit(new int[]{}));
    }

    @Test
    public void testIncreasingArray() {
        // nums = [1,2,3,4,5]
        // split at 0: left=[1], mid=[2], right=[3,4,5]. 1<=2<=12 ✓
        // split at 0: left=[1], mid=[2,3], right=[4,5]. 1<=5<=9 ✓
        // split at 0: left=[1], mid=[2,3,4], right=[5]. 1<=9<=5? No.
        // split at 1: left=[1,2], mid=[3], right=[4,5]. 3<=3<=9 ✓
        // split at 1: left=[1,2], mid=[3,4], right=[5]. 3<=7<=5? No.
        // split at 2: left=[1,2,3], mid=[4], right=[5]. 6<=4? No.
        assertEquals(3, solver.waysToSplit(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testDecreasingArray() {
        // nums = [5,4,3,2,1]
        // split at 0: left=[5], mid=[4], right=[3,2,1]. 5<=4? No.
        // split at 1: left=[5,4], mid=[3], right=[2,1]. 9<=3? No.
        // split at 2: left=[5,4,3], mid=[2], right=[1]. 12<=2? No.
        assertEquals(0, solver.waysToSplit(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testAllSameLarge() {
        // nums = [1,1,1,1,1]
        // split at 0: left=[1], mid=[1], right=[1,1,1]. 1<=1<=3 ✓
        // split at 0: left=[1], mid=[1,1], right=[1,1]. 1<=2<=2 ✓
        // split at 0: left=[1], mid=[1,1,1], right=[1]. 1<=3<=1? No.
        // split at 1: left=[1,1], mid=[1], right=[1,1]. 2<=1? No.
        // split at 1: left=[1,1], mid=[1,1], right=[1]. 2<=2<=1? No.
        // split at 2: left=[1,1,1], mid=[1], right=[1]. 3<=1? No.
        assertEquals(2, solver.waysToSplit(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, solver.waysToSplit(null));
    }

    @Test
    public void testSixZeros() {
        assertEquals(10, solver.waysToSplit(new int[]{0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) {
            nums[i] = 1;
        }
        int result = solver.waysToSplit(nums);
        assertTrue(result > 0);
    }
}
