package solution.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code.
 * Print the sequence of gray code. A gray code sequence must begin with 0.
 *
 * @author BorisMirage
 * Time: 2019/06/03 14:05
 * Created with IntelliJ IDEA
 */

public class GreyCode_89 {
    /**
     * G(i) = i^ (i/2)
     *
     * @param n n bits
     * @return sequence of gray code
     */
    public List<Integer> grayCode(int n) {
        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < 1 << n; i++) {
            output.add(i ^ i >> 1);     // G(i) = i^ (i/2)
        }
        return output;
    }
}
