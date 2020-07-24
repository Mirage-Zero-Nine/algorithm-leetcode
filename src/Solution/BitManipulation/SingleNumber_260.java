package Solution.BitManipulation;

import java.util.Arrays;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 *
 * @author BorisMirage
 * Time: 2019/06/30 16:44
 * Created with IntelliJ IDEA
 */

public class SingleNumber_260 {
    /**
     * XOR all numbers in array (a xor b).
     * (a xor b) must be non-zero, otherwise they are equal, and a ^ b differ in at least one bit.
     * The different bit in a ^ b result will be 1. Hence, find the last bit set in a ^ b.
     * Find last bit set using the low bit formula m & -m.
     * Partition the numbers into two groups: one group with bit_i == 1 and the other group with bit_i == 0.
     * a is in one group and b is in the other.
     * a is the only single number in its group.
     * b is also the only single number in its group.
     * XOR all numbers in a's group to get a, XOR all numbers in b's group to get b
     * Alternatively, XOR (a xor b) with a gets b.
     *
     * @param nums given array
     * @return two elements that appear only once
     */
    public int[] singleNumber(int[] nums) {

        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }

        /*
         * diff &= -diff => ~(diff - 1) = - (diff - 1) - 1 = -diff
         * This is to get the last set bit (last bit of 1, all bits at this bit's right are 0).
         * diff - 1 will set all bits right at last set bit to 0.
         * Then the ~ will reverse all bits, and then + 1.
         * In this way, the last set bit can be found. */
        diff &= -diff;      // get its last set bit

        int[] result = {0, 0};        // this array stores the two numbers we will return

        for (int num : nums) {

            /*
             * XOR = 1 means this bits in two numbers will contains one 0 and one 1.
             * If diff & num is 0, then this number may be one of two single numbers, that contains last set bit of 0.
             * If diff & num is 0, then this number may contains last set bit of 1. */
            result[((num & diff) == 0) ? 0 : 1] ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        SingleNumber_260 test = new SingleNumber_260();
        System.out.println(Arrays.toString(test.singleNumber(new int[]{1, 1, 2, 2, 3, 3, 4, 5})));
    }
}
