package solution.bitmanipulation;

/**
 * Write a function that takes an unsigned integer, return the number of '1' bits it has (also known as the Hamming weight).
 *
 * @author BorisMirage
 * Time: 2019/10/11 20:12
 * Created with IntelliJ IDEA
 */

public class HammingWeight_191 {
    /**
     * Right shift one bit each time.
     *
     * @param n given int
     * @return bit in int
     */
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count = count + (1 & n);
            n >>>= 1;
        }

        return count;
    }
}
