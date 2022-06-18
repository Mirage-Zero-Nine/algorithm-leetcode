package solution.array;

/**
 * Given an integer n and an integer start.
 * Define an array nums where nums[i] = start + 2 * i (0-indexed) and n == nums.length.
 * Return the bitwise XOR of all elements of nums.
 *
 * @author BorisMirage
 * Time: 2020/06/20 20:37
 * Created with IntelliJ IDEA
 */

public class XorOperation_1486 {
    /**
     * Directly XOR each element. Use the given formula to calculate element in array.
     *
     * @param n     n elements
     * @param start first element
     * @return he bitwise XOR of all elements of array
     */
    public int xorOperation(int n, int start) {
        int out = start;

        for (int i = 1; i < n; i++) {
            int tmp = start + 2 * i;
            out ^= tmp;
        }

        return out;
    }
}
