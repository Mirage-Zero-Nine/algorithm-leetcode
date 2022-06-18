package solution.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of digit strings nums and a digit string target.
 * Return the number of pairs of indices (i, j) (i != j) such that the concatenation of nums[i] + nums[j] equals target.
 *
 * @author BorisMirage
 * Time: 2021/10/09 17:25
 * Created with IntelliJ IDEA
 */

public class NumOfPairs_2023 {
    /**
     * If a pair is a valid output, then each one in the pair should be the starting of (and ends with) the target.
     * Hence, keep two maps. One map count the length of prefix of the string, and how many prefix under this length.
     * The other map count the frequency of the suffix of the target string.
     * If a string is the start (or the end) of the target, the number of valid pair can be obtained from the map.
     *
     * @param nums   given numbers array
     * @param target target number in string
     * @return the number of pairs of indices (i, j) (i != j) that the concatenation of nums[i] + nums[j] equals target
     */
    public int numOfPairs(String[] nums, String target) {

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0, n = target.length();
        Map<Integer, Integer> prefix = new HashMap<>(), suffix = new HashMap<>();

        for (String num : nums) {
            int size = num.length();

            if (target.startsWith(num)) { // if a prefix of the target is found, find how many valid suffix to make pair
                count += suffix.getOrDefault(n - size, 0);
            }
            if (target.endsWith(num)) { // if a suffix of the target is found, find how many valid prefix to make pair
                count += prefix.getOrDefault(n - size, 0);
            }
            if (target.startsWith(num)) {
                prefix.put(size, 1 + prefix.getOrDefault(size, 0));
            }
            if (target.endsWith(num)) {
                suffix.put(size, 1 + suffix.getOrDefault(size, 0));
            }
        }

        return count;
    }
}
