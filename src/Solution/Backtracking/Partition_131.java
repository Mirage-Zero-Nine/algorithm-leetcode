package Solution.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * @author BorisMirage
 * Time: 2019/06/06 14:45
 * Created with IntelliJ IDEA
 */

public class Partition_131 {
    /**
     * Backtracking.
     * Use backtracking to "insert" all possible breaking point for substring.
     * If creat a palindrome substring, then add it to list.
     *
     * @param s given string
     * @return all possible palindrome partitioning of s
     */
    public List<List<String>> partition(String s) {
        List<List<String>> out = new ArrayList<>();

        backtracking(s, out, new ArrayList<>());
        return out;
    }

    /**
     * Backtracking to generate all possible substring to find all possible palindrome partitioning of s.
     *
     * @param s    given string
     * @param out  output list
     * @param temp temp list
     */
    private void backtracking(String s, List<List<String>> out, List<String> temp) {
        if (s.length() == 0) {
            out.add(new ArrayList<>(temp));     // also hold the corner case
            return;
        }
        for (int i = 1; i <= s.length(); i++) {     // i should starts at 1 to avoid empty substring
            if (isPalindrome(s.substring(0, i))) {
                temp.add(s.substring(0, i));
                backtracking(s.substring(i), out, temp);        // repeat this process to rest of string
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * Check whether given string is palindrome.
     *
     * @param s given string
     * @return whether given string is palindrome
     */
    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abcabc";
        Partition_131 test = new Partition_131();
        System.out.println(test.partition(s));
    }
}
