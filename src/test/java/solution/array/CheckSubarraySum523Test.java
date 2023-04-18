package solution.array;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/18 17:56
 * Created with IntelliJ IDEA
 */

public class CheckSubarraySum523Test {

    private final CheckSubarraySum_523 test = new CheckSubarraySum_523();

    @Test
    public void testCheckSubarraySum() {

        int[] nums1 = {23, 2, 4, 6, 7};
        int k1 = 6;
        assertTrue(test.checkSubarraySum(nums1, k1));
        assertTrue(test.checkSubarraySumByTraverseArray(nums1, k1));

        int[] nums2 = {0};
        int k3 = 0;
        assertFalse(test.checkSubarraySum(nums2, k3));
        assertFalse(test.checkSubarraySumByTraverseArray(nums2, k3));

        int[] nums3 = {0, 0};
        int k4 = 0;
        assertTrue(test.checkSubarraySum(nums3, k4));
        assertTrue(test.checkSubarraySumByTraverseArray(nums3, k4));

        int[] nums5 = {0, 1, 0};
        assertFalse(test.checkSubarraySum(nums5, 0));
        assertFalse(test.checkSubarraySumByTraverseArray(nums5, 0));


        int[] nums6 = {0, 1, 0};
        assertTrue(test.checkSubarraySum(nums6, 1));
        assertTrue(test.checkSubarraySumByTraverseArray(nums6, 1));
    }
}