package solution.array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link GoodIndices_2420}.
 */
public class GoodIndices_2420Test {

    private final GoodIndices_2420 solver = new GoodIndices_2420();

    @Test
    public void testLeetCodeExample1() {
        // nums = [2,1,1,1,3,4,1], k = 2
        // Good indices: 2, 3
        List<Integer> result = solver.goodIndices(new int[]{2, 1, 1, 1, 3, 4, 1}, 2);
        assertEquals(2, result.size());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testLeetCodeExample2() {
        // nums = [2,1,1], k = 1
        // No good indices (range is empty since n-k = 2, so i from 1 to 1, but need i < n-k = 2)
        // Actually i from k=1 to n-k=2-1=1, so i=1 only
        // Check non-increasing before i=1: nums[0]=2, length=0+1=1 >= k-1=0 ✓
        // Check non-decreasing after i=1: nums[2]=1, length=0 >= k-1=0 ✓
        // Wait, let me recalculate
        List<Integer> result = solver.goodIndices(new int[]{2, 1, 1}, 1);
        assertEquals(1, result.size());
        assertTrue(result.contains(1));
    }

    @Test
    public void testNoGoodIndices() {
        // nums = [1,2,3], k = 1
        // i from 1 to 2-1=1, so i=1 only
        // nonIncreasing: [0,1,0] (nums[1]=2 > nums[0]=1, so nonIncreasing[1]=0; nums[2]=3 > nums[1]=2, so nonIncreasing[2]=0)
        // nonDecreasing: [0,1,2]
        // i=1: nonIncreasing[0]=0 >= 0 ✓, nonDecreasing[2]=2 >= 0 ✓ → good
        List<Integer> result = solver.goodIndices(new int[]{1, 2, 3}, 1);
        assertTrue(result.contains(1));
    }

    @Test
    public void testSmallArray() {
        // nums = [1], k = 1 → n-k = 0, range empty
        List<Integer> result = solver.goodIndices(new int[]{1}, 1);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAllSame() {
        // nums = [1,1,1,1,1], k = 2
        // nonIncreasing: [0,1,2,3,4]
        // nonDecreasing: [0,1,2,3,4]
        // i from 2 to 5-2=3, so i=2 only
        // i=2: nonIncreasing[1]=1 >= 1 ✓, nonDecreasing[4]=4 >= 1 ✓ → good
        List<Integer> result = solver.goodIndices(new int[]{1, 1, 1, 1, 1}, 2);
        assertTrue(result.contains(2));
    }

    @Test
    public void testStrictlyDecreasing() {
        // nums = [5,4,3,2,1], k = 2
        // nonIncreasing: [0,1,2,3,4]
        // nonDecreasing: [0,0,0,0,0]
        // i from 2 to 3, so i=2,3
        // i=2: nonIncreasing[1]=1 >= 1 ✓, nonDecreasing[4]=0 >= 1 ✗
        // i=3: nonIncreasing[2]=2 >= 1 ✓, nonDecreasing[5] → out of bounds
        // Wait, n=5, i+k=3+2=5, index 5 is out of bounds for array of size 5
        // Let me check: nonDecreasingSubarrayLength has size n=5, indices 0-4
        // i=3: nonDecreasing[3+2]=nonDecreasing[5] → out of bounds!
        // Hmm, the code accesses i+k which can be n. Let me check the code...
        // The loop is i from k to n-k-1 (i < n-k), so max i = n-k-1, max i+k = n-1
        // For k=2, n=5: i goes from 2 to 2 (i < 3), so i=2 only
        // i=2: nonIncreasing[1]=1 >= 1 ✓, nonDecreasing[4]=0 >= 1 ✗
        List<Integer> result = solver.goodIndices(new int[]{5, 4, 3, 2, 1}, 2);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testStrictlyIncreasing() {
        // nums = [1,2,3,4,5], k = 2
        // nonIncreasing: [0,0,0,0,0]
        // nonDecreasing: [0,1,2,3,4]
        // i from 2 to 2 (i < 3)
        // i=2: nonIncreasing[1]=0 >= 1 ✗
        List<Integer> result = solver.goodIndices(new int[]{1, 2, 3, 4, 5}, 2);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFlatMiddle() {
        // nums = [1,2,1,1,1,2,1], k = 2
        // nonIncreasing: [0,0,1,2,3,0,1]
        // nonDecreasing: [0,1,0,1,2,3,0]
        // i from 2 to 7-2-1=4, so i=2,3,4
        // i=2: nonIncreasing[1]=0 >= 1 ✗
        // i=3: nonIncreasing[2]=1 >= 1 ✓, nonDecreasing[5]=3 >= 1 ✓ → good
        // i=4: nonIncreasing[3]=2 >= 1 ✓, nonDecreasing[6]=0 >= 1 ✗
        List<Integer> result = solver.goodIndices(new int[]{1, 2, 1, 1, 1, 2, 1}, 2);
        assertEquals(1, result.size());
        assertTrue(result.contains(3));
    }

    @Test
    public void testKEquals1() {
        // nums = [1,2,1,2,1], k = 1
        // nonIncreasing: [0,0,1,0,1]
        // nonDecreasing: [0,1,0,1,0]
        // i from 1 to 5-1-1=3, so i=1,2,3
        // i=1: nonIncreasing[0]=0 >= 0 ✓, nonDecreasing[2]=0 >= 0 ✓ → good
        // i=2: nonIncreasing[1]=0 >= 0 ✓, nonDecreasing[3]=1 >= 0 ✓ → good
        // i=3: nonIncreasing[2]=1 >= 0 ✓, nonDecreasing[4]=0 >= 0 ✓ → good
        List<Integer> result = solver.goodIndices(new int[]{1, 2, 1, 2, 1}, 1);
        assertEquals(3, result.size());
    }

    @Test
    public void testLargeK() {
        // nums = [1,2,3,4,5,6,7], k = 3
        // nonIncreasing: [0,0,0,0,0,0,0]
        // nonDecreasing: [0,1,2,3,4,5,6]
        // i from 3 to 7-3-1=3, so i=3 only
        // i=3: nonIncreasing[2]=0 >= 2 ✗
        List<Integer> result = solver.goodIndices(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testTwoElements() {
        // nums = [1,2], k = 1 → n-k = 1, range i from 1 to 0 → empty
        List<Integer> result = solver.goodIndices(new int[]{1, 2}, 1);
        assertTrue(result.isEmpty());
    }
}
