package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link SubarraySum_560}.
 */
public class SubarraySum_560Test {

    private final SubarraySum_560 solver = new SubarraySum_560();

    @Test
    public void testLeetCodeExample1() {
        // nums = [1,1,1], k = 2
        // subarrays: [1,1] at indices 0-1, [1,1] at indices 1-2 → 2
        int[] nums = {1, 1, 1};
        assertEquals(2, solver.subarraySum(nums, 2));
    }

    @Test
    public void testLeetCodeExample2() {
        // nums = [1,2,3], k = 3
        // subarrays: [1,2], [3] → 2
        int[] nums = {1, 2, 3};
        assertEquals(2, solver.subarraySum(nums, 3));
    }

    @Test
    public void testSingleElementMatch() {
        int[] nums = {1};
        assertEquals(1, solver.subarraySum(nums, 1));
    }

    @Test
    public void testSingleElementNoMatch() {
        int[] nums = {1};
        assertEquals(0, solver.subarraySum(nums, 2));
    }

    @Test
    public void testWithNegativeNumbers() {
        // nums = [1,-1,-1,1], k = 0
        // subarrays with sum 0: [-1,1] at 1-2, [1,-1,-1,1] at 0-3, [-1,-1,1,1] doesn't work...
        // prefix sums: 1, 0, -1, 0
        // i=0: prefix=1, need 1-0=1, count=0, m={0:1, 1:1}
        // i=1: prefix=0, need 0-0=0, count+=1=1, m={0:2, 1:1}
        // i=2: prefix=-1, need -1-0=-1, count+=0=1, m={0:2, 1:1, -1:1}
        // i=3: prefix=0, need 0-0=0, count+=2=3, m={0:3, 1:1, -1:1}
        int[] nums = {1, -1, -1, 1};
        assertEquals(3, solver.subarraySum(nums, 0));
    }

    @Test
    public void testAllZeros() {
        // nums = [0,0,0], k = 0
        // all subarrays sum to 0: [0],[0],[0],[0,0],[0,0],[0,0,0] → 6
        int[] nums = {0, 0, 0};
        assertEquals(6, solver.subarraySum(nums, 0));
    }

    @Test
    public void testNoMatch() {
        int[] nums = {1, 2, 3};
        assertEquals(0, solver.subarraySum(nums, 7));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        assertEquals(0, solver.subarraySum(nums, 0));
    }

    @Test
    public void testLargeArray() {
        // nums = [4,1,3,7], k = 4
        // prefix sums: 4, 5, 8, 15
        // i=0: prefix=4, need 4-4=0, count+=1=1, m={0:1, 4:1}
        // i=1: prefix=5, need 5-4=1, count+=0=1, m={0:1, 4:1, 5:1}
        // i=2: prefix=8, need 8-4=4, count+=1=2, m={0:1, 4:1, 5:1, 8:1}
        // i=3: prefix=15, need 15-4=11, count+=0=2
        int[] nums = {4, 1, 3, 7};
        assertEquals(2, solver.subarraySum(nums, 4));
    }

    @Test
    public void testNegativeK() {
        // nums = [1,-1,0], k = -1
        // prefix sums: 1, 0, 0
        // i=0: prefix=1, need 1-(-1)=2, count+=0=0, m={0:1, 1:1}
        // i=1: prefix=0, need 0-(-1)=1, count+=1=1, m={0:2, 1:1}
        // i=2: prefix=0, need 0-(-1)=1, count+=1=2, m={0:3, 1:1}
        int[] nums = {1, -1, 0};
        assertEquals(2, solver.subarraySum(nums, -1));
    }
}
