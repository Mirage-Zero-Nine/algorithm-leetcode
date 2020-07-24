package Solution.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 * @author BorisMirage
 * Time: 2020/02/17 23:04
 * Created with IntelliJ IDEA
 */

public class MinSetSize_1338 {
    /**
     * Bucket sort. Use a bucket array.
     * Idea is same compare to directly sort hash map, but reduce runtime from O(nlogn) to O(n).
     * The index of array is the frequency of elements in array.
     * The value in array is how many numbers in array have this frequency.
     *
     * @param arr given array
     * @return minimum size of the set so that at least half of the integers of the array are removed
     */
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int max = 0;                          // max appearance
        for (int i : arr) {
            m.put(i, m.getOrDefault(i, 0) + 1);
            max = Math.max(max, m.get(i));
        }
        int[] buckets = new int[max + 1];     // index: frequency count. value: how many numbers have this frequency

        for (int v : m.values()) {
            buckets[v]++;
        }

        int half = arr.length / 2, index = max, count = 0;

        while (half > 0) {
            if (buckets[index] > 0) {
                half -= index;
                buckets[index]--;
                count++;
            }
            if (buckets[index] == 0) {
                index--;
            }
        }

        return count;
    }

    /**
     * Count frequency, then sort the values and remove until reaches at least half of the array.
     *
     * @param arr given array
     * @return minimum size of the set so that at least half of the integers of the array are removed
     */
    public int minSetSizeBySortingMap(int[] arr) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i : arr) {
            m.put(i, m.getOrDefault(i, 0) + 1);
        }

        List<Integer> count = new ArrayList<>(m.values());      // linked list will cause TLE
        Collections.sort(count);
        int half = arr.length / 2, out = 0;
        for (int i = count.size() - 1; i >= 0; i--) {
            half -= count.get(i);
            out++;
            if (half <= 0) {
                break;
            }
        }

        return out;
    }
}
