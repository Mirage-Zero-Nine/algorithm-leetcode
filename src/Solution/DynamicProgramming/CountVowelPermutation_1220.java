package Solution.DynamicProgramming;

import java.util.Arrays;

/**
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2019/10/09 18:13
 * Created with IntelliJ IDEA
 */

public class CountVowelPermutation_1220 {
    /**
     * Dynamic programming.
     * a: char after 'a' can only be 'e'. Hence, # of 'a' is equal to 'e'.
     * e: char after 'e' can only be 'a' or 'i', which is # of 'a' + # of 'i'.
     * i: char after 'i' can not be followed by 'i'
     * o: char after 'o' can be followed by 'i' or a 'u'.
     * u: char after 'u' can be followed by 'a', same situation as 'a'.
     *
     * @param n given length
     * @return how many strings of length n can be formed
     */
    public int countVowelPermutation(int n) {
        int[] current = new int[5], previous = new int[5];
        Arrays.fill(previous, 1);
        int MOD = 1_000_000_007;

        for (int i = 0; i < n; i++) {
            current[0] = previous[1] % MOD;                                                                 // a
            current[1] = (previous[0] + previous[2]) % MOD;                                                 // e
            current[2] = (((previous[0] + previous[1]) % MOD + previous[3]) % MOD + previous[4]) % MOD;     // i
            current[3] = (previous[2] % MOD + previous[4] % MOD) % MOD;                                     // o
            current[4] = (previous[0] % MOD) % MOD;                                                         // u
            int[] tmp = previous;
            previous = current;
            current = tmp;
        }

        int out = 0;
        for (int value : current) {
            out = (out + value) % MOD;
        }

        return out;
    }
}
