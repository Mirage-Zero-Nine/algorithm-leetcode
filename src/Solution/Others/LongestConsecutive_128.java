package Solution.Others;

import java.util.HashMap;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * @author BorisMirage
 * Time: 2019/07/06 12:11
 * Created with IntelliJ IDEA
 */

public class LongestConsecutive_128 {
    /**
     * Use a hash map to save the # of current consecutive elements.
     * Each time, if array element's left or right exist in array, then add it to new consecutive elements.
     * Elements inside this range will not meet again.
     * Hence, directly update new length to beginning and the end of it is enough.
     *
     * @param nums given int array
     * @return longest consecutive elements
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (!m.containsKey(num)) {      // avoid duplication
                int left = m.getOrDefault(num - 1, 0);
                int right = m.getOrDefault(num + 1, 0);
                m.put(num, left + right + 1);
                max = Integer.max(max, left + right + 1);

                /*
                 * If found an element in array that has a longer consecutive length, the boundary should be updated.
                 * Elements inside this range will not meet again.
                 * Hence, directly update new length to beginning and the end of it is enough. */
                m.put(num - left, left + right + 1);
                m.put(num + right, left + right + 1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestConsecutive_128 test = new LongestConsecutive_128();
        System.out.println(test.longestConsecutive(new int[]{1, 2, 0, 1}));
    }
}
