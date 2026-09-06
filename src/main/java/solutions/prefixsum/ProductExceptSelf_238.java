package solutions.prefixsum;

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
     * Returns the product of every value other than the one at each index, without using division.
     *
     * <p>The result for index {@code i} can be separated into two independent parts:
     * {@code product(nums[0..i - 1]) * product(nums[i + 1..n - 1])}. This observation avoids
     * calculating the total product, which would be incorrect when {@code nums} contains zero.
     *
     * <p>First, the left-to-right pass stores the product to the <em>left</em> of each index in
     * {@code output}. For example, after that pass, {@code output[3]} is
     * {@code nums[0] * nums[1] * nums[2]}. Then the right-to-left pass maintains the product of
     * the values to the <em>right</em> of the current index in one variable and multiplies it into
     * the already-stored left product. Thus, the output array doubles as the left-product array,
     * and no second auxiliary array is needed.
     *
     * <p>The empty product is {@code 1}, so a one-element input produces {@code [1]}. An empty
     * input is accepted and returned unchanged.
     *
     * @param nums the input values; prefix and suffix products are assumed to fit in an {@code int}
     * @return an array where each element is the product of all input values except the value at
     * the same index
     * @implNote Runs in {@code O(n)} time and {@code O(1)} auxiliary space, excluding the returned
     * output array.
     */
    public int[] productExceptSelf(int[] nums) {
        // corner case
        if (nums.length < 1) {
            return nums;
        }

        int[] output = new int[nums.length];
        // There are no values to the left of the first element: its left product is the empty product, 1.
        output[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            // Before this assignment, output[i - 1] is product(nums[0..i - 2]). Multiplying by
            // nums[i - 1] extends it to product(nums[0..i - 1]), the left product for i.
            output[i] = nums[i - 1] * output[i - 1];
        }

        // At i = n - 2, the only value to its right is nums[n - 1]. Each iteration extends this
        // product one position to the left after using it for the current output element.
        int rightProduct = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            // output[i] currently holds its left product; rightProduct holds its right product.
            output[i] = output[i] * rightProduct;
            rightProduct *= nums[i];
        }

        return output;
    }
}
