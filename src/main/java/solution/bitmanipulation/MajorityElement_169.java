package solution.bitmanipulation;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * The array is non-empty and the majority element always exist in the array.
 *
 * @author BorisMirage
 * Time: 2019/06/30 21:00
 * Created with IntelliJ IDEA
 */

public class MajorityElement_169 {
    /**
     * Count bit sum for each number.
     * The majority element will make each bits make up this majority number appearing more than n/2 times.
     *
     * @param nums given array
     * @return majority element
     */
    public int majorityElement(int[] nums) {
        int[] bit = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++)
                if ((num >> (31 - i) & 1) == 1) {       // count bit appearance
                    bit[i]++;
                }
        }

        int majority = 0;
        for (int i = 0; i < 32; i++) {
            bit[i] = bit[i] > nums.length / 2 ? 1 : 0;
            majority += bit[i] * (1 << (31 - i));
        }
        return majority;
    }
}
