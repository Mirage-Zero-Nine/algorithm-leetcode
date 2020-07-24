package Solution.Array;

/**
 * Given an array nums of n integers where n > 1.
 * Return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * Note that in this problem, the product of all elements will not exceed the max integer value.
 *
 * @author BorisMirage
 * Time: 2019/02/20 19:18
 * Created with IntelliJ IDEA
 */

public class ProductExceptSelf_238 {
    /**
     * Two traversals.
     * First traverse multiple each element of its left.
     * Second traverse multiple each element of its right.
     *
     * @param nums int array
     * @return array that each element equal to the product of all the elements of nums except nums[i]
     */
    public int[] productExceptSelf(int[] nums) {

        /* Corner case */
        if (nums.length < 1) {
            return nums;
        }

        int[] out = new int[nums.length];
        out[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            out[i] = out[i - 1] * nums[i - 1];     // left part
        }

        int right = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            out[i] = out[i] * right;        // right part except self
            right *= nums[i];
        }

        return out;
    }
}
