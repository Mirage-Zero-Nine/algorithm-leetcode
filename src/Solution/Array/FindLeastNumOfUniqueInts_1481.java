package Solution.Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of integers arr and an integer k.
 * Find the least number of unique integers after removing exactly k elements.
 *
 * @author BorisMirage
 * Time: 2020/06/13 20:18
 * Created with IntelliJ IDEA
 */

public class FindLeastNumOfUniqueInts_1481 {
    /**
     * Count the frequency of each element in array.
     * Remove the least frequent elements until k elements has been removed.
     * The number of elements left in given array is the output.
     *
     * @param arr given array
     * @param k   k elements to be removed
     * @return the least number of unique integers after removing exactly k elements
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> m = new HashMap<>();

        for (int n : arr) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }

        List<Integer> tmp = new ArrayList<>(m.values());        // store elements frequency
        tmp.sort(Comparator.comparingInt(o -> o));              // sort by frequency

        int i = 0;
        while (k > 0) {
            k -= tmp.get(i++);
        }

        return k == 0 ? tmp.size() - i : tmp.size() - i + 1;
    }
}
