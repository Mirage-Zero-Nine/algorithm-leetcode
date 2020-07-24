package Solution.Others;

import java.util.LinkedList;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * Given n and k, return the kth permutation sequence.
 * n will be between 1 and 9 inclusive.
 * k will be between 1 and n! inclusive.
 *
 * @author BorisMirage
 * Time: 2018/08/06 14:35
 * Created with IntelliJ IDEA
 */

public class GetPermutation_60 {
    /**
     * This problem can not be solved in time when using backtracking.
     * Only need to find out kth permutation sequence, hence no need to find all sequence.
     * Each digit of kth permutations is determined by number of its subsequence.
     * If n = 4, k = 10, then the first digit is 10 / 6, which is 1 (index = 1 and representing 2).
     * Repeat this process until all digits are found.
     *
     * @param n between 1 and 9 inclusive
     * @param k between 1 and n! inclusive
     * @return kth permutation sequence
     */
    public String getPermutation(int n, int k) {

        List<Integer> set = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            set.add(i);
        }

        int[] factorial = new int[n];       // save factorial from 1 to n
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = i * factorial[i - 1];
        }

        k = k - 1;      // subset index starts from 0
        StringBuilder output = new StringBuilder();

        for (int i = n; i > 0; i--) {

            int temp = k / factorial[i - 1];
            k = k % factorial[i - 1];
            output.append(set.get(temp));

            set.remove(temp);       // find digits in subset that do not contains previous selected digit
        }

        return output.toString();
    }
}
