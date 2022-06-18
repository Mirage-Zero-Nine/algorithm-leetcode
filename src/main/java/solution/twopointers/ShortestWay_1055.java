package solution.twopointers;

/**
 * From any string, it form a subsequence of that string by deleting some number of characters (possibly no deletions).
 * Given two strings source and target.
 * Return the minimum number of subsequences of source such that their concatenation equals target.
 * If the task is impossible, return -1.
 *
 * @author BorisMirage
 * Time: 2019/07/17 15:48
 * Created with IntelliJ IDEA
 */

public class ShortestWay_1055 {
    /**
     * Greedy & two pointers.
     * Target can only be formed by substring. The substring can be formed by subsequence in source.
     * Hence, use a pointer points at start position of target, and check each character in source.
     * Find the max subsequence in source that can form substring in target.
     *
     * @param source source string
     * @param target target string
     * @return minimum number of subsequences of source such that their concatenation equals target
     */
    public int shortestWay(String source, String target) {

        int min = 0, p = 0;     // p is the pointer in target, points at the start position of substring

        while (p < target.length()) {

            int start = p;      // mark start position, in case there contains char in target that do not in source
            for (int i = 0; i < source.length(); i++) {

                /*
                 * Find maximum chars in target that can be made up by subsequence of source.
                 * Each time, if a char in source is matched to char in target, move pointer in target forward.
                 * Finally, if target pointer moves to the end of target, return counter. */
                if (p < target.length() && source.charAt(i) == target.charAt(p)) {
                    p++;
                }
            }

            if (start == p) {
                return -1;
            }

            min++;      // find one subsequence
        }

        return min;
    }
}
