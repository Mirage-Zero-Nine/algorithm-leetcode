package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanDivideIntoSubsequences_1121Test {

    private final CanDivideIntoSubsequences_1121 test = new CanDivideIntoSubsequences_1121();

    @Test
    public void testHappyCases() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 2, 2, 3, 3, 4, 4}, 3));
        assertFalse(test.canDivideIntoSubsequences(new int[]{5, 6, 6, 7, 8}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 2, 3}, 3));
        assertFalse(test.canDivideIntoSubsequences(new int[]{1, 1, 1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2));
    }

    @Test
    public void testSingleElement() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1}, 1));
    }

    @Test
    public void testAllSameElements() {
        // [1,1,1,1] max freq=4, K=1 => 4 >= 4*1 = true
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 1, 1, 1}, 1));
        // [1,1,1,1] K=2 => 4 >= 4*2 = false
        assertFalse(test.canDivideIntoSubsequences(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testKEqualsOne() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 1, 1}, 1));
    }

    @Test
    public void testNoDuplicates() {
        // all distinct, max freq=1, n >= 1*K
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 2, 3, 4, 5}, 5));
        assertFalse(test.canDivideIntoSubsequences(new int[]{1, 2, 3, 4, 5}, 6));
    }

    @Test
    public void testTwoGroups() {
        // [1,1,2,2,3,3] max freq=2, K=3 => 6 >= 2*3 = true
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 1, 2, 2, 3, 3}, 3));
    }

    @Test
    public void testNegativeCaseHighK() {
        assertFalse(test.canDivideIntoSubsequences(new int[]{1, 2, 2, 3}, 3));
    }

    @Test
    public void testGiantCase() {
        // 10000 elements, each appears twice => max freq=2, K=5000 => 10000 >= 2*5000 = true
        int[] arr = new int[10000];
        for (int i = 0; i < 5000; i++) {
            arr[2 * i] = i;
            arr[2 * i + 1] = i;
        }
        assertTrue(test.canDivideIntoSubsequences(arr, 5000));
    }
}
