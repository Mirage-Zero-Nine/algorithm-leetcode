package playground;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Minimum number of distinct elements after removing m item.
 * Given an array of items, an i-th index element denotes the item id’s and given a number m.
 * The task is to remove m elements such that there should be minimum distinct id’s left.Print the number of distinct id’s.
 *
 * @author BorisMirage
 * Time: 2019/07/12 19:42
 * Created with IntelliJ IDEA
 */


public class DistinctIds {

    /**
     * @param arr given array
     * @param n   size of array
     * @param mi  m items to be removed
     * @return ids left in array after remove
     */
    public int distinctIds(int[] arr, int n, int mi) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int j : arr) {
            m.put(j, m.getOrDefault(j, 0) + 1);
        }
        List<Integer> l = new LinkedList<>();
        for (Integer i : m.keySet()) {
            l.add(m.get(i));
        }
        Collections.sort(l);
        int count = m.size();
        for (Integer i : l) {
            if (mi - i > 0) {
                count--;
                mi = mi - i;
            } else {
                break;
            }
        }
        return count;
    }
}
