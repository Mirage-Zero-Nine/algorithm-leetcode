package solution.array;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 *
 * @author BorisMirage
 * Time: 2020/02/14 21:15
 * Created with IntelliJ IDEA
 */

public class MaximumProduct_628 {
    /**
     * Use 3 integers to store max 3 values, and two integer for min values (input may contains negative int).
     * Finally, compare the product of min * min * max and max * max * max.
     *
     * @param nums given array
     * @return three numbers whose product is maximum and output the maximum product
     */
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;       // max1 < max2 < max3
        int min1 = Integer.MAX_VALUE, min2 = Integer.MIN_VALUE;                                 // min1 < min2

        for (int i : nums) {
            if (i > max3) {
                max1 = max2;
                max2 = max3;
                max3 = i;
            } else if (i > max2) {
                max1 = max2;
                max2 = i;
            } else if (i > max1) {
                max1 = i;
            }

            if (i < min1) {
                min2 = min1;
                min1 = i;
            } else if (i < min2) {
                min2 = i;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max3);
    }
}
