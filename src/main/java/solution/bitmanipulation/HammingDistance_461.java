package solution.bitmanipulation;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * Note:
 * 0 ≤ x, y < 231.
 *
 * @author BorisMirage
 * Time: 2020/01/13 17:04
 * Created with IntelliJ IDEA
 */

public class HammingDistance_461 {
    /**
     * XOR two numbers so that each different bit will be 1 in result.
     * Count how many 1s in result int.
     *
     * @param x first int
     * @param y second int
     * @return Hamming distance (# of different bits in two numbers)
     */
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y, count = 0;

        while (tmp > 0) {
            count += tmp & 1;
            tmp >>= 1;
        }

        return count;
    }
}
