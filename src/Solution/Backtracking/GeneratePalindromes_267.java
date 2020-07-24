package Solution.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 * Return an empty list if no palindromic permutation could be form.
 *
 * @author BorisMirage
 * Time: 2019/09/14 10:34
 * Created with IntelliJ IDEA
 */

public class GeneratePalindromes_267 {
    /**
     * Backtracking with pruning. Basic backtracking will cause TLE.
     * First, check if current string contains palindromic permutation.
     * Then, if string contains a char that occurred in odd times, then put it into middle of palindromic permutations.
     * Based on that, only generate half of the palindromic string, since palindromic permutations is mirrored.
     *
     * @param s given string
     * @return return all the palindromic permutations (without duplicates)
     */
    public List<String> generatePalindromes(String s) {

        List<String> out = new ArrayList<>();

        int odds = 0;
        int[] map = new int[256];

        for (char c : s.toCharArray()) {
            map[c]++;
            odds = (map[c] & 1) == 1 ? odds + 1 : odds - 1;
        }

        if (odds > 1) {
            return out;
        }

        String mid = "";
        int length = 0;
        for (int i = 0; i < 256; i++) {
            if (map[i] > 0) {

                if ((map[i] & 1) == 1) {        // if string contains char in odd times, then it will be in the middle
                    mid = "" + (char) i;
                    map[i]--;
                }
                map[i] /= 2;        // palindromic permutations only requires half of string
                length += map[i];   // length of half string
            }
        }
        backtracking(out, map, length, "", mid);
        return out;
    }

    private void backtracking(List<String> out, int[] count, int length, String s, String mid) {
        if (s.length() == length) {
            StringBuilder reverse = new StringBuilder(s).reverse();     // second half
            out.add(s + mid + reverse);
            return;
        }

        for (int i = 0; i < 256; i++) {
            if (count[i] > 0) {
                count[i]--;
                backtracking(out, count, length, s + (char) i, mid);
                count[i]++;
            }
        }
    }
}
