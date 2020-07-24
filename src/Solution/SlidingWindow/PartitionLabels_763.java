package Solution.SlidingWindow;

import java.util.*;

/**
 * A string S of lowercase letters is given.
 * Partition this string into as many parts as possible so that each letter appears in at most one part.
 * Return a list of integers representing the size of these parts.
 *
 * @author BorisMirage
 * Time: 2019/07/18 13:15
 * Created with IntelliJ IDEA
 */

public class PartitionLabels_763 {
    /**
     * Sliding window problem.
     * Traverse the array and find the last appearance of each char in string.
     * Then traverse the string again with a window.
     * Each time, if current position is a last appearance char in string, then one substring is found.
     * Add the length of substring into output list, and set the start of next window to the next char in string.
     *
     * @param s given string
     * @return list of integers representing the size of these parts that each letter appears in at most one part
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> out = new LinkedList<>();

        /* Corner case */
        if (s.length() == 0) {
            return out;
        }

        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {      // find the last appearance of each char in string
            arr[s.charAt(i) - 'a'] = i;
        }

        int last = 0;       // last index of char's appearance
        int start = 0;      // start position of sub string
        for (int i = 0; i < s.length(); i++) {

            last = Math.max(last, arr[s.charAt(i) - 'a']);

            if (last == i) {
                out.add(last - start + 1);
                start = last + 1;
            }
        }

        return out;
    }
}
