package solution.bitmanipulation;

/**
 * Given a non negative integer number num.
 * For every numbers i in the range 0 ≤ i ≤ num, calculate the number of 1's in their binary representation and return them as an array.
 *
 * @author BorisMirage
 * Time: 2019/07/01 15:17
 * Created with IntelliJ IDEA
 */

public class CountBits_338 {
    /**
     * out[i] = out[i / 2] + i % 2.
     *
     * @param num given array
     * @return number of 1's in their binary representation
     */
    public int[] countBits(int num) {
        int[] out = new int[num + 1];

        for (int i = 1; i <= num; i++) {

            /*
             * # of 1 is equal to # of 1 that remove last digit, which is out[i >> 1].
             * If i is odd, then add a 1 to result, as i's last digit should be 1 if it is odd. */
            out[i] = out[i >> 1] + (i & 1);       // i >> 1 is equivalent to i / 2, if i is odd then add one more 1
        }
        return out;
    }
}
