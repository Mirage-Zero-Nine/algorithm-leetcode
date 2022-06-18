package solution.map;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an array of integers arr, returns true iff the number of occurrences of each value in the array is unique.
 *
 * @author BorisMirage
 * Time: 2019/09/29 14:50
 * Created with IntelliJ IDEA
 */

public class UniqueOccurrences_1207 {
    /**
     * Use a hash map to count the occurrence, and a hash set to find if there is any duplicated appearance.
     *
     * @param arr given int array
     * @return returns true iff the number of occurrences of each value in the array is unique
     */
    public boolean uniqueOccurrences(int[] arr) {

        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            m.put(arr[i], m.getOrDefault(i, 0) + 1);
        }

        HashSet<Integer> s = new HashSet<>();
        for (Integer value : m.values()) {
            if (!s.add(value)) {
                return false;
            }
        }
        return true;
    }
}
